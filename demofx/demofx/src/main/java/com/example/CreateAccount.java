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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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


        //username
        Label usernameLabel = new Label ("Username:");
        fieldsBox.add (usernameLabel, 3, 1);

        TextField usernameTextField = new TextField ();
        fieldsBox.add (usernameTextField, 4, 1);
        // email
        Label emailLabel = new Label ("Email:");
        fieldsBox.add (emailLabel, 3, 2);

        TextField emailTextField = new TextField();
        fieldsBox.add (emailTextField, 4, 2);

        // password
        Label passwordLabel = new Label ("Password:");
        fieldsBox.add (passwordLabel, 3, 3);

        PasswordField passwordTextField = new PasswordField ();
        fieldsBox.add (passwordTextField, 4, 3);

        // semester
        HBox semesterBox = new HBox();
        semesterBox.setAlignment(Pos.CENTER);
        semesterBox.setSpacing(10);
        Label semesterLabel = new Label ("Semester:");
        
        RadioButton semesterBtn1 = new RadioButton("1");
        RadioButton semesterBtn2 = new RadioButton("2");
        RadioButton semesterBtn3 = new RadioButton("3");
        RadioButton semesterBtn4 = new RadioButton("4");
        
        ToggleGroup semesterButtons = new ToggleGroup();
        semesterBtn1.setToggleGroup(semesterButtons);
        semesterBtn2.setToggleGroup(semesterButtons); 
        semesterBtn3.setToggleGroup(semesterButtons);
        semesterBtn4.setToggleGroup(semesterButtons);
        semesterBox.getChildren().addAll(semesterLabel, semesterBtn1, semesterBtn2, semesterBtn3, semesterBtn4);
        

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
                String inputUsername = usernameTextField.getText();
                String inputEmail = emailTextField.getText();
                String inputPassword = passwordTextField.getText();
                int inputSemester;

                if(semesterBtn1.isSelected())
                {
                    inputSemester = 1;
                }
                else if(semesterBtn2.isSelected())
                {
                    inputSemester = 2;
                }
                else if(semesterBtn3.isSelected())
                {
                    inputSemester = 3;
                }
                else if(semesterBtn4.isSelected())
                {
                    inputSemester = 4;
                }
                else{
                    inputSemester = -1; //missing info       
                }


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
                } 

                // TODO: If it does not exist check if all the inputs are valid or not
                if(inputDepartment.equals("") || inputEmail.equals("") || inputName.equals("") || inputPassword.equals("")
                   || inputSurname.equals("") ||  inputUsername.equals("") || inputSemester == -1)
                {
                    Alert missingInformationAlert = new Alert(AlertType.ERROR);
                    missingInformationAlert.setHeaderText("Missing Information!");
                    missingInformationAlert.setContentText("Please enter all of the information.");
                    missingInformationAlert.showAndWait();
                }

                //ADD USER
                UserManager UM = new UserManager();
                UM.createUser(inputUsername, inputPassword, inputEmail, inputName, inputSurname, inputSemester, inputDepartment);
                DatabaseConnection.disconnect(); 

                try{
                    createAccountStage.close();
                    Application login = new Login();
                    Stage loginStage = new Stage();
                    login.start(loginStage);
                }
                catch(Exception e)
                {

                }

            }
            
        } );


        // create account button box
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add (createAccountButton);

        // adding components to root and scene
        root.getChildren().addAll (forumLabel, fieldsBox, semesterBox, buttonBox);
        
        Scene createAccountScene = new Scene (root, 550, 400);

        // stage
        createAccountStage.setScene (createAccountScene);
        createAccountStage.setTitle ("Create Account for Bilkent Forum");
        createAccountStage.show();


    } 
}
