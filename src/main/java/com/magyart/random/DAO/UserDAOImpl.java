package com.magyart.random.DAO;

import com.magyart.random.DAO.dao.UserDAO;
import com.magyart.random.model.UserEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * Class implementing {@link UserDAO} interface.
 */
@Slf4j
public class UserDAOImpl implements UserDAO {

    /**
     * Instance of {@link EntityManager}.
     */
    private EntityManager entityManager;

    /**
     * Constructor of the DAO.
     *
     * @param entityManager - Instance of {@link EntityManager}.
     */
    public UserDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    /**
     * Method to persist the user.
     *
     * @param o - An entity.
     */
    @Override
    public void persist(Object o) {
        if(o == null){
            throw new EntityNotFoundException("User not found!");
        }

        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();

    }

    /**
     * Method to update the data of the user.
     *
     * @param o - An entity.
     */
    @Override
    public void update(Object o) {

        if(o == null){
            throw new EntityNotFoundException("User not found!");
        }

        entityManager.getTransaction().begin();
        entityManager.merge(o);
        entityManager.getTransaction().commit();
    }

    /**
     * Returns an entity that's username equals to the username parameter.
     *
     * @param username - A username.
     * @return - Entity that's username equals to the username parameter.
     */
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

    /**
     * Returns an entity that's username and password equals to the username and password parameters.
     *
     * @param username - A username.
     * @param password - A password.
     * @return - Entity that's username and password equals to the username and password parameters.
     */
    public UserEntity loggedIn(String username, String password){
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
