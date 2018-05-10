package com.magyart.random.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class Signup {
    public Button toLogin;

    public TextField txtSignupUsr;
    public PasswordField txtSignupPswd;
    public PasswordField txtSignupPswd2;

    public Label msgBoxSignup;

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


    public void switchLogin(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        if(actionEvent.getSource()==toLogin) {
            stage = (Stage) toLogin.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void signup(ActionEvent actionEvent) {
        if(txtSignupUsr.getText().isEmpty() || txtSignupPswd.getText().isEmpty() || txtSignupPswd2.getText().isEmpty()) {
            msgBoxSignup.setStyle(style);
            msgBoxSignup.setText("You must write something in each box!");
        }
        if (!txtSignupUsr.getText().isEmpty() && !txtSignupPswd.getText().equals(txtSignupPswd2.getText())) {
            msgBoxSignup.setStyle(style);
            msgBoxSignup.setText("Passwords do not match!");
        }else if(!txtSignupUsr.getText().isEmpty() && txtSignupPswd.getText().equals(txtSignupPswd2.getText()) && !txtSignupPswd.getText().isEmpty() && !txtSignupPswd2.getText().isEmpty()){
            msgBoxSignup.setStyle(style);
            msgBoxSignup.setText("Signup successful, you can log in now!");
        }
    }
}
