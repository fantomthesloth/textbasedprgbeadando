package com.magyart.random.service.service;

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

import com.magyart.random.model.UserEntity;

/**
 * Interface of the user's services.
 */
public interface UserService {

    /**
     * Method to register a user.
     *
     * @param userEntity - Entity of the user.
     */
    void registerUser(UserEntity userEntity);

    /**
     * Method to update the user's data.
     *
     * @param userEntity - Entity of the user.
     */
    void updateUser(UserEntity userEntity);

    /**
     * Returns the entity that's username equals to the username given in the parameter.
     *
     * @param username - A username.
     * @return - An entity that's username equals to the parameter.
     */
    UserEntity registered(String username);


    /**
     * Returns the entity that's username and password equals to the username and password given in the parameter.
     *
     * @param username - A username.
     * @param password - A password.
     * @return - An entity that's username and password equals to the parameter.
     * @throws Exception
     */
    UserEntity loggedIn(String username, String password) throws Exception;
}
