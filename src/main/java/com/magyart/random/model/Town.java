package com.magyart.random.model;

import com.magyart.random.DAO.UserDAOImpl;
import com.magyart.random.DB.Manager;
import com.magyart.random.service.UserServiceImpl;
import com.magyart.random.service.service.UserService;
import lombok.Data;

/**
 * Class representing the Town and it's functions in the game.
 */
@Data
public class Town {
    private UserService userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
    private UserEntity userEntity = userService.registered(UserServiceImpl.getUsername());

    private int healUp;
    private int refillPotions;
    private int upgradeWeapon;

    /**
     * Constructor of the Town class
     */
    public Town() {
        this.healUp = 10;
        this.refillPotions = 15;
        this.upgradeWeapon = 20;
    }

    /**
     * Method to heal up the player to max health.
     */
    public void healUp(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getGold() >= healUp) {
            userEntity.getPlayerEntity().setCurrentHealth(userEntity.getPlayerEntity().getMaxHealth());
            userEntity.getPlayerEntity().setGold(userEntity.getPlayerEntity().getGold() - healUp);
        }
    }

    /**
     * Method to refill the player's potions.
     */
    public void refillPotions(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getGold() >= refillPotions) {
            userEntity.getPlayerEntity().setNumberOfPotions(5);
            userEntity.getPlayerEntity().setGold(userEntity.getPlayerEntity().getGold() - refillPotions);
        }
    }

    /**
     * Method to upgrade the player's weapon.
     */
    public void upgradeWeapon(){


        if(userEntity.getPlayerEntity().getGold() >= upgradeWeapon) {
            userEntity.getPlayerEntity().setMinDamage((int) Math.round(userEntity.getPlayerEntity().getMinDamage() * 1.3));
            userEntity.getPlayerEntity().setMaxDamage((int) Math.round(userEntity.getPlayerEntity().getMaxDamage() * 1.3));
            userEntity.getPlayerEntity().setGold(userEntity.getPlayerEntity().getGold() - upgradeWeapon);
            upgradeWeapon += 10;
        }
    }

    /**
     * Method to set up the town according to the player's level.
     */
    public void setUpTown(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getLevel() > 1){
            setHealUp((10 + (5 * (userEntity.getPlayerEntity().getLevel() - 1))));
            setRefillPotions((15 + (7 * (userEntity.getPlayerEntity().getLevel() - 1))));
        }
    }
}
