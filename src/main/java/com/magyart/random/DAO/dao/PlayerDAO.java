package com.magyart.random.DAO.dao;

public interface PlayerDAO<PlayerEntity, Long> {

    void persist(PlayerEntity entity);
    void update(PlayerEntity entity);
}
