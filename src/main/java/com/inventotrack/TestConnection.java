package com.inventotrack;

import com.inventotrack.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class TestConnection {

    public static void main(String[] args) {

        EntityManager em = null;

        try {

            em = JPAUtil.getEntityManager();

            System.out.println("====================================");
            System.out.println("DATABASE CONNECTED SUCCESSFULLY");
            System.out.println("====================================");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (em != null) {
                em.close();
            }

            JPAUtil.close();

        }

    }

}