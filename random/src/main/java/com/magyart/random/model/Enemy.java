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
        this.currentHealth = maxHealth;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
    }

    public void skeleton(Enemy enemy){
        enemy.setName("Skeleton");
        enemy.setMaxHealth(35);
        setCurrentHealth(getMaxHealth());
        setMinDamage(15);
        setMaxDamage(20);
    }
    public void bandit(Enemy enemy){
        enemy.setName("Bandit");
        enemy.setMaxHealth(40);
        setCurrentHealth(getMaxHealth());
        setMinDamage(20);
        setMaxDamage(25);
    }
    public void necromancer(Enemy enemy){
        enemy.setName("Necromancer");
        enemy.setMaxHealth(30);
        setCurrentHealth(getMaxHealth());
        setMinDamage(15);
        setMaxDamage(30);
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


    public void takeDamage(int dmg){
        if(dmg >= currentHealth){
            currentHealth = 0;
        }else{
            currentHealth = currentHealth - dmg;
        }
    }

}
