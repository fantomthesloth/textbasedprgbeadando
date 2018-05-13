package com.magyart.random.DB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.extern.slf4j.Slf4j;

/**
 * A singleton class the manages the database.
 */
@Slf4j
public class Manager implements AutoCloseable {

    /**
     * Instance of {@link EntityManagerFactory}.
     */
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Instance of {@link EntityManager}.
     */
    private static EntityManager entityManager;

    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("randomDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Gets an instance of {@link EntityManager}.
     *
     * @return - An instance of {@link EntityManager}.
     */
    public static EntityManager getInstance() {
        return entityManager;
    }


    /**
     * Method to close the database.
     */
    @Override
    public void close(){
        if (entityManagerFactory != null){
            entityManager.close();
            entityManagerFactory.close();
            log.info("Databe closed!");
        }
    }

}
