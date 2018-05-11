package com.magyart.random.service.service;

import com.magyart.random.model.UserEntity;

public interface UserService {

    void registerUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    UserEntity registered(String username);
    UserEntity loggedIn(String username, String password) throws Exception;
}
