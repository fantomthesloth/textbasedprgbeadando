package com.magyart.random.service;

import com.magyart.random.DAO.UserDAOImpl;
import com.magyart.random.model.UserEntity;
import com.magyart.random.service.api.UserService;

public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO;

    private static String username;

    public UserServiceImpl(UserDAOImpl userDAO){
        this.userDAO = userDAO;
    }

    public static String getUsername() {
        return UserServiceImpl.username;
    }

    public static void setUsername(String username) {
        UserServiceImpl.username = username;
    }

    @Override
    public void registerUser(UserEntity userEntity) {
        userDAO.persist(userEntity);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userDAO.update(userEntity);
    }

    @Override
    public UserEntity registered(String username) {
        return userDAO.registered(username);
    }

    @Override
    public UserEntity loggedIn(String username, String password) throws Exception {
        return userDAO.loggedIn(username,password);
    }
}
