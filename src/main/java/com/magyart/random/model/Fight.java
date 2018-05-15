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

import java.util.Random;

/**
 * Class representing the fight option in the game.
 */
@Data
public class Fight {
    private UserService userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
    private UserEntity userEntity  = userService.registered(UserServiceImpl.getUsername());

    private int playerDamage;
    private int enemyDamage;
    private Random random = new Random();

    /**
     * Method for fighting.
     * Changes the player's and enemy's health according to their damage value.
     * Calculates player's, enemy's and town's progress after each enemy defeated.
     *
     * @param enemy - Instance of {@link Enemy}.
     * @param town - Instance of {@link Town}.
     */
    public void fight(Enemy enemy, Town town) {
        playerDamage = playerAttack();
        enemyDamage = enemy.attack();

        if (playerIsAlive() && enemy.isAlive()) {
            enemy.takeDamage(playerDamage);
            if (enemy.isAlive()) {
                playerTakeDamage(enemyDamage);
            } else {
                leveling(enemy, town);
                userEntity.getPlayerEntity().setGold(userEntity.getPlayerEntity().getGold() + userEntity.getPlayerEntity().getGoldGain());
            }
        }
    }

    /**
     * Method to calculate the player's attack damage.
     *
     * @return - A random int value between the player's minimum and maximum damage.
     */
    private int playerAttack() {
        return random.nextInt(userEntity.getPlayerEntity().getMaxDamage() - userEntity.getPlayerEntity().getMinDamage()) + userEntity.getPlayerEntity().getMinDamage();
    }

    /**
     * Method to calculate the player's health after it's taken damage.
     *
     * @param dmg - The damage value that the player received.
     */
    private void playerTakeDamage(int dmg) {
        if (dmg >= userEntity.getPlayerEntity().getCurrentHealth()) {

            userEntity.getPlayerEntity().setCurrentHealth(0);
        } else {
            userEntity.getPlayerEntity().setCurrentHealth(userEntity.getPlayerEntity().getCurrentHealth() - dmg);
        }
    }

    /**
     * Method to calculate the player's, enemy's and town's stats after the player has leveled up.
     *
     * @param enemy - Instance of {@link Enemy}.
     * @param town - Instance of {@link Town}.
     */
    private void leveling(Enemy enemy, Town town) {
        userEntity.getPlayerEntity().setCurrentXp(userEntity.getPlayerEntity().getCurrentXp() + userEntity.getPlayerEntity().getXpGain());

        if (userEntity.getPlayerEntity().getCurrentXp() >= userEntity.getPlayerEntity().getXpNeeded()) {

            userEntity.getPlayerEntity().setLevel(userEntity.getPlayerEntity().getLevel() + 1);
            userEntity.getPlayerEntity().setXpNeeded((int) Math.round(userEntity.getPlayerEntity().getXpNeeded() * 1.5));
            userEntity.getPlayerEntity().setXpGain((int) Math.round(userEntity.getPlayerEntity().getXpGain() * 1.25));
            userEntity.getPlayerEntity().setMaxHealth((int) Math.round(userEntity.getPlayerEntity().getMaxHealth() * 1.3));
            userEntity.getPlayerEntity().setCurrentHealth(userEntity.getPlayerEntity().getMaxHealth());
            userEntity.getPlayerEntity().setMinDamage((int) Math.round(userEntity.getPlayerEntity().getMinDamage() * 1.2));
            userEntity.getPlayerEntity().setMaxDamage((int) Math.round(userEntity.getPlayerEntity().getMaxDamage() * 1.2));
            userEntity.getPlayerEntity().setNumberOfPotions(5);
            userEntity.getPlayerEntity().setGoldGain((int) Math.round(userEntity.getPlayerEntity().getGoldGain() * 1.6));

            town.setUpTown();

            enemy.setMaxHealth((int) Math.round(enemy.getMaxHealth() * Math.pow(1.2, (userEntity.getPlayerEntity().getLevel() - 1))));
            enemy.setCurrentHealth(enemy.getMaxHealth());
            enemy.setMinDamage((int) Math.round(enemy.getMinDamage() * Math.pow(1.5, (userEntity.getPlayerEntity().getLevel() - 1))));
            enemy.setMaxDamage((int) Math.round(enemy.getMaxDamage() * Math.pow(1.5, (userEntity.getPlayerEntity().getLevel() - 1))));
        }
    }

    /**
     * Checks if the player is alive.
     *
     * @return - True if the player has at least 1 health.
     */
    public boolean playerIsAlive() {
        return userEntity.getPlayerEntity().getCurrentHealth() > 0;
    }

    /**
     * Heals the player. Changes the player's health according to their current health and number of potions.
     */
    public void heal() {
        if (playerIsAlive() && userEntity.getPlayerEntity().getNumberOfPotions() > 0) {
            if (userEntity.getPlayerEntity().getCurrentHealth() == userEntity.getPlayerEntity().getMaxHealth()) {
                userEntity.getPlayerEntity().setCurrentHealth(userEntity.getPlayerEntity().getMaxHealth());
            }
            if ((userEntity.getPlayerEntity().getMaxHealth() - userEntity.getPlayerEntity().getCurrentHealth()) <= (userEntity.getPlayerEntity().getMaxHealth() * 0.4)) {
                userEntity.getPlayerEntity().setCurrentHealth(userEntity.getPlayerEntity().getMaxHealth());
                userEntity.getPlayerEntity().setNumberOfPotions(userEntity.getPlayerEntity().getNumberOfPotions() - 1);

            } else {
                userEntity.getPlayerEntity().setCurrentHealth((int) (userEntity.getPlayerEntity().getCurrentHealth() + Math.round(userEntity.getPlayerEntity().getMaxHealth() * 0.4)));
                userEntity.getPlayerEntity().setNumberOfPotions(userEntity.getPlayerEntity().getNumberOfPotions() - 1);
            }
        }
    }
}
