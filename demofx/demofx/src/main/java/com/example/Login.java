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

        //root 
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        //email and password fields
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
        fieldsBox.add(passwordField, 1, 2);

        //login button event handling
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) 
            { 
                String inputEmail = emailTextField.getText();
                String inputPassword = passwordField.getText();
                int mainUserID = checkEmailAndPassword(inputEmail);
                if(mainUserID == -1)
                {
                    Alert emailDoesNotExistAlert = new Alert(AlertType.ERROR);
                    emailDoesNotExistAlert.setHeaderText("Email not valid!");
                    emailDoesNotExistAlert.setContentText("There is no account for this email. Please register.");
                    emailDoesNotExistAlert.showAndWait();
                }
                else{
                    DatabaseConnection.connect(); 
                    try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
                    {
                        User user = session.get(User.class, mainUserID);
                        if(user.getPassword().equals(inputPassword))
                        {
                            loginStage.close();
                            Application home = new HomePage();
                            Stage homeStage = new Stage();
                            home.start(homeStage);
                        }
                        else{
                            Alert invalidPasswordAlert = new Alert(AlertType.ERROR);
                            invalidPasswordAlert.setHeaderText("Password is incorrect!");
                            invalidPasswordAlert.setContentText("Your password is not correct. Please try again.");
                            invalidPasswordAlert.showAndWait();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        DatabaseConnection.disconnect(); 
                    }
                }
            } 
        });


        //button box
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(loginButton);

        //create account label
        Label createAccountLabel = new Label("Create Account");
        GridPane labelBox = new GridPane();
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setHgap(10);
        labelBox.setVgap(10);

        createAccountLabel.setOnMouseClicked(new EventHandler< MouseEvent>() 
        {    
            @Override    
            public void handle(MouseEvent event) {     
                //TODO 
                createAccountLabel.setText("Mouse Clicked");    
            }  
        });

        createAccountLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {    
            @Override    
            public void handle(MouseEvent event) 
            {      
                createAccountLabel.setUnderline(true);;    
            }  
        });  
        
        createAccountLabel.setOnMouseExited(new EventHandler<MouseEvent>() {    
            @Override    
            public void handle(MouseEvent event) 
            {      
                createAccountLabel.setUnderline(false);   
            }  
        });

        labelBox.add(createAccountLabel, 12, 0);



        //adding components to the root and scene
        root.getChildren().addAll(fieldsBox, buttonBox, labelBox);
        Scene loginScene = new Scene(root, 300, 300);

        //stage
        loginStage.setScene(loginScene);
        loginStage.setTitle("Login to Bilkent Forum");
        loginStage.show();

    }

    public int checkEmailAndPassword(String email)
    {
        DatabaseConnection.connect(); 

        try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
        {
            int i = 1;
            while(session.get(User.class, i) != null)
            {
                User user = session.get(User.class, i);
                if(user.getEmail().equals(email))
                {
                    return user.getId();
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect(); 
        }
        return -1;
    }
}