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
import com.magyart.random.model.Player;
import com.magyart.random.model.PlayerEntity;
import com.magyart.random.model.UserEntity;
import com.magyart.random.service.UserServiceImpl;
import com.magyart.random.service.service.UserService;
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

    private PlayerEntity playerEntity = new PlayerEntity();


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
