package com.inventotrack.dao.impl;

import com.inventotrack.dao.CustomerOrderDAO;
import com.inventotrack.enums.OrderStatus;
import com.inventotrack.model.CustomerOrder;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CustomerOrderDAOImpl
        extends AbstractDAOImpl<CustomerOrder>
        implements CustomerOrderDAO {

    public CustomerOrderDAOImpl() {
        super(CustomerOrder.class);
    }

    @Override
    public List<CustomerOrder> findByUser(EntityManager em,
                                          Long userId) {

        return em.createQuery(
                        "SELECT o FROM CustomerOrder o WHERE o.user.id=:id ORDER BY o.createdAt DESC",
                        CustomerOrder.class)
                .setParameter("id", userId)
                .getResultList();
    }

    @Override
    public List<CustomerOrder> findAllOrders(EntityManager em) {

        return em.createQuery(
                        "SELECT o FROM CustomerOrder o ORDER BY o.createdAt DESC",
                        CustomerOrder.class)
                .getResultList();
    }

    @Override
    public List<CustomerOrder> findByStatus(EntityManager em,
                                            OrderStatus status) {

        return em.createQuery(
                        "SELECT o FROM CustomerOrder o WHERE o.status=:status",
                        CustomerOrder.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public BigDecimal calculateRevenue(EntityManager em) {

        BigDecimal revenue = em.createQuery(
                        "SELECT SUM(o.totalAmount) FROM CustomerOrder o WHERE o.status=:status",
                        BigDecimal.class)
                .setParameter("status", OrderStatus.COMPLETED)
                .getSingleResult();

        return revenue == null ? BigDecimal.ZERO : revenue;
    }

    @Override
    public Long countPendingOrders(EntityManager em) {

        return em.createQuery(
                        "SELECT COUNT(o) FROM CustomerOrder o WHERE o.status=:status",
                        Long.class)
                .setParameter("status", OrderStatus.PENDING)
                .getSingleResult();
    }
}