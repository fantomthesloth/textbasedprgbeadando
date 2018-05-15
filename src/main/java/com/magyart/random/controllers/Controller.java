package com.magyart.random.controllers;

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

import com.magyart.random.DAO.UserDAOImpl;
import com.magyart.random.DB.Manager;
import com.magyart.random.model.*;
import com.magyart.random.service.UserServiceImpl;
import com.magyart.random.service.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField namer;
    @FXML
    public TextArea playerStatTA;
    @FXML
    public Button gameStarter;
    @FXML
    public Button healBtn;
    @FXML
    public Button attackBtn;
    @FXML
    public TextArea logFeed;
    @FXML
    public Button keepGoing;
    @FXML
    public Button toTownBtn;
    @FXML
    public Button townHeal;
    @FXML
    public Button townRefill;
    @FXML
    public Button townUpgrade;
    @FXML
    public Button toDungeon;
    @FXML
    public Button restartBtn;
    @FXML
    public Label welcome1;
    @FXML
    public Label welcome2;
    @FXML
    public ImageView diedGif;

    private String playerName;

    private Player player = new Player();
    private Enemy enemy = new Enemy();
    private Town town = new Town();
    private Fight fight = new Fight();

    private UserEntity userEntity;
    private UserService userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));

    double x, y = 0;

    public void pressed(MouseEvent mouseEvent) {
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }

    public void dragged(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(mouseEvent.getScreenX()-x);
        stage.setY(mouseEvent.getScreenY()-y);
    }

    public void closeApp(ActionEvent actionEvent) {
        userEntity = userService.registered(UserServiceImpl.getUsername());

        userService.updateUser(userEntity);
        Manager.getInstance().close();
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userEntity = userService.registered(UserServiceImpl.getUsername());

        playerStatTA.setVisible(false);
        healBtn.setVisible(false);
        attackBtn.setVisible(false);
        logFeed.setVisible(false);
        keepGoing.setVisible(false);
        toTownBtn.setVisible(false);
        townHeal.setVisible(false);
        townRefill.setVisible(false);
        townUpgrade.setVisible(false);
        toDungeon.setVisible(false);
        restartBtn.setVisible(false);
        diedGif.setVisible(false);


        setUpEnemy(enemy);
        town.setUpTown();
        playerStats();
        start();
    }

    public void start(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getName() == null) {
            gameStarter.setOnAction(event -> {
                namer.setVisible(false);
                gameStarter.setVisible(false);
                healBtn.setVisible(false);
                attackBtn.setVisible(false);
                welcome1.setVisible(false);
                welcome2.setVisible(false);
                logFeed.setVisible(true);
                playerStatTA.setVisible(true);

                userEntity.getPlayerEntity().setName(namer.getText());
                playerName = namer.getText();
                player.setName(namer.getText());
                inTown();
                playerStats();
            });
        }else{
            welcome1.setVisible(false);
            welcome2.setVisible(false);
            namer.setVisible(false);
            gameStarter.setVisible(false);
            healBtn.setVisible(false);
            attackBtn.setVisible(false);
            logFeed.setVisible(true);
            playerStatTA.setVisible(true);

            inTown();
        }
    }

    public void attack(ActionEvent actionEvent) {
        fight.fight(enemy,town);
        playerStats();
        enemyStats();

        logFeed.appendText("\n\nYou have dealt [" + fight.getPlayerDamage() +"] damage to [" + enemy.getName() + "] !");
        logFeed.appendText("\nYou were hit for [" + fight.getEnemyDamage() + "] damage!");

        if (!enemy.isAlive()) {
            logFeed.setText("You have defeated [" + enemy.getName() + "]!\n\nYou have earned [" + userEntity.getPlayerEntity().getXpGain() + "xp]!\nYou have found [" + userEntity.getPlayerEntity().getGoldGain() +" gold]!");
            choice();
        }
        if (!fight.playerIsAlive()){
            attackBtn.setVisible(false);
            healBtn.setVisible(false);
            diedGif.setVisible(true);
            restartBtn.setVisible(true);

            logFeed.setText("You have died, you can restart the game from level 1.");

            restartBtn.setOnAction(event -> {
                diedGif.setVisible(false);
                restartBtn.setVisible(false);

                PlayerEntity playerEntity = new PlayerEntity(player);
                userEntity.setPlayerEntity(playerEntity);
                userEntity.getPlayerEntity().setName(playerName);
                playerStats();
                inTown();
            });
        }
    }

    public void heal(ActionEvent actionEvent) {
        fight.heal();
        playerStats();
    }

    public void setUpEnemy(Enemy enemy){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        if(userEntity.getPlayerEntity().getLevel() > 1){
            enemy.setMaxHealth((int) Math.round(enemy.getMaxHealth() * Math.pow(1.5, (userEntity.getPlayerEntity().getLevel()-1))));
            enemy.setCurrentHealth(enemy.getMaxHealth());
            enemy.setMinDamage((int) Math.round(enemy.getMinDamage() * Math.pow(1.4, (userEntity.getPlayerEntity().getLevel()-1))));
            enemy.setMaxDamage((int) Math.round(enemy.getMaxDamage() * Math.pow(1.4, (userEntity.getPlayerEntity().getLevel()-1))));
        }
    }


    public void getEnemy(){
        enemy.randomEnemy();
        setUpEnemy(enemy);
        enemyStats();
    }

    public void choice() {
        attackBtn.setVisible(false);
        healBtn.setVisible(false);
        toDungeon.setVisible(false);
        keepGoing.setVisible(true);
        toTownBtn.setVisible(true);
        keepGoing.setOnAction(event -> {
            keepGoing();
        });
        toTownBtn.setOnAction(event -> {
            inTown();
        });
    }

    public void keepGoing(){
        keepGoing.setVisible(false);
        toTownBtn.setVisible(false);
        townHeal.setVisible(false);
        townRefill.setVisible(false);
        townUpgrade.setVisible(false);
        attackBtn.setVisible(true);
        healBtn.setVisible(true);

        getEnemy();
    }

    public void inTown(){
        attackBtn.setVisible(false);
        healBtn.setVisible(false);
        toTownBtn.setVisible(false);
        keepGoing.setVisible(false);
        toDungeon.setVisible(true);
        townHeal.setVisible(true);
        townRefill.setVisible(true);
        townUpgrade.setVisible(true);

        townHeal.setOnAction(event -> {
            town.healUp();
            playerStats();
        });

        townRefill.setOnAction(event -> {
            town.refillPotions();
            playerStats();
        });

        townUpgrade.setOnAction(event -> {
            town.upgradeWeapon();
            playerStats();
            townStat();
        });

        toDungeon.setOnAction(event -> {
            toDungeon.setVisible(false);
            keepGoing();
        });

        townStat();

    }

    public void playerStats(){
        userEntity = userService.registered(UserServiceImpl.getUsername());

        playerStatTA.setText("Name: " + userEntity.getPlayerEntity().getName() + "  lvl: " + userEntity.getPlayerEntity().getLevel() +
                "\nHealth: " + userEntity.getPlayerEntity().getCurrentHealth() + " / " + userEntity.getPlayerEntity().getMaxHealth() +
                "\nDamage: " + userEntity.getPlayerEntity().getMinDamage()+" ~ "+userEntity.getPlayerEntity().getMaxDamage() +
                "\nXP: " + userEntity.getPlayerEntity().getCurrentXp()+" / "+ userEntity.getPlayerEntity().getXpNeeded() +
                "\nPotions: " + userEntity.getPlayerEntity().getNumberOfPotions() +
                "\nGold: " + userEntity.getPlayerEntity().getGold());
    }

    public void enemyStats(){
        logFeed.setText("You have encountered with [" + enemy.getName() + "] !" + "\nHealth: " + enemy.getCurrentHealth()+"/"+enemy.getMaxHealth() +
                "\nDamage: " + enemy.getMinDamage()+" ~ "+enemy.getMaxDamage());
    }

    public void townStat(){
        logFeed.setText("You are in the town now!\nWhat will you do now?\n\n" +
                "Heal - " + town.getHealUp() + " gold\n" +
                "Refill potions - " + town.getRefillPotions() + " gold\n" +
                "Upgrade weapon - " + town.getUpgradeWeapon() + " gold");
    }
}
