package com.inventotrack.service.impl;

import com.inventotrack.dao.CustomerOrderDAO;
import com.inventotrack.dao.ProductDAO;
import com.inventotrack.dao.UserDAO;
import com.inventotrack.dto.OrderDTO;
import com.inventotrack.enums.OrderStatus;
import com.inventotrack.mapper.OrderMapper;
import com.inventotrack.service.OrderService;
import com.inventotrack.dto.OrderItemDTO;
import com.inventotrack.exception.ResourceNotFoundException;
import com.inventotrack.model.CustomerOrder;
import com.inventotrack.model.OrderItem;
import com.inventotrack.model.Product;
import com.inventotrack.model.User;
import com.inventotrack.util.JPAUtil;
import com.inventotrack.validation.OrderValidator;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final CustomerOrderDAO orderDAO;
    private final UserDAO userDAO;
    private final ProductDAO productDAO;

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

            BigDecimal total = BigDecimal.ZERO;

            for (OrderItemDTO itemDTO : dto.getItems()) {

                Product product =
                        productDAO.findById(em, itemDTO.getProductId());

                if (product == null) {

                    throw new ResourceNotFoundException(
                            "Product not found : "
                                    + itemDTO.getProductId());

                }

                if (!product.isActive()) {

                    throw new IllegalArgumentException(
                            "Product is inactive : "
                                    + product.getName());

                }

                if (product.getStockQuantity()
                        < itemDTO.getQuantity()) {

                    throw new IllegalArgumentException(
                            "Insufficient stock for "
                                    + product.getName());

                }

                product.setStockQuantity(

                        product.getStockQuantity()
                                - itemDTO.getQuantity()

                );

                product.setUpdatedBy("SYSTEM");

                productDAO.update(em, product);

                OrderItem orderItem = new OrderItem();

                orderItem.setOrder(order);

                orderItem.setProduct(product);

                orderItem.setQuantity(itemDTO.getQuantity());

                orderItem.setUnitPrice(product.getPrice());

                order.getItems().add(orderItem);

                total = total.add(orderItem.getLineTotal());

            }

            order.setTotalAmount(total);

            order.setCreatedBy("SYSTEM");

            order.setUpdatedBy("SYSTEM");

            orderDAO.save(em, order);

            em.getTransaction().commit();

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

        }

    }

    @Override
    public Long getPendingOrdersCount() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return orderDAO.countPendingOrders(em);

        } finally {

            em.close();

        }

    }
}