package com.magyart.random.DAO;

import com.magyart.random.DAO.api.UserDAO;
import com.magyart.random.model.UserEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

@Slf4j
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public void persist(Object o) {
        if(o == null){
            throw new EntityNotFoundException("User not found!");
        }

        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();

    }

    @Override
    public void update(Object o) {

        if(o == null){
            throw new EntityNotFoundException("User not found!");
        }

        entityManager.getTransaction().begin();
        entityManager.merge(o);
        entityManager.getTransaction().commit();
    }

    public UserEntity loggedIn(String username, String password) throws Exception{
        if(username == null || password == null){
            throw new IllegalArgumentException("Username or password not given!");
        }
        try{
            Query query = entityManager.createQuery("select u from UserEntity ")
        }
    }
}
