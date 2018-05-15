package com.magyart.random;

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

import com.magyart.random.DB.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


/**
 * Class containing the main method, used to launch JavaFX, set up the database, and to close it.
 */
public class Main  extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    /**
     * The main method.
     *
     * @param args - command line argument
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the database.
     */
    public void init(){
        Manager.getInstance();
    }

    /**
     * Closes the database.
     *
     * @throws Exception
     */
    public void stop() throws Exception {
        Manager.getInstance().close();
        super.stop();
    }
}
