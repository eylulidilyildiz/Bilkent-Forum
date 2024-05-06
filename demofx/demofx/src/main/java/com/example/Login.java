package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.*;

public class Login extends Application{

    public static void main(String[] args) 
    {
        launch(args);
    }


    @Override
    public void start(Stage loginStage) throws Exception {

        //Components of the scene
        Label forumLabel = new Label("BILKENT FORUM");
        Button loginButton = new Button("LOGIN");


        //root and scene
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        root.add(forumLabel, 1, 0);

        Label emailLabel = new Label("Email:");
        root.add(emailLabel, 0, 1);

        TextField emailTextField = new TextField();
        root.add(emailTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        root.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        /*passwordField.setText("Password");
        passwordField.setVisible(true);*/
        root.add(passwordField, 1, 2);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.getChildren().add(loginButton);
        root.add(buttonBox, 1, 3);

        Scene loginScene = new Scene(root, 300, 300);

        //stage
        loginStage.setScene(loginScene);
        loginStage.setTitle("Login to Bilkent Forum");
        loginStage.show();

    }
}