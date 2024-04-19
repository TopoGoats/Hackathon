package com.example.hackaton;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Pane root = new Pane();
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseController.connectToDatabase();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}