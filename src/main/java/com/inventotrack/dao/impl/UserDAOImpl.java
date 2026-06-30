package com.inventotrack.dao.impl;

import com.inventotrack.dao.UserDAO;
import com.inventotrack.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserDAOImpl extends AbstractDAOImpl<User>
        implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(EntityManager em, String username) {

        try {

            return em.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username",
                            User.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

    @Override
    public User findByEmail(EntityManager em, String email) {

        try {

            return em.createQuery(
                            "SELECT u FROM User u WHERE u.email = :email",
                            User.class)
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

}