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

    Player player = new Player();
    Enemy enemy = new Enemy();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        asd.setVisible(false);
        enemy.randomEnemy();
//        displayStats();
    }
    public void displayStats(){
        asd.setText("Name: " + player.getName() + "  Lvl: " + player.getLevel() +
                "\nHealth: " + player.getCurrentHealth()+"/"+player.getMaxHealth() +
                "\nDamage: " + player.getMinDamage()+" ~ "+player.getMaxDamage() +
                "\nXP: " + player.getCurrentXp()+"/"+player.getXpNeeded() +
                "\nPotions: " + player.getNumberOfPotions() +
                "\nGold: " + player.getGold() +

                "\n\neName: " + enemy.getName() +
                "\neHealth: " + enemy.getCurrentHealth()+"/"+enemy.getMaxHealth() +
                "\neDamage: " + enemy.getMinDamage()+" ~ "+enemy.getMaxDamage());
    }

    public void startGame(ActionEvent actionEvent) {
        player.setName(namer.getText());
        namer.setVisible(false);
        gameStarter.setVisible(false);
        asd.setVisible(true);
        displayStats();
    }

    public void ptakeDamage(ActionEvent actionEvent) {
        player.takeDamage(enemy);
        displayStats();
    }

    public void Heal(ActionEvent actionEvent) {
        player.heal();
        displayStats();
    }
}
