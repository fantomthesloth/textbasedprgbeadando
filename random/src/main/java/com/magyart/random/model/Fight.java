package com.magyart.random.model;

public class Fight {
    int playerDamage;
    int enemyDamage;

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
            if(enemy.isAlive()) {
                player.takeDamage(enemyDamage);
            }else {
                player.leveling(enemy, town);
                player.setGold(player.getGold() + player.getGoldGain());
            }
        }
    }
}
