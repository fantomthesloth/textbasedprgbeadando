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

    public Player() {
        this.name = name;
        this.maxHealth = 50;
        this.currentHealth = 50;
        this.maxDamage = 20;
        this.minDamage = 15;
        this.numberOfPotions = 5;
        this.xpNeeded = 100;
        this.currentXp = 0;
        this.level = 1;
        this.gold = 30;
    }

    //Player player = new Player("RandomPlayer",50,50,20,10,5,100,0,1,30);

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

    public int getXpNeeded() {
        return xpNeeded;
    }

    public void setXpNeeded(int xpNeeded) {
        this.xpNeeded = xpNeeded;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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

    public void heal(){
        if (currentHealth > 0 && numberOfPotions > 0){
            if (currentHealth == maxHealth){}
            if ((maxHealth - currentHealth) <=20){
                currentHealth = maxHealth;
                numberOfPotions--;
            }else{
                currentHealth = currentHealth + 20;
                numberOfPotions--;
            }
        }
    }
}