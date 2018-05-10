package com.magyart.random.DAO.api;

public interface UserDAO<UserEntity, Long> {

    void persist(UserEntity entity);
    void update(UserEntity entity);

}
