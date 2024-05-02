package com.example;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestJavaFX extends Application{

    @Override
    public void start(Stage stage) {
        stage.setTitle("My First Stage!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
