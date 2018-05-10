package com.magyart.random.sample;

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
    public TextArea asd;
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

    private Player player = new Player();
    private Enemy enemy = new Enemy();
    private Town town = new Town();

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
        asd.setText("Name: " + player.getName() + "  Lvl: " + player.getLevel() +
                "\nHealth: " + player.getCurrentHealth()+"/"+player.getMaxHealth() +
                "\nDamage: " + player.getMinDamage()+" ~ "+player.getMaxDamage() +
                "\nXP: " + player.getCurrentXp()+"/"+player.getXpNeeded() +
                "\nPotions: " + player.getNumberOfPotions() +
                "\nGold: " + player.getGold());
    }

    public void enemyStats(){
        logFeed.setText("You have encountered with " + enemy.getName() + "!" + "\neHealth: " + enemy.getCurrentHealth()+"/"+enemy.getMaxHealth() +
                                                                                    "\neDamage: " + enemy.getMinDamage()+" ~ "+enemy.getMaxDamage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        asd.setVisible(false);
        healBtn.setVisible(false);
        attackBtn.setVisible(false);
        logFeed.setVisible(false);
        keepGoing.setVisible(false);
        toTownBtn.setVisible(false);
        getEnemy();
    }
    public void getEnemy(){
        enemy.randomEnemy();
        enemyStats();
    }

    public void startGame(ActionEvent actionEvent) {
        player.setName(namer.getText());
        namer.setVisible(false);
        gameStarter.setVisible(false);
        asd.setVisible(true);
        healBtn.setVisible(true);
        attackBtn.setVisible(true);
        logFeed.setVisible(true);
        playerStats();
    }

    public void Heal(ActionEvent actionEvent) {
        player.heal();
        playerStats();
    }

    public void attack(ActionEvent actionEvent) throws InterruptedException {
        int playerDamage = player.attack();
        int enemyDamage = enemy.attack();
        if(player.isAlive() && enemy.isAlive()) {
            enemy.takeDamage(playerDamage);
            enemyStats();
            logFeed.appendText("\nYou have dealt " +playerDamage+" damage to " + enemy.getName() + "!");
            if(enemy.isAlive()) {
                logFeed.appendText("\nYou were hit for " + enemyDamage + " damage!");
                player.takeDamage(enemyDamage);
            }else {
                logFeed.appendText("\nYou have defeated " + enemy.getName() + "!");
                player.leveling(town);
                player.setGold(player.getGold()+5);
                choice();
            }
            playerStats();
        }
    }

    public void choice() {
        logFeed.appendText("\n\nWhat will you do?");
        attackBtn.setVisible(false);
        healBtn.setVisible(false);
        keepGoing.setVisible(true);
        toTownBtn.setVisible(true);
        keepGoing.setOnAction(event -> {
            getEnemy();
            attackBtn.setVisible(true);
            healBtn.setVisible(true);
            keepGoing.setVisible(false);
        });
        toTownBtn.setOnAction(event -> {
            logFeed.setText("You decided to go back to town!\n\nWhat will you do now?");
        });
    }
}
