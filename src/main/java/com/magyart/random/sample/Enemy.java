package com.magyart.random.sample;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Enemy {
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int maxDamage;
    private int minDamage;

    private Random random = new Random();


    public Enemy(String name, int maxHealth, int currentHealth, int maxDamage, int minDamage) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
    }

    public Enemy randomEnemy(){
        int enemyTypes = 3;
        int i = random.nextInt(enemyTypes);

        Enemy enemy = new Enemy();

        if (i == 0){
            return new Enemy("Skeleton",30, 30,15,10);
        }
        if (i == 1){
            return new Enemy("Bandit",35, 40,25,15);
        }
        if (i == 2){
            return  new Enemy("Necromancer",15, 15,30,20);
        }
        return enemy;
    }


    public int attack(){
        return random.nextInt(maxDamage-minDamage) + minDamage;
    }

    public boolean isAlive(){
        return currentHealth > 0;
    }



}
