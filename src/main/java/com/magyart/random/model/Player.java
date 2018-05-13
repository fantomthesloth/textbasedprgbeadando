package com.magyart.random.model;

/*-
 * #%L
 * Random
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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

