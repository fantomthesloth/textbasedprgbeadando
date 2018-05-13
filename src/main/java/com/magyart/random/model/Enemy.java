package com.magyart.random.model;

import lombok.Data;

import java.util.Random;

/**
 * Class representing an enemy.
 */
@Data
public class Enemy {

    private String name;
    private int maxHealth;
    private int currentHealth;
    private int maxDamage;
    private int minDamage;

    private Random random = new Random();


    /**
     * Constructor of the Enemy class.
     *
     */
    public Enemy() {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
    }

    /**
     * Method to set the stats of the enemy "Skeleton".
     *
     * @param enemy - Instance of the {@link Enemy}.
     */
    private void skeleton(Enemy enemy){
        enemy.setName("Skeleton");
        enemy.setMaxHealth(35);
        setCurrentHealth(getMaxHealth());
        setMinDamage(15);
        setMaxDamage(20);
    }

    /**
     * Method to set the stats of the enemy "Bandit".
     *
     * @param enemy - Instance of the {@link Enemy}.
     */
    public void bandit(Enemy enemy){
        enemy.setName("Bandit");
        enemy.setMaxHealth(40);
        setCurrentHealth(getMaxHealth());
        setMinDamage(20);
        setMaxDamage(25);
    }

    /**
     * Method to set the stats of the enemy "Bandit".
     *
     * @param enemy - Instance of the {@link Enemy}.
     */
    public void necromancer(Enemy enemy){
        enemy.setName("Necromancer");
        enemy.setMaxHealth(30);
        setCurrentHealth(getMaxHealth());
        setMinDamage(15);
        setMaxDamage(30);
    }

    /**
     * Method to give back a random enemy.
     *
     */
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

    /**
     * Method to check if the enemy is alive.
     *
     * @return - True if the enemy has at least 1 health.
     */
    public boolean isAlive(){
        return currentHealth > 0;
    }

    /**
     * Method to calculate the enemy's attack damage.
     *
     * @return - A random int value between the enemy's minimum and maximum damage.
     */
    public int attack(){
        return random.nextInt(maxDamage-minDamage) + minDamage;
    }

    /**
     * Method to calculate the enemy's health after it's taken damage.
     *
     * @param dmg - The damage value that the enemy received.
     */
    public void takeDamage(int dmg){
        if(dmg >= currentHealth){
            currentHealth = 0;
        }else{
            currentHealth = currentHealth - dmg;
        }
    }

}
