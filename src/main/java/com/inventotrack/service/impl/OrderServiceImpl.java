package com.inventotrack.service.impl;

import com.inventotrack.dao.CustomerOrderDAO;
import com.inventotrack.dao.ProductDAO;
import com.inventotrack.dao.UserDAO;
import com.inventotrack.dto.OrderDTO;
import com.inventotrack.dto.OrderItemDTO;
import com.inventotrack.enums.InventoryTransactionType;
import com.inventotrack.enums.OrderStatus;
import com.inventotrack.exception.ResourceNotFoundException;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.logging.LoggerUtil;
import com.inventotrack.mapper.OrderMapper;
import com.inventotrack.model.CustomerOrder;
import com.inventotrack.model.OrderItem;
import com.inventotrack.model.Product;
import com.inventotrack.model.User;
import com.inventotrack.service.InventoryTransactionService;
import com.inventotrack.service.OrderService;
import com.inventotrack.util.JPAUtil;
import com.inventotrack.validation.OrderValidator;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class OrderServiceImpl implements OrderService {

    private final CustomerOrderDAO orderDAO;
    private final UserDAO userDAO;
    private final ProductDAO productDAO;

    private final InventoryTransactionService inventoryTransactionService =
            ServiceFactory.getInventoryTransactionService();

    private static final Logger logger =
            LoggerUtil.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(CustomerOrderDAO orderDAO,
                            UserDAO userDAO,
                            ProductDAO productDAO) {

        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
        this.productDAO = productDAO;
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {

        OrderValidator.validate(dto);

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            User user = userDAO.findById(em, dto.getUserId());

            if (user == null) {
                throw new ResourceNotFoundException(
                        "User not found with id : " + dto.getUserId());
            }

            CustomerOrder order = new CustomerOrder();

            order.setUser(user);
            order.setNotes(dto.getNotes());
            order.setStatus(OrderStatus.PENDING);

            order.setCreatedBy("SYSTEM");
            order.setUpdatedBy("SYSTEM");

            // Persist early so Hibernate generates the Order ID
            orderDAO.save(em, order);

            em.flush();

            BigDecimal total = BigDecimal.ZERO;

            for (OrderItemDTO itemDTO : dto.getItems()) {

                Product product =
                        productDAO.findById(em, itemDTO.getProductId());

                if (product == null) {
                    throw new ResourceNotFoundException(
                            "Product not found : " + itemDTO.getProductId());
                }

                if (!product.isActive()) {
                    throw new IllegalArgumentException(
                            "Product is inactive : " + product.getName());
                }

                if (product.getStockQuantity() < itemDTO.getQuantity()) {
                    throw new IllegalArgumentException(
                            "Insufficient stock for " + product.getName());
                }

                int previousStock = product.getStockQuantity();

                product.setStockQuantity(
                        previousStock - itemDTO.getQuantity());

                product.setUpdatedBy("SYSTEM");

                productDAO.update(em, product);

                inventoryTransactionService.recordTransaction(
                        em,
                        product,
                        InventoryTransactionType.SALE,
                        itemDTO.getQuantity(),
                        previousStock,
                        product.getStockQuantity(),
                        "CUSTOMER_ORDER",
                        order.getId(),
                        "Customer Order"
                );

                OrderItem orderItem = new OrderItem();

                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(itemDTO.getQuantity());
                orderItem.setUnitPrice(product.getPrice());

                order.getItems().add(orderItem);

                total = total.add(orderItem.getLineTotal());
            }

            order.setTotalAmount(total);

            orderDAO.update(em, order);

            em.getTransaction().commit();

            logger.info("Customer order created successfully. Order ID: "
                    + order.getId());

            return OrderMapper.toDTO(order);

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();

        }

    }

    @Override
    public OrderDTO getOrderById(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            CustomerOrder order = orderDAO.findById(em, id);

            if (order == null) {
                throw new ResourceNotFoundException(
                        "Order not found with id : " + id);
            }

            return OrderMapper.toDTO(order);

        } finally {

            em.close();
            logger.info("Fetched customer order. Order ID: " + id);

        }

    }

    @Override
    public List<OrderDTO> getAllOrders() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return OrderMapper.toDTOList(
                    orderDAO.findAllOrders(em));

        } finally {

            em.close();
            logger.info("Fetched all customer orders.");

        }

    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return OrderMapper.toDTOList(
                    orderDAO.findByUser(em, userId));

        } finally {

            em.close();
            logger.info("Fetched orders for user : " + userId);

        }

    }

    @Override
    public List<OrderDTO> getOrdersByStatus(OrderStatus status) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return OrderMapper.toDTOList(
                    orderDAO.findByStatus(em, status));

        } finally {

            em.close();
            logger.info("Fetched orders with status : " + status);

        }

    }

    @Override
    public OrderDTO updateOrderStatus(Long id,
                                      OrderStatus status) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            CustomerOrder order = orderDAO.findById(em, id);

            if (order == null) {
                throw new ResourceNotFoundException(
                        "Order not found with id : " + id);
            }

            em.getTransaction().begin();

            order.setStatus(status);
            order.setUpdatedBy("SYSTEM");

            orderDAO.update(em, order);

            em.getTransaction().commit();

            logger.info("Order status updated. Order ID: "
                    + id + ", Status: " + status);

            return OrderMapper.toDTO(order);

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();

        }

    }

    @Override
    public void deleteOrder(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            CustomerOrder order = orderDAO.findById(em, id);

            if (order == null) {
                throw new ResourceNotFoundException(
                        "Order not found with id : " + id);
            }

            em.getTransaction().begin();

            order.setStatus(OrderStatus.CANCELLED);
            order.setUpdatedBy("SYSTEM");

            orderDAO.update(em, order);

            em.getTransaction().commit();

            logger.info("Order cancelled. Order ID: " + id);

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();

        }

    }

    @Override
    public BigDecimal getTotalRevenue() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return orderDAO.calculateRevenue(em);

        } finally {

            em.close();
            logger.info("Revenue report requested.");

        }

    }

    @Override
    public Long getPendingOrdersCount() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return orderDAO.countPendingOrders(em);

        } finally {

            em.close();
            logger.info("Pending order count requested.");

        }

    }

}