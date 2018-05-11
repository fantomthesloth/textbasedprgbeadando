package com.magyart.random.DAO.api;

public interface PlayerDAO<PlayerEntity, Long> {

    void persist(PlayerEntity entity);
    void update(PlayerEntity entity);
}
