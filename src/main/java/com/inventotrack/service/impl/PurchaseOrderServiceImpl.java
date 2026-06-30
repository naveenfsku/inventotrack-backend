package com.inventotrack.service.impl;
import com.inventotrack.dao.ProductDAO;
import com.inventotrack.dao.PurchaseOrderDAO;
import com.inventotrack.dao.SupplierDAO;
import com.inventotrack.dto.PurchaseOrderDTO;
import com.inventotrack.dto.PurchaseOrderItemDTO;
import com.inventotrack.enums.PurchaseOrderStatus;
import com.inventotrack.mapper.PurchaseOrderMapper;
import com.inventotrack.model.Product;
import com.inventotrack.model.PurchaseOrder;
import com.inventotrack.model.PurchaseOrderItem;
import com.inventotrack.model.Supplier;
import com.inventotrack.service.PurchaseOrderService;
import com.inventotrack.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderDAO purchaseOrderDAO;

    private final SupplierDAO supplierDAO;

    private final ProductDAO productDAO;

    public PurchaseOrderServiceImpl(
            PurchaseOrderDAO purchaseOrderDAO,
            SupplierDAO supplierDAO,
            ProductDAO productDAO) {

        this.purchaseOrderDAO = purchaseOrderDAO;
        this.supplierDAO = supplierDAO;
        this.productDAO = productDAO;

    }

    @Override
    public PurchaseOrderDTO createPurchaseOrder(
            PurchaseOrderDTO dto) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Supplier supplier =
                    supplierDAO.findById(
                            em,
                            dto.getSupplierId());
            System.out.println("====================================");
            System.out.println("Supplier ID from DTO : " + dto.getSupplierId());
            System.out.println("Order Date : " + dto.getOrderDate());
            System.out.println("Items Count : " + dto.getItems().size());
            System.out.println("====================================");



            if (supplier == null) {

                throw new IllegalArgumentException(
                        "Supplier not found.");

            }

            PurchaseOrder purchaseOrder =
                    new PurchaseOrder();

            purchaseOrder.setSupplier(supplier);

            purchaseOrder.setStatus(
                    PurchaseOrderStatus.PENDING);

            purchaseOrder.setOrderDate(
                    dto.getOrderDate() == null
                            ? LocalDate.now()
                            : dto.getOrderDate());

            purchaseOrder.setExpectedDeliveryDate(
                    dto.getExpectedDeliveryDate());

            purchaseOrder.setNotes(
                    dto.getNotes());

            BigDecimal total =
                    BigDecimal.ZERO;

            for (PurchaseOrderItemDTO itemDTO :
                    dto.getItems()) {

                Product product =
                        productDAO.findById(
                                em,
                                itemDTO.getProductId());

                if (product == null) {

                    throw new IllegalArgumentException(
                            "Product not found : "
                                    + itemDTO.getProductId());

                }

                PurchaseOrderItem item =
                        new PurchaseOrderItem();

                item.setPurchaseOrder(
                        purchaseOrder);

                item.setProduct(product);

                item.setQuantity(
                        itemDTO.getQuantity());

                item.setUnitCost(
                        itemDTO.getUnitCost());

                purchaseOrder.getItems()
                        .add(item);

                total = total.add(
                        item.getLineTotal());

            }

            purchaseOrder.setTotalAmount(total);

            purchaseOrder.setCreatedBy("SYSTEM");

            purchaseOrder.setUpdatedBy("SYSTEM");

            purchaseOrderDAO.save(
                    em,
                    purchaseOrder);

            em.getTransaction().commit();

            return PurchaseOrderMapper.toDTO(
                    purchaseOrder);

        }

        catch (Exception e) {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();

            }

            throw e;

        }

        finally {

            em.close();

        }

    }

    @Override
    public PurchaseOrderDTO getPurchaseOrderById(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return PurchaseOrderMapper.toDTO(
                    purchaseOrderDAO.findById(em, id));

        } finally {

            em.close();

        }

    }

    @Override
    public List<PurchaseOrderDTO> getAllPurchaseOrders() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return purchaseOrderDAO
                    .findAllPurchaseOrders(em)
                    .stream()
                    .map(PurchaseOrderMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }

    }
    @Override
    public List<PurchaseOrderDTO> getPurchaseOrdersBySupplier(
            Long supplierId) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return purchaseOrderDAO
                    .findBySupplier(em, supplierId)
                    .stream()
                    .map(PurchaseOrderMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }

    }
    @Override
    public List<PurchaseOrderDTO> getPurchaseOrdersByStatus(
            PurchaseOrderStatus status) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return purchaseOrderDAO
                    .findByStatus(em, status)
                    .stream()
                    .map(PurchaseOrderMapper::toDTO)
                    .collect(Collectors.toList());

        } finally {

            em.close();

        }

    }
    @Override
    public PurchaseOrderDTO updatePurchaseOrderStatus(
            Long id,
            PurchaseOrderStatus status) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            PurchaseOrder purchaseOrder =
                    purchaseOrderDAO.findById(em, id);

            if (purchaseOrder == null) {

                throw new IllegalArgumentException(
                        "Purchase Order not found.");

            }

            /*
             * Prevent updating an already received order.
             */
            if (purchaseOrder.getStatus() ==
                    PurchaseOrderStatus.RECEIVED) {

                throw new IllegalArgumentException(
                        "Purchase Order is already received.");

            }

            /*
             * If the order is being received,
             * increase product stock.
             */
            if (status == PurchaseOrderStatus.RECEIVED) {

                for (PurchaseOrderItem item :
                        purchaseOrder.getItems()) {

                    Product product = item.getProduct();

                    product.setStockQuantity(

                            product.getStockQuantity()
                                    + item.getQuantity()

                    );

                    product.setUpdatedBy("SYSTEM");

                    productDAO.update(em, product);

                }

            }

            purchaseOrder.setStatus(status);

            purchaseOrder.setUpdatedBy("SYSTEM");

            purchaseOrderDAO.update(em, purchaseOrder);

            em.getTransaction().commit();

            return PurchaseOrderMapper.toDTO(purchaseOrder);

        }

        catch (Exception e) {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();

            }

            throw e;

        }

        finally {

            em.close();

        }

    }
    @Override
    public void deletePurchaseOrder(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            PurchaseOrder purchaseOrder =
                    purchaseOrderDAO.findById(em, id);

            if (purchaseOrder == null) {

                throw new IllegalArgumentException(
                        "Purchase Order not found.");

            }

            if (purchaseOrder.getStatus() ==
                    PurchaseOrderStatus.RECEIVED) {

                throw new IllegalArgumentException(
                        "Received Purchase Orders cannot be deleted.");

            }

            purchaseOrderDAO.delete(em, id);

            em.getTransaction().commit();

        }

        catch (Exception e) {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();

            }

            throw e;

        }

        finally {

            em.close();

        }

    }
}
