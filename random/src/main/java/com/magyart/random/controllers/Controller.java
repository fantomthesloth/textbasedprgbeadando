package com.magyart.random.controllers;

import com.magyart.random.model.Enemy;
import com.magyart.random.model.Fight;
import com.magyart.random.model.Player;
import com.magyart.random.model.Town;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    private Player player = new Player();
    private Enemy enemy = new Enemy();
    private Town town = new Town();
    private Fight fight = new Fight();

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
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    public void playerStats(){
        playerStatTA.setText("Name: " + player.getName() + "  Lvl: " + player.getLevel() +
                "\nHealth: " + player.getCurrentHealth()+"/"+player.getMaxHealth() +
                "\nDamage: " + player.getMinDamage()+" ~ "+player.getMaxDamage() +
                "\nXP: " + player.getCurrentXp()+"/"+player.getXpNeeded() +
                "\nPotions: " + player.getNumberOfPotions() +
                "\nGold: " + player.getGold());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        enemy.setUpEnemy(player, enemy);
        town.setUpTown(player);
    }

    public void getEnemy(){
        enemy.randomEnemy();
        enemy.setUpEnemy(player,enemy);
        enemyStats();
    }

    public void startGame(ActionEvent actionEvent) {
        player.setName(namer.getText());
        namer.setVisible(false);
        gameStarter.setVisible(false);
        healBtn.setVisible(false);
        attackBtn.setVisible(false);

        logFeed.setVisible(true);
        playerStatTA.setVisible(true);
        inTown();
        playerStats();
    }

    public void Heal(ActionEvent actionEvent) {
        player.heal();
        playerStats();
    }

    public void attack(ActionEvent actionEvent) throws InterruptedException {

        fight.fight(player,enemy,town);
        playerStats();
        enemyStats();

        logFeed.appendText("\n\nYou have dealt [" + fight.getPlayerDamage() +"] damage to [" + enemy.getName() + "] !");
        logFeed.appendText("\nYou were hit for [" + fight.getEnemyDamage() + "] damage!");

        if (!enemy.isAlive()) {
            choice();
            logFeed.setText("You have defeated [" + enemy.getName() + "]!\n\nYou have earned [" + player.getXpGain() + "xp]!\nYou have found [" + player.getGoldGain() +" gold]!");
        }
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
        attackBtn.setVisible(true);
        healBtn.setVisible(true);
        keepGoing.setVisible(false);
        toTownBtn.setVisible(false);
        townHeal.setVisible(false);
        townRefill.setVisible(false);
        townUpgrade.setVisible(false);

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
            town.healUp(player);
            playerStats();
        });

        townRefill.setOnAction(event -> {
            town.refillPotions(player);
            playerStats();
        });

        townUpgrade.setOnAction(event -> {
            town.upgradeWeapon(player);
            playerStats();
            townStat();
        });

        toDungeon.setOnAction(event -> {
            toDungeon.setVisible(false);
            keepGoing();
        });

        townStat();

    }
}
