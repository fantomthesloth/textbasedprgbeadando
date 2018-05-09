package com.magyart.random;

import com.magyart.random.DB.Manager.SampleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main  extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    private static final SampleManager DB = SampleManager.getDbPeldany();

    public static void main(String[] args) {

        try {
            DB.connectDB();
            launch(args);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DB.disconnectDB();
        }
    }

}
