package com.magyart.random.sample;

public class Town {
    private int healUp;
    private int refillPotions;
    private int upgradeWeapon;

    Town() {
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

    public void setUpgradeWeapon(int upgradeWeapon) {
        this.upgradeWeapon = upgradeWeapon;
    }

    public void healUp(Player player){
        player.setCurrentHealth(player.getMaxHealth());
        player.setGold(player.getGold() - healUp);
        if(player.getGold() < healUp){
            return;
        }
    }

    public void refillPotions(Player player){
        player.setNumberOfPotions(5);
        player.setGold(player.getGold() - refillPotions);
        if(player.getGold() < refillPotions){
            return;
        }
    }

    public void upgradeWeapon(Player player){
        player.setMinDamage((int) Math.round(player.getMinDamage() * 1.3));
        player.setMaxDamage((int) Math.round(player.getMaxDamage() * 1.3));
        player.setGold(player.getGold() - upgradeWeapon);
        if(player.getGold() < upgradeWeapon){
            return;
        }
    }
}
