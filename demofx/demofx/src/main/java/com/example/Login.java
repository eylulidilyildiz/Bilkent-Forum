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
import javafx.scene.layout.VBox;
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
        loginButton.setPrefWidth(212.5);


        //root and scene
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        GridPane fieldsBox = new GridPane();
        fieldsBox.setAlignment(Pos.CENTER);
        fieldsBox.setHgap(10);
        fieldsBox.setVgap(10);

        fieldsBox.add(forumLabel, 1, 0);

        Label emailLabel = new Label("Email:");
        fieldsBox.add(emailLabel, 0, 1);

        TextField emailTextField = new TextField();
        fieldsBox.add(emailTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        fieldsBox.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        /*passwordField.setText("Password");
        passwordField.setVisible(true);*/
        fieldsBox.add(passwordField, 1, 2);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(loginButton);

        root.getChildren().addAll(fieldsBox, buttonBox);

        Scene loginScene = new Scene(root, 300, 300);

        //stage
        loginStage.setScene(loginScene);
        loginStage.setTitle("Login to Bilkent Forum");
        loginStage.show();

    }
}