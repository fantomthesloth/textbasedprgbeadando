package com.magyart.random.DAO.dao;

public interface UserDAO<UserEntity, Long> {

    void persist(UserEntity entity);
    void update(UserEntity entity);

}
