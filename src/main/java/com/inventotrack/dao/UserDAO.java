package com.inventotrack.dao;

import com.inventotrack.model.User;
import jakarta.persistence.EntityManager;

public interface UserDAO extends BaseDAO<User> {

    User findByUsername(EntityManager em, String username);

    User findByEmail(EntityManager em, String email);

}