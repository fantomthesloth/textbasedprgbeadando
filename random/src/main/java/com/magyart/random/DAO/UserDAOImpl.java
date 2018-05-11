package com.magyart.random.DAO;

import com.magyart.random.DAO.api.UserDAO;
import com.magyart.random.model.UserEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

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

    public UserEntity registered(String username){
        if (username == null){
            throw new IllegalArgumentException("No username found!");
        }

        try{
            Query query = entityManager.createQuery("select user from UserEntity user where lower(user.username) = lower(:username)");

            query.setParameter("username", username);

            return (UserEntity) query.getSingleResult();
        }catch (NoResultException exception){
            log.error("No result found!");
            return null;

        }catch (PersistenceException exception){
            log.error("Query error!");
            return null;
        }
    }

    public UserEntity loggedIn(String username, String password) throws Exception{
        if(username == null || password == null){
            throw new IllegalArgumentException("Username or password not given!");
        }
        try{
            Query query = entityManager.createQuery("select user from UserEntity user where user.username = :username and user.password = :password");

            query.setParameter("username", username);
            query.setParameter("password", password);

            return (UserEntity) query.getSingleResult();
        }catch (NoResultException exception){
            log.error("No result found!");
            return null;
            
        }catch (PersistenceException exception){
            log.error("Query error!");
            return null;
        }
    }
}
