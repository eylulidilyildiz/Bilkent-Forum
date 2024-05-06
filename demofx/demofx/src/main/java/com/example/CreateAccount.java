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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
        Label forumLabel = new Label(" BILKENT FORUM ");
        forumLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        forumLabel.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(11), new Insets(1))));
        Button createAccountButton = new Button("CREATE ACCOUNT");
        createAccountButton.setPrefWidth(225);

        //root 
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        // name / surname, department, semester, email, password
        GridPane fieldsBox = new GridPane();
        fieldsBox.setAlignment(Pos.CENTER);
        fieldsBox.setHgap(10);
        fieldsBox.setVgap(10);

        // name 
        Label nameLabel = new Label ("Name:");
        fieldsBox.add (nameLabel, 0, 1);
        
        TextField nameTextField = new TextField();
        fieldsBox.add (nameTextField, 1, 1);

        //surname
        Label surnameLabel = new Label ("Surname:");
        fieldsBox.add (surnameLabel, 0, 2);

        TextField surnameTextField = new TextField();
        fieldsBox.add (surnameTextField, 1, 2);

        // department
        Label departmentLabel = new Label ("Department:");
        fieldsBox.add (departmentLabel, 0, 3);

        TextField departmentTextField = new TextField();
        fieldsBox.add (departmentTextField, 1, 3);

        // semester
        Label semesterLabel = new Label ("Semester:");
        fieldsBox.add (semesterLabel, 0, 4);

        TextField semesterTextField = new TextField();
        fieldsBox.add (semesterTextField, 1, 4);

        //username
        Label usernameLabel = new Label ("Username:");
        fieldsBox.add (usernameLabel, 0, 5);

        TextField usernameTextField = new TextField ();
        fieldsBox.add (usernameTextField, 1, 5);
        // email
        Label emailLabel = new Label ("Email:");
        fieldsBox.add (emailLabel, 0, 6);

        TextField emailTextField = new TextField();
        fieldsBox.add (emailTextField, 1, 6);

        // password
        Label passwordLabel = new Label ("Password:");
        fieldsBox.add (passwordLabel, 0, 7);

        PasswordField passwordTextField = new PasswordField ();
        fieldsBox.add (passwordTextField, 1, 7);


        // create account button event handling
        createAccountButton.setOnAction (new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle (ActionEvent event) 
            {
                // Input from the text fields
                String inputName = nameTextField.getText();
                String inputSurname = surnameTextField.getText();
                String inputDepartment = departmentTextField.getText();
                String inputSemester = semesterTextField.getText();
                String inputUsername = usernameTextField.getText();
                String inputEmail = emailTextField.getText();
                String inputPassword = passwordTextField.getText();


                //INFORMATION VERIFICATION
                DatabaseConnection.connect(); 

                try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
                {

                    //check if an account already exists with the input email or username
                    int i = 1;
                    while(session.get(User.class, i) != null)
                    {
                        User user = session.get(User.class, i);
                        if (user.getEmail().equals(inputEmail))
                        {
                            Alert emailExistsAlert = new Alert(AlertType.ERROR);
                            emailExistsAlert.setHeaderText("Email already exists!");
                            emailExistsAlert.setContentText("An account already exists with this email address.");
                            emailExistsAlert.showAndWait();
                        }
                        else if (user.getUsername().equals(inputUsername))
                        {
                            Alert usernameExistsAlert = new Alert(AlertType.ERROR);
                            usernameExistsAlert.setHeaderText("Username already exists!");
                            usernameExistsAlert.setContentText("An account already exists with this username. Try another one.");
                            usernameExistsAlert.showAndWait();
                        }
                        i++;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                DatabaseConnection.disconnect(); 
                }

                // TODO: If it does not exist check if all the inputs are valid or not
                if(inputDepartment.equals("") || inputEmail.equals("") || inputName.equals("")l || inputPassword.equals("")
                   || inputSurname.equals("") ||  inputUsername.equals(""))
                {
                    Alert missingInformationAlert = new Alert(AlertType.ERROR);
                    missingInformationAlert.setHeaderText("Missing Information!");
                    missingInformationAlert.setContentText("Please fill in all of the fields.");
                    missingInformationAlert.showAndWait();
                }
                if(Integer.parseInt(inputSemester) < 1 || Integer.parseInt(inputSemester) > 4)
                {
                    Alert invalidSemesterAlert = new Alert(AlertType.ERROR);
                    invalidSemesterAlert.setHeaderText("Incorrect Semester Information!");
                    invalidSemesterAlert.setContentText("Semester needs to be between 1 and 4");
                    invalidSemesterAlert.showAndWait();
                }

                // TODO: Add the user to the Bilkent Forum
            }
            
        } );


        // create account button box
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add (createAccountButton);

        // adding components to root and scene
        root.getChildren().addAll (forumLabel, fieldsBox, buttonBox);
        
        Scene createAccountScene = new Scene (root, 500, 500);

        // stage
        createAccountStage.setScene (createAccountScene);
        createAccountStage.setTitle ("Create Account for Bilkent Forum");
        createAccountStage.show();


    } 
}
