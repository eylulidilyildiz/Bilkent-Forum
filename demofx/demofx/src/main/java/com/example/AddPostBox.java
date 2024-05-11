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
    private HBox searchBox;
    private VBox postInfoBox;
    private VBox DescriptionAndPostTypeBox;
    private HBox BookDetailsBox;
    private VBox InfoFieldsBox;
    private VBox CourseUsageAndPriceBox;
    private Button createPostButton;

    public AddPostBox(User user)
    {
        super();
        mainUser = user;
        this.createSearchBox();

        HBox addPostBox = new HBox();
        addPostBox.setSpacing (500);
        addPostBox.getChildren().addAll (this.nameDepartmentSemesterBox, this.mailPasswordBox);
        this.getChildren().add (addPostBox);
    }




     private void createSearchBox ()
    {
        this.searchBox = new HBox();

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

        Label searchLabel = new Label ("Search:");
        searchLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        searchPane.add (searchLabel, 1, 1);

        searchBox.setSpacing (550);
        searchBox.getChildren().add(searchPane);
        this.getChildren().add (searchBox);
        
    }

}
