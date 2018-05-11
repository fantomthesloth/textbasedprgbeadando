package com.magyart.random.model;

import com.magyart.random.DAO.UserDAOImpl;
import com.magyart.random.DB.Manager;
import com.magyart.random.service.UserServiceImpl;
import com.magyart.random.service.service.UserService;

public class Fight {
    private int playerDamage;
    private int enemyDamage;

    private UserEntity userEntity;
    private UserService userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));

    public int getPlayerDamage() {
        return playerDamage;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }

    public int getEnemyDamage() {
        return enemyDamage;
    }

    public void setEnemyDamage(int enemyDamage) {
        this.enemyDamage = enemyDamage;
    }

    public void fight(Player player, Enemy enemy, Town town){
        setPlayerDamage(player.attack());
        setEnemyDamage(enemy.attack());
        if(player.isAlive() && enemy.isAlive()) {
            enemy.takeDamage(playerDamage);
            if (enemy.isAlive()) {
                playerTakeDamage(enemyDamage);
            } else {
                leveling(enemy, town);
                userEntity.getPlayerEntity().setGold(userEntity.getPlayerEntity().getGold() + userEntity.getPlayerEntity().getGoldGain());
            }
        }
    }

    private void playerTakeDamage(int dmg) {
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if (dmg >= userEntity.getPlayerEntity().getCurrentHealth()) {

            userEntity.getPlayerEntity().setCurrentHealth(0);
        } else {
            userEntity.getPlayerEntity().setCurrentHealth(userEntity.getPlayerEntity().getCurrentHealth() - dmg);
        }
    }

    private void leveling(Enemy enemy, Town town) {
        userEntity = userService.registered(UserServiceImpl.getUsername());

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

    public void heal() {
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if (userEntity.getPlayerEntity().getCurrentHealth() > 0 && userEntity.getPlayerEntity().getNumberOfPotions() > 0) {
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
