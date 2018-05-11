package com.magyart.random.DB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Manager implements AutoCloseable {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("randomDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getInstance() {
        return entityManager;
    }


    @Override
    public void close(){
        if (entityManagerFactory != null){
            entityManager.close();
            entityManagerFactory.close();
            log.info("Databe closed!");
        }
    }

}
