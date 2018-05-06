package com.magyart.random.sample;

import java.util.Random;

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

    public static Enemy skeleton(){
        return new Enemy("Skeleton",30, 30,15,10);
    }
    public static Enemy bandit(){
        return new Enemy("Bandit",35, 40,25,15);
    }
    public static Enemy necromancer(){
        return  new Enemy("Necromancer",15, 15,30,20);
    }

    public void randomEnemy(){
        int enemyTypes = 3;
        int i = random.nextInt(enemyTypes);

        if (i == 0){
            skeleton();
        }
        if (i == 1){
            bandit();
        }
        if (i == 2){
            necromancer();
        }
    }

    public boolean isAlive(){
        return currentHealth > 0;
    }

    public int attack(){
        return random.nextInt(maxDamage-minDamage) + minDamage;
    }

    public void takeDamage(Player player){
        int dmg = player.attack();
        if(dmg >= currentHealth){
            currentHealth = 0;
        }else{
            currentHealth = currentHealth - dmg;
        }
    }


}
