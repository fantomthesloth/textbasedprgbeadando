package com.magyart.random.model;

import lombok.Data;

/**
 * Class representing the player's default stats.
 */
@Data
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

    /**
     * Constructor of the Player class.
     *
     */
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
}

