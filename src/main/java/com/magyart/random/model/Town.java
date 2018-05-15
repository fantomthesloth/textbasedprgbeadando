package com.magyart.random.model;

/*-
 * #%L
 * Random
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
     * Constructor of the Town class.
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
