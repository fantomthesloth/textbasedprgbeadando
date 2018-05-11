package com.magyart.random.controllers;

import com.magyart.random.DAO.UserDAOImpl;
import com.magyart.random.DB.Manager;
import com.magyart.random.service.UserServiceImpl;
import com.magyart.random.service.api.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    public Button toSignup;
    @FXML
    public TextField txtLoginUsr;
    @FXML
    public PasswordField txtLoginPswd;
    @FXML
    public Label msgBoxLogin;

    private double x, y = 0;
    private String style = "-fx-background-color:  #282828; -fx-text-fill: red";

    private UserService userService;


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
        Manager.getInstance().close();
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void initialize(){
        userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
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

    public void logIn(ActionEvent actionEvent) throws Exception {
        Parent root;
        Stage stage = new Stage();

        if(txtLoginUsr.getText().isEmpty() || txtLoginPswd.getText().isEmpty()){
            msgBoxLogin.setStyle(style);
            msgBoxLogin.setText("You forgot to write your username and/or password!");
        }else if(userService.loggedIn(txtLoginUsr.getText(), txtLoginPswd.getText()) != null){
            UserServiceImpl.setUsername(txtLoginUsr.getText());

            root = FXMLLoader.load(getClass().getResource("/FXML/mainWindow.fxml"));
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }else{
            msgBoxLogin.setStyle(style);
            msgBoxLogin.setText("Incorrect username and/or password!");
        }
    }
}
