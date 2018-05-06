package com.magyart.random.sample;

import java.util.Random;

public class Player {
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int maxDamage;
    private int minDamage;
    private int numberOfPotions;
    private int xpNeeded;
    private int currentXp;
    private int level;
    private int gold;

    private Random random = new Random();

    public Player(String name, int maxHealth, int currentHealth, int maxDamage, int minDamage, int numberOfPotions, int xpNeeded, int currentXp, int level, int gold) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.numberOfPotions = numberOfPotions;
        this.xpNeeded = xpNeeded;
        this.currentXp = currentXp;
        this.level = level;
        this.gold = gold;
    }

    Player player = new Player("RandomPlayer",50,50,20,10,5,100,0,1,30);

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

    public int getNumberOfPotions() {
        return numberOfPotions;
    }

    public void setNumberOfPotions(int numberOfPotions) {
        this.numberOfPotions = numberOfPotions;
    }

    public boolean isAlive(){
        return currentHealth > 0;
    }

    public int attack(){
        return random.nextInt(maxDamage-minDamage) + minDamage;
    }

    public void takeDamage(Enemy enemy){
        int dmg = enemy.attack();
        if(dmg >= currentHealth){
            currentHealth = 0;
        }else{
            currentHealth = currentHealth - dmg;
        }
    }

}
