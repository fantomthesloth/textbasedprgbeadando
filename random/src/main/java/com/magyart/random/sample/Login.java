package com.magyart.random.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Login {
    public Button toSignup;

    public TextField txtLoginUsr;
    public PasswordField txtLoginPswd;
    public Label msgBoxLogin;

    double x, y = 0;
    String style = "-fx-background-color:  #282828; -fx-text-fill: red";


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


    public void switchSignup(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        if(actionEvent.getSource()==toSignup) {
            stage = (Stage) toSignup.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/signup.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void sleep (int time) throws InterruptedException {
        Thread.sleep(time);
    }

    public void logIn(ActionEvent actionEvent) throws IOException, InterruptedException {
        Parent root;
        Stage stage = new Stage();
        if(!txtLoginUsr.getText().equals("asd") || !txtLoginPswd.getText().equals("asd")){
            msgBoxLogin.setStyle(style);
            msgBoxLogin.setText("Incorrect username and/or password!");
            if(txtLoginUsr.getText().isEmpty() || txtLoginPswd.getText().isEmpty()){
                msgBoxLogin.setText("You forgot to write your username and/or password!");
            }
        }
        if(txtLoginUsr.getText().equals("asd") && txtLoginPswd.getText().equals("asd")) {
            msgBoxLogin.setStyle(style);
            msgBoxLogin.setText("You have logged in, please wait..");
            root = FXMLLoader.load(getClass().getResource("/FXML/welcomeScreen.fxml"));
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }

    }
}
