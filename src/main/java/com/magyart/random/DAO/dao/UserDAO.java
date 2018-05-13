package com.magyart.random.DAO.dao;

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
