package com.magyart.random.service;

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

import com.magyart.random.DAO.UserDAOImpl;
import com.magyart.random.model.UserEntity;
import com.magyart.random.service.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * Class implementing {@link UserService} interface.
 */
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * Instance of {@link com.magyart.random.DAO.UserDAOImpl}.
     */
    private UserDAOImpl userDAO;

    /**
     * Static string containing the username.
     */
    private static String username;

    /**
     * Constructor of the service.
     *
     * @param userDAO - Instance of {@link com.magyart.random.DAO.UserDAOImpl}.
     */
    public UserServiceImpl(UserDAOImpl userDAO){
        this.userDAO = userDAO;
    }

    /**
     * Gets the username.
     *
     * @return - A username.
     */
    public static String getUsername() {
        return UserServiceImpl.username;
    }

    /**
     * Sets the username.
     *
     * @param username - A username.
     */
    public static void setUsername(String username) {
        UserServiceImpl.username = username;
    }

    /**
     * Registers a new user.
     *
     * @param userEntity - Entity of the user.
     */
    @Override
    public void registerUser(UserEntity userEntity) {
        userDAO.persist(userEntity);
        log.info("User registered!");
    }

    /**
     * Updates the data of a user.
     *
     * @param userEntity - Entity of the user.
     */
    @Override
    public void updateUser(UserEntity userEntity) {
        userDAO.update(userEntity);
    }

    /**
     *  Checks if a user is already registered .
     *
     * @param username - A username.
     * @return - An entity if it has been registered.
     */
    @Override
    public UserEntity registered(String username) {
        return userDAO.registered(username);
    }

    /**
     *  Checks if a user and password exists.
     *
     * @param username - A username.
     * @param password - A password.
     * @return - An entity if it exists.
     */
    @Override
    public UserEntity loggedIn(String username, String password){
        return userDAO.loggedIn(username,password);
    }
}
