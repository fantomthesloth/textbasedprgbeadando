package com.magyart.random.service.service;

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
