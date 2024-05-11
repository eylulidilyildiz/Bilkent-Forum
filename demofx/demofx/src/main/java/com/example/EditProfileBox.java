package com.example;



import javafx.event.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class EditProfileBox extends VBox
{
    // Constants
    private static final double ICON_HEIGHT = 20;
    private static final double ICON_WIDTH = 20;
    private static final double FIELD_HEIGHT = 40;
    private static final double FIELD_WIDTH = 300;
    private static final double BUTTON_HEIGHT = 60;
    private static final double BUTTON_WIDTH = 200;


    // Instance Variables
    private User mainUser;
    private VBox nameDepartmentSemesterBox;
    private VBox mailPasswordBox;
    private Button saveButton;
    private HBox searchAddPostBox;

    // Constructor
    public EditProfileBox (User user)
    {
        super();
        this.mainUser = user;

        this.createSearchAndAddPost();
        this.createNameDepartmentSemesterBox();
        this.createMailPasswordBox();

        // button
        saveButton = new Button ("Save Changes");
        saveButton.setPrefHeight (BUTTON_HEIGHT);
        saveButton.setPrefWidth (BUTTON_WIDTH);
        saveButton.setFont (Font.font ("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));

        HBox editProfileBox = new HBox();
        editProfileBox.getChildren().addAll (this.nameDepartmentSemesterBox, this.mailPasswordBox);
        editProfileBox.setSpacing (500);
        editProfileBox.setAlignment (Pos.CENTER);

        Label editProfileLabel = new Label ("Edit Profile");
        editProfileLabel.setFont (Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 37));

        VBox combineButton = new VBox();
        combineButton.setPadding (new Insets (10));
        combineButton.setSpacing (30);
        combineButton.setAlignment (Pos.CENTER);

        combineButton.getChildren().addAll (editProfileLabel, editProfileBox, saveButton);


        this.getChildren().add (combineButton);

    }

    @SuppressWarnings("unchecked")
    private void createNameDepartmentSemesterBox ()
    {
        nameDepartmentSemesterBox = new VBox();
        nameDepartmentSemesterBox.setSpacing (15);
        nameDepartmentSemesterBox.setAlignment (Pos.CENTER_LEFT);

        

        // name label and text field
        Label nameLabel = new Label ("Name:");
        nameLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField nameTextField = new TextField ();
        nameTextField.setPrefHeight (FIELD_HEIGHT);
        nameTextField.setPrefWidth (FIELD_WIDTH);
        nameTextField.setText (mainUser.getName());
        nameTextField.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        // surname
        Label surnameLabel = new Label ("Surname:");
        surnameLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField surnameTextField = new TextField ();
        surnameTextField.setPrefHeight (FIELD_HEIGHT);
        surnameTextField.setPrefWidth (FIELD_WIDTH);
        surnameTextField.setText (mainUser.getSurname());
        surnameTextField.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));


        // department
        Label departmentLabel = new Label ("Department:");
        departmentLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField departmentTextField = new TextField();
        departmentTextField.setPrefHeight (FIELD_HEIGHT);
        departmentTextField.setPrefWidth (FIELD_WIDTH);
        departmentTextField.setText (mainUser.getDepartment());
        departmentTextField.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        // semester
        Label semesterLabel = new Label ("Semester:");
        semesterLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        @SuppressWarnings("rawtypes")
        ChoiceBox semesterBox = new ChoiceBox <Integer>();
        semesterBox.getItems().add (1);
        semesterBox.getItems().add (2);
        semesterBox.getItems().add (3);
        semesterBox.getItems().add (4);

        semesterBox.setValue (mainUser.getSemester());

        semesterBox.setOnAction (new EventHandler<Event>() 
        {

            @Override
            public void handle (Event event) 
            {
                
                Integer newSemester = (Integer) semesterBox.getValue();
            }
            
        });

        // connecting the components
        nameDepartmentSemesterBox.getChildren().addAll (nameLabel, nameTextField, surnameLabel, surnameTextField, departmentLabel, departmentTextField, semesterLabel, semesterBox);


    }

    private void createMailPasswordBox ()
    {
        this.mailPasswordBox = new VBox();
        this.mailPasswordBox.setSpacing (15);
        this.mailPasswordBox.setAlignment (Pos.CENTER_LEFT);

        // username
        Label usernameLabel = new Label ("Username:");
        usernameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField usernameTextField = new TextField ();
        usernameTextField.setPrefHeight (FIELD_HEIGHT);
        usernameTextField.setPrefWidth (FIELD_WIDTH);
        usernameTextField.setText (mainUser.getUsername());
        usernameTextField.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));


        // mail
        Label mailLabel = new Label ("Email:");
        mailLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField mailTextField = new TextField();
        mailTextField.setPrefHeight (FIELD_HEIGHT);
        mailTextField.setPrefWidth (FIELD_WIDTH);
        mailTextField.setText (mainUser.getEmail());
        mailTextField.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        // passwprd
        Label passwordLabel = new Label ("Password:");
        passwordLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField passwordTextField = new TextField();
        passwordTextField.setPrefHeight (FIELD_HEIGHT);
        passwordTextField.setPrefWidth (FIELD_WIDTH);
        passwordTextField.setText (mainUser.getPassword());
        passwordTextField.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        // password again
        Label passAgainLabel = new Label ("Password (Again):");
        passAgainLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField passAgainTextField = new TextField();
        passAgainTextField.setPrefHeight (FIELD_HEIGHT);
        passAgainTextField.setPrefWidth (FIELD_WIDTH);
        passAgainTextField.setText (mainUser.getPassword());
        passAgainTextField.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));


        this.mailPasswordBox.getChildren().addAll (usernameLabel, usernameTextField, mailLabel, mailTextField, passwordLabel, passwordTextField, passAgainLabel, passAgainTextField);
        
        
    
    }

    private void saveButtonIsClicked ()
    {
        saveButton.setOnAction (new EventHandler <ActionEvent>() 
        {

            @Override
            public void handle (ActionEvent arg0) 
            {
                
            }
            
        });
    }

    private void createSearchAndAddPost ()
    {
        this.searchAddPostBox = new HBox();

        // SEARCH BAR
        GridPane searchPane = new GridPane();
        searchPane.setAlignment (Pos.TOP_LEFT);
        searchPane.setHgap (10);
        searchPane.setVgap (10);
        searchPane.setPadding(new Insets(70));

        TextField searchBar = new TextField();
        searchBar.setPrefHeight (40);
        searchBar.setPrefWidth (400);
        searchPane.add (searchBar, 2, 1);
        
        ImageView searchIcon = new ImageView (getClass().getResource("images/browseIcon.png").toString());
        searchIcon.setFitHeight (ICON_HEIGHT);
        searchIcon.setFitWidth (ICON_WIDTH);
        searchPane.add (searchIcon, 0, 1); // image

        Label searchLabel = new Label ("Search:");
        searchLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        searchPane.add (searchLabel, 1, 1);


        // ADD POST BUTTON
        GridPane postButtonPane = new GridPane();
        postButtonPane.setAlignment (Pos.TOP_RIGHT);
        postButtonPane.setHgap (10);
        postButtonPane.setVgap (10);
        postButtonPane.setPadding(new Insets(70));

        ToggleButton addPostButton = new ToggleButton ();
        addPostButton.setBackground (new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
        addPostButton.setPrefHeight (60);
        addPostButton.setPrefWidth (60);

        ImageView plusIcon = new ImageView (getClass().getResource("images/plusIcon.png").toString());
        plusIcon.setFitHeight (60);
        plusIcon.setFitWidth (60);

        addPostButton.setGraphic (plusIcon);

        postButtonPane.add (addPostButton, 0, 1);

        // combining the searchPane and the addPostButton 
        searchAddPostBox.setSpacing (550);
        searchAddPostBox.getChildren().addAll (searchPane, postButtonPane);

        this.getChildren().add (searchAddPostBox);
        
    }

}
