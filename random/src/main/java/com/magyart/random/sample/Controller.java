package com.magyart.random.sample;

public class Controller {

    public void playerHeal(Player player){
        if (player.getCurrentHealth() > 0){
            if (player.getCurrentHealth() == player.getMaxHealth() || (player.getMaxHealth() - player.getCurrentHealth()) <=20){
                player.setCurrentHealth(player.getMaxHealth());
            }else{
                player.setCurrentHealth(player.getCurrentHealth() + 20);
                player.setNumberOfPotions(player.getNumberOfPotions()- 1);
            }
        }
    }


}
