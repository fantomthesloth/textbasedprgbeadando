package com.magyart.random.controllers;

import com.magyart.random.DAO.UserDAOImpl;
import com.magyart.random.DB.Manager;
import com.magyart.random.model.Player;
import com.magyart.random.model.PlayerEntity;
import com.magyart.random.model.UserEntity;
import com.magyart.random.service.UserServiceImpl;
import com.magyart.random.service.api.UserService;
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

    private double x, y = 0;
    private String style = "-fx-background-color:  #282828; -fx-text-fill: red";

    private UserService userService;

    PlayerEntity playerEntity = new PlayerEntity();


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

    public void initialize(){
        userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
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
        UserEntity userEntity = new UserEntity();
        playerEntity = new PlayerEntity(new Player());

        if(txtSignupUsr.getText().isEmpty() || txtSignupPswd.getText().isEmpty() || txtSignupPswd2.getText().isEmpty()) {
            msgBoxSignup.setStyle(style);
            msgBoxSignup.setText("You must write something in each box!");
        }
        else if (!txtSignupUsr.getText().isEmpty() && !txtSignupPswd.getText().equals(txtSignupPswd2.getText())) {
            msgBoxSignup.setStyle(style);
            msgBoxSignup.setText("Passwords do not match!");
        }else{
            userEntity.setUsername(txtSignupUsr.getText());
            userEntity.setPassword(txtSignupPswd.getText());
            userEntity.setPlayerEntity(playerEntity);

            if(userService.registered(txtSignupUsr.getText()) == null){
                userService.registerUser(userEntity);
                msgBoxSignup.setStyle(style);
                msgBoxSignup.setText("Signup successful, you can log in now!");
            }else{
                msgBoxSignup.setStyle(style);
                msgBoxSignup.setText("Username already taken!");
            }
        }
    }
}
