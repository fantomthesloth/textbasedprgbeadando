package com.magyart.random.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Table(name="PLAYER")
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "PLAYER_NAME")
    private String name;

    @Column(name = "MAX_HP")
    private int maxHealth;

    @Column(name = "ACTUAL_HP")
    private int currentHealth;

    @Column(name = "MAX_DMG")
    private int maxDamage;

    @Column(name = "MIN_DMG")
    private int minDamage;

    @Column(name = "POT_NUM")
    private int numberOfPotions;

    @Column(name = "XP_NEEDED")
    private int xpNeeded;

    @Column(name = "ACTUAL_XP")
    private int currentXp;

    @Column(name = "LEVEL")
    private int level;

    @Column(name = "GOLD")
    private int gold;

    @Column(name = "XP_GAIN")
    private int xpGain;

    @Column(name= "GOLD_GAIN")
    private int goldGain;

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
