package com.example;


import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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


        HBox editProfileBox = new HBox();
        editProfileBox.getChildren().addAll (this.nameDepartmentSemesterBox, this.mailPasswordBox);
        editProfileBox.setSpacing (500);


        this.getChildren().add (editProfileBox);

    }

    @SuppressWarnings("unchecked")
    private void createNameDepartmentSemesterBox ()
    {
        nameDepartmentSemesterBox = new VBox();
        nameDepartmentSemesterBox.setAlignment (Pos.CENTER_LEFT);


        // name label and text field
        Label nameLabel = new Label ("Name:");
        nameLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField nameTextField = new TextField ();

        // department
        Label departmentLabel = new Label ("Department:");
        departmentLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField departmentTextField = new TextField();

        // semester
        Label semesterLabel = new Label ("Semester:");
        semesterLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        @SuppressWarnings("rawtypes")
        ChoiceBox semesterBox = new ChoiceBox <Integer>();
        semesterBox.getItems().add (1);
        semesterBox.getItems().add (2);
        semesterBox.getItems().add (3);
        semesterBox.getItems().add (4);

        semesterBox.setOnAction (new EventHandler<Event>() 
        {

            @Override
            public void handle (Event event) 
            {
                Integer newSemester = (Integer) semesterBox.getValue();
            }
            
        });

        // connecting the components
        nameDepartmentSemesterBox.getChildren().addAll (nameLabel, nameTextField, departmentLabel, departmentTextField, semesterLabel, semesterBox);


    }

    private void createMailPasswordBox ()
    {
        this.mailPasswordBox = new VBox();
        this.mailPasswordBox.setAlignment (Pos.CENTER_LEFT);

        // mail
        Label mailLabel = new Label ("Email:");
        mailLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField mailTextField = new TextField();

        // passwprd
        Label passwordLabel = new Label ("Password:");
        passwordLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField passwordTextField = new TextField();

        // password again
        Label passAgainLabel = new Label ("Password (Again):");
        passAgainLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField passAgainTextField = new TextField();

        
        this.mailPasswordBox.getChildren().addAll (mailLabel, mailTextField, passwordLabel, passwordTextField, passAgainLabel, passAgainTextField);
    
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
