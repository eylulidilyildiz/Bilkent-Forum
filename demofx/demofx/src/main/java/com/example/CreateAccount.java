package com.example;

import org.hibernate.Session;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class CreateAccount extends Application
{
    public static void main(String[] args) 
    {
        launch (args);
    }

    @Override
    public void start (Stage createAccountStage) throws Exception 
    {
        //Components of the scene
        Label forumLabel = new Label("BILKENT FORUM");
        Button createAccountButton = new Button("CREATE ACCOUNT");
        createAccountButton.setPrefWidth(212.5);

        //root 
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        // name / surname, department, semester, email, password
        GridPane fieldsBox = new GridPane();
        fieldsBox.setAlignment(Pos.CENTER);
        fieldsBox.setHgap(10);
        fieldsBox.setVgap(10);

        // name / surname
        Label nameSurnameLabel = new Label ("Name / Surname:");
        fieldsBox.add (nameSurnameLabel, 0, 1);

        TextField nameSurnameTextField = new TextField();
        fieldsBox.add (nameSurnameTextField, 1, 1);

        // department
        Label departmentLabel = new Label ("Department:");
        fieldsBox.add (departmentLabel, 0, 2);

        TextField departmentTextField = new TextField();
        fieldsBox.add (departmentTextField, 1, 2);

        // semester
        Label semesterLabel = new Label ("Semester:");
        fieldsBox.add (semesterLabel, 0, 3);

        TextField semesterTextField = new TextField();
        fieldsBox.add (semesterTextField, 1, 3);

        // email
        Label emailLabel = new Label ("Email:");
        fieldsBox.add (emailLabel, 0, 4);

        TextField emailTextField = new TextField();
        fieldsBox.add (emailTextField, 1, 4);

        // password
        Label passwordLabel = new Label ("Password:");
        fieldsBox.add (passwordLabel, 0, 5);

        PasswordField passwordTextField = new PasswordField ();
        fieldsBox.add (passwordTextField, 1, 5);


        // create account button event handling
        createAccountButton.setOnAction (new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle (ActionEvent event) 
            {
                // Input from the text fields
                String inputNameSurname = nameSurnameTextField.getText();
                String inputDepartment = departmentTextField.getText();
                String inputSemester = semesterTextField.getText();
                String inputEmail = emailTextField.getText();
                String inputPassword = passwordTextField.getText();

                // TODO: Check if email or password already exists

                // TODO: If it does not exist check if all the inputs are valid or not
            }
            
        } );


        // create account button box
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add (createAccountButton);

        // adding components to root and scene
        root.getChildren().addAll (fieldsBox, buttonBox);
        
        Scene createAccountScene = new Scene (root, 500, 500);

        // stage
        createAccountStage.setScene (createAccountScene);
        createAccountStage.setTitle ("Create Account for Bilkent Forum");
        createAccountStage.show();


    }


    
}
