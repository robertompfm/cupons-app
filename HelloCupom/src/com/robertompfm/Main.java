package com.robertompfm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        primaryStage.setTitle("HelloCupom");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    public static Stage getStage() { return stage; };

    public static void main(String[] args) {
        launch(args);
    }
}
