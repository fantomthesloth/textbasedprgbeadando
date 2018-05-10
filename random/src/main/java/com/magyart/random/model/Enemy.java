package com.magyart.random.model;

import java.util.Random;

public class Enemy {

    private String name;
    private int maxHealth;
    private int currentHealth;
    private int maxDamage;
    private int minDamage;


    private Random random = new Random();


    public Enemy() {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = 30;
        this.maxDamage = 10;
        this.minDamage = 5;
    }


    public void skeleton(Enemy enemy){
        enemy.setName("Skeleton");
        enemy.setMaxHealth(37);
        setCurrentHealth(getMaxHealth());
        setMinDamage(getMinDamage());
        setMaxDamage(getMaxDamage());
    }
    public void bandit(Enemy enemy){
        enemy.setName("Bandit");
        enemy.setMaxHealth(40);
        setCurrentHealth(getMaxHealth());
        setMinDamage(getMinDamage());
        setMaxDamage(getMaxDamage());
    }
    public void necromancer(Enemy enemy){
        enemy.setName("Necromancer");
        enemy.setMaxHealth(30);
        setCurrentHealth(getMaxHealth());
        setMinDamage(getMinDamage());
        setMaxDamage(getMaxDamage());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = maxHealth;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public void randomEnemy(){
        int enemyTypes = 3;
        int i = random.nextInt(enemyTypes);

        if (i == 0){
            skeleton(this);
        }
        if (i == 1){
            bandit(this);
        }
        if (i == 2){
            necromancer(this);
        }
    }

    public boolean isAlive(){
        return currentHealth > 0;
    }

    public int attack(){
        return random.nextInt(maxDamage-minDamage) + minDamage;
    }

    public void setUpEnemy(Player player, Enemy enemy){

        if(player.getLevel() > 1){
            enemy.setMaxHealth((int) Math.round(enemy.getMaxHealth() * Math.pow(1.5, (player.getLevel()-1))));
            enemy.setCurrentHealth(enemy.getMaxHealth());
            enemy.setMinDamage((int) Math.round(enemy.getMinDamage() * Math.pow(1.4, (player.getLevel()-1))));
            enemy.setMaxDamage((int) Math.round(enemy.getMaxDamage() * Math.pow(1.4, (player.getLevel()-1))));
        }
    }

    public void takeDamage(int dmg){
        if(dmg >= currentHealth){
            currentHealth = 0;
        }else{
            currentHealth = currentHealth - dmg;
        }
    }

}
