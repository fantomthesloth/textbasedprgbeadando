package com.magyart.random.DB;

/*-
 * #%L
 * Random
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
