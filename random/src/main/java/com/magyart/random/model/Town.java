package com.magyart.random.model;

public class Town {
    private int healUp;
    private int refillPotions;
    private int upgradeWeapon;

    public Town() {
        this.healUp = 10;
        this.refillPotions = 15;
        this.upgradeWeapon = 20;
    }

    public int getHealUp() {
        return healUp;
    }

    public void setHealUp(int healUp) {
        this.healUp = healUp;
    }

    public int getRefillPotions() {
        return refillPotions;
    }

    public void setRefillPotions(int refillPotions) {
        this.refillPotions = refillPotions;
    }

    public int getUpgradeWeapon() {
        return upgradeWeapon;
    }

    public void healUp(Player player){
        if(player.getGold() >= healUp) {
            player.setCurrentHealth(player.getMaxHealth());
            player.setGold(player.getGold() - healUp);
        }
    }

    public void refillPotions(Player player){
        if(player.getGold() >= refillPotions) {
            player.setNumberOfPotions(5);
            player.setGold(player.getGold() - refillPotions);
        }
    }

    public void upgradeWeapon(Player player){
        if(player.getGold() >= upgradeWeapon) {
            player.setMinDamage((int) Math.round(player.getMinDamage() * 1.4));
            player.setMaxDamage((int) Math.round(player.getMaxDamage() * 1.4));
            player.setGold(player.getGold() - upgradeWeapon);
            upgradeWeapon += 10;
        }
    }

    public void setUpTown(Player player){
        if(player.getLevel() > 1){
            setHealUp((10 + (5 * (player.getLevel()-1))));
            setRefillPotions((15 + (7 * (player.getLevel() - 1))));
        }
    }
}
