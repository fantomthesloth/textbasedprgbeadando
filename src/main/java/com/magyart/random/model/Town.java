package com.magyart.random.model;

import com.magyart.random.DAO.UserDAOImpl;
import com.magyart.random.DB.Manager;
import com.magyart.random.service.UserServiceImpl;
import com.magyart.random.service.service.UserService;

public class Town {
    private int healUp;
    private int refillPotions;
    private int upgradeWeapon;

    private UserEntity userEntity;
    private UserService userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));

    public Town() {
        this.healUp = 10;
        this.refillPotions = 15;
        this.upgradeWeapon = 20;
    }

    public int getHealUp() {
        return healUp;
    }

    public void setHealUp(int healUp) {
        this.healUp = healUp;
    }

    public int getRefillPotions() {
        return refillPotions;
    }

    public void setRefillPotions(int refillPotions) {
        this.refillPotions = refillPotions;
    }

    public int getUpgradeWeapon() {
        return upgradeWeapon;
    }

    public void healUp(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getGold() >= healUp) {
            userEntity.getPlayerEntity().setCurrentHealth(userEntity.getPlayerEntity().getMaxHealth());
            userEntity.getPlayerEntity().setGold(userEntity.getPlayerEntity().getGold() - healUp);
        }
    }

    public void refillPotions(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getGold() >= refillPotions) {
            userEntity.getPlayerEntity().setNumberOfPotions(5);
            userEntity.getPlayerEntity().setGold(userEntity.getPlayerEntity().getGold() - refillPotions);
        }
    }

    public void upgradeWeapon(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getGold() >= upgradeWeapon) {
            userEntity.getPlayerEntity().setMinDamage((int) Math.round(userEntity.getPlayerEntity().getMinDamage() * 1.3));
            userEntity.getPlayerEntity().setMaxDamage((int) Math.round(userEntity.getPlayerEntity().getMaxDamage() * 1.3));
            userEntity.getPlayerEntity().setGold(userEntity.getPlayerEntity().getGold() - upgradeWeapon);
            upgradeWeapon += 10;
        }
    }

    public void setUpTown(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getLevel() > 1){
            setHealUp((10 + (5 * (userEntity.getPlayerEntity().getLevel() - 1))));
            setRefillPotions((15 + (7 * (userEntity.getPlayerEntity().getLevel() - 1))));
        }
    }
}
