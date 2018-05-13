package com.magyart.random.DAO.dao;

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

/**
 * Interface for the user DAO methods.
 *
 * @param <UserEntity> - The class that it's used on.
 * @param <Long> - The class' id.
 */
public interface UserDAO<UserEntity, Long> {

    /**
     * Method for persisting an entity.
     *
     * @param entity - An entity.
     */
    void persist(UserEntity entity);

    /**
     * Method for updating the data of of the entity.
     *
     * @param entity - An entity.
     */
    void update(UserEntity entity);

}
