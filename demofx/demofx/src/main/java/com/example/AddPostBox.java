package com.example;

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

public class AddPostBox extends VBox {
    // Constants
    private static final double ICON_HEIGHT = 20;
    private static final double ICON_WIDTH = 20;

    // Instance Variables
    private User mainUser;
    private HBox searchAddPostBox;
    private VBox nameDepartmentSemesterBox;
    private VBox mailPasswordBox;
    private Button saveButton;

    public AddPostBox(User user)
    {
        super();
        mainUser = user;
        this.createSearchAndAddPost();

        HBox addPostBox = new HBox();
        addPostBox.setSpacing (500);
        //addPostBox.getChildren().addAll (this.nameDepartmentSemesterBox, this.mailPasswordBox);
        this.getChildren().add (addPostBox);
    }

     @SuppressWarnings("unchecked")
    private void createNameDepartmentSemesterBox ()
    {
        nameDepartmentSemesterBox = new VBox();
        nameDepartmentSemesterBox.setAlignment (Pos.CENTER_LEFT);

        // name label and text field
       
        //nameDepartmentSemesterBox.getChildren().addAll (nameLabel, nameTextField, departmentLabel, departmentTextField, semesterLabel, semesterBox);


    }

    private void createMailPasswordBox ()
    {
        this.mailPasswordBox = new VBox();
        this.mailPasswordBox.setAlignment (Pos.CENTER_LEFT);
        
        //this.mailPasswordBox.getChildren().addAll (mailLabel, mailTextField, passwordLabel, passwordTextField, passAgainLabel, passAgainTextField);
    
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
