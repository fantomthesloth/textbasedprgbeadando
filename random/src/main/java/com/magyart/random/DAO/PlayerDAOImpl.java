package com.magyart.random.DAO;

import com.magyart.random.DAO.api.PlayerDAO;

import javax.persistence.EntityManager;

public class PlayerDAOImpl implements PlayerDAO {
    private EntityManager entityManager;

    public PlayerDAOImpl (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void persist(Object o) {
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Object o) {
        entityManager.getTransaction().begin();
        entityManager.merge(o);
        entityManager.getTransaction().commit();
    }
}
