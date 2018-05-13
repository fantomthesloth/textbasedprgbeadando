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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * Class that generates the PLAYER table.
 */
@Slf4j
@Entity
@Table(name="PLAYER")
@Data
@NoArgsConstructor
public class PlayerEntity {


    /**
     * Primary key of the entity, automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false, updatable = false)
    private Long id;

    /**
     * Player's name.
     */
    @Column(name = "PLAYER_NAME")
    private String name;

    /**
     * Player's max health.
     */
    @Column(name = "MAX_HP")
    private int maxHealth;

    /**
     * Player's current health.
     */
    @Column(name = "ACTUAL_HP")
    private int currentHealth;

    /**
     * Player's max damage.
     */
    @Column(name = "MAX_DMG")
    private int maxDamage;

    /**
     * Player's min damage
     */
    @Column(name = "MIN_DMG")
    private int minDamage;

    /**
     * Number of the potions the player can hold.
     */
    @Column(name = "POT_NUM")
    private int numberOfPotions;

    /**
     * Xp needed for the player to level up.
     */
    @Column(name = "XP_NEEDED")
    private int xpNeeded;

    /**
     * Player's current xp.
     */
    @Column(name = "ACTUAL_XP")
    private int currentXp;

    /**
     * Player's level.
     */
    @Column(name = "LEVEL")
    private int level;

    /**
     * Player's gold.
     */
    @Column(name = "GOLD")
    private int gold;

    /**
     * The amount of xp the player gets.
     */
    @Column(name = "XP_GAIN")
    private int xpGain;

    /**
     * The amount of gold the player gets.
     */
    @Column(name= "GOLD_GAIN")
    private int goldGain;

    /**
     * Constructor of this class.
     *
     * @param player - Instance of {@link Player}.
     */
    public PlayerEntity(Player player){
        this.name = player.getName();
        this.maxHealth = player.getMaxHealth();
        this.currentHealth = player.getCurrentHealth();
        this.maxDamage = player.getMaxDamage();
        this.minDamage = player.getMinDamage();
        this.numberOfPotions = player.getNumberOfPotions();
        this.xpNeeded = player.getXpNeeded();
        this.currentXp = player.getCurrentXp();
        this.level = player.getLevel();
        this.gold = player.getGold();
        this.xpGain = player.getXpGain();
        this.goldGain = player.getGoldGain();
    }


}
