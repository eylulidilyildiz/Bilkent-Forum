package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class ProfileBox extends VBox
{
    // CONSTANTS
    final int ICON_WIDTH = 20;
    final int ICON_HEIGHT = 20;

    // Instance Variables 
    private User mainUser;
    private ScrollPane postsBox;
    private Button editProfileButton;
    private HBox informationBox;
    private ImageView profileIcon;
    private HBox searchAddPostBox;
    
    // Constructor
    public ProfileBox (User user)
    {
        super();
        this.mainUser = user;

        this.createSearchAndAddPost();
        this.createInfoPane();
    }

    // Methods
    private void createPostsBox ()
    {
        
    }

    private void createInfoPane ()
    {
        informationBox = new HBox();
        informationBox.setAlignment (Pos.CENTER);

        VBox informationTextBox = new VBox();
        informationTextBox.setPadding (new Insets(10));
        informationTextBox.setAlignment (Pos.CENTER_LEFT);

        // Name Label
        Label nameLabel = new Label ("Ayça Topkaya");
        nameLabel.setFont (Font.font ("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 45));


        // University Label
        Label universityLabel = new Label ("Bilkent University");
        universityLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 30));

        // Department Label
        Label departmentLabel = new Label ("CS");
        departmentLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        

        // Semester Label
        Label semesterLabel = new Label ("Freshman");
        semesterLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 30));

        // adding the components together
        informationTextBox.getChildren().addAll (nameLabel, universityLabel, departmentLabel, semesterLabel);

        editProfileButton = new Button ("Edit Profile");
        editProfileButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));
        editProfileButton.setPrefHeight (60);
        editProfileButton.setPrefWidth (200);
        
        VBox textAndProfileBox = new VBox();
        textAndProfileBox.setPadding (new Insets(10));
        textAndProfileBox.setAlignment (Pos.CENTER_RIGHT);

        textAndProfileBox.getChildren().addAll (informationTextBox, editProfileButton);
        

        // ImageView for PROFILE
        profileIcon = new ImageView (getClass().getResource("images/profileIcon.png").toString());
        profileIcon.setFitHeight (400);
        profileIcon.setFitWidth (400);


        
        // adding the info pane to the profile box
        this.informationBox.getChildren().addAll (profileIcon, textAndProfileBox);
        this.informationBox.setSpacing (200);
        

        this.getChildren().add (this.informationBox);


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

    private void editProfileIsClicked (Button button)
    {
        button.setOnAction (new EventHandler <ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent arg0) 
            {
                EditProfileBox editProfileBox = new EditProfileBox (mainUser);
            }
            
        });
    }
}
