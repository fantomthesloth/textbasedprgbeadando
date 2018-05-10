package com.magyart.random.model;

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
    private int xpGain = 25;
    private int goldGain = 5;

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
        this.level = 2;
        this.gold = 30;
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

    public int getCurrentXp() {
        return currentXp;
    }

    public int getLevel() {
        return level;
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

    public int getXpGain() {
        return xpGain;
    }

    public int getGoldGain() {
        return goldGain;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public int attack() {
        return random.nextInt(maxDamage - minDamage) + minDamage;
    }

    public void takeDamage(int dmg) {
        if (dmg >= currentHealth) {
            currentHealth = 0;
        } else {
            currentHealth = currentHealth - dmg;
        }
    }

    public void heal() {
        if (currentHealth > 0 && numberOfPotions > 0) {
            if (currentHealth == maxHealth) {
                currentHealth = maxHealth;
            }
            if ((maxHealth - currentHealth) <= (maxHealth * 0.4)) {
                currentHealth = maxHealth;
                numberOfPotions--;
            } else {
                currentHealth = (int) (currentHealth + Math.round(maxHealth * 0.4));
                numberOfPotions--;
            }
        }
    }

    public void leveling(Enemy enemy, Town town) {
        currentXp += xpGain;
        if (currentXp >= xpNeeded) {
            level++;
            xpNeeded = (int) Math.round(xpNeeded * 1.5);
            xpGain *= 1.25;
            maxHealth *= 1.3;
            currentHealth = maxHealth;
            minDamage *= 1.2;
            maxDamage *= 1.2;
            numberOfPotions = 5;
            goldGain = (int) Math.round(goldGain*1.6);

            town.setUpTown(this);

            enemy.setMaxHealth((int) Math.round(enemy.getMaxHealth() * Math.pow(1.2, (getLevel()-1))));
            enemy.setCurrentHealth(enemy.getMaxHealth());
            enemy.setMinDamage((int) Math.round(enemy.getMinDamage() * Math.pow(1.5, (getLevel()-1))));
            enemy.setMaxDamage((int) Math.round(enemy.getMaxDamage() * Math.pow(1.5, (getLevel()-1))));
        }
    }

}

