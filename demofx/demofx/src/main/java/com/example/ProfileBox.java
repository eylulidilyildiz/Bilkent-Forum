package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    private Button editProfileButton;
    private HBox informationBox;
    private ImageView profileIcon;
    private BorderPane root;
    private boolean isFriend;
    
    // Constructor
    public ProfileBox (User user, @SuppressWarnings("exports") BorderPane root, boolean isFriend)
    {
        super();
        this.mainUser = user;
        this.root = root;
        this.isFriend = isFriend;

        
        this.createInfoPane();
    }

 
    private void createInfoPane ()
    {
        informationBox = new HBox();
        informationBox.setPadding (new Insets (150));
        informationBox.setAlignment (Pos.CENTER);

        VBox informationTextBox = new VBox();
        informationTextBox.setPadding (new Insets(10));
        informationTextBox.setAlignment (Pos.CENTER_LEFT);

        // Name Label
        Label nameLabel = new Label (mainUser.getName() + " " + mainUser.getSurname());
        nameLabel.setFont (Font.font ("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 45));


        // University Label
        Label universityLabel = new Label ("Bilkent University");
        universityLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 30));

        // Department Label
        Label departmentLabel = new Label (mainUser.getDepartment());
        departmentLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        

        // Semester Label
        Label semesterLabel;
        if(mainUser.getSemester() == 1)
        {
            semesterLabel = new Label ("Freshman");
        }
        else if(mainUser.getSemester() == 2)
        {
            semesterLabel = new Label("Sophomore");
        }
        else if(mainUser.getSemester() == 3)
        {
            semesterLabel = new Label("Junior");
        }
        else{ //semester == 4
            semesterLabel = new Label ("Senior");
        }
        semesterLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 30));

        // Email Label
        Label emailLabel = new Label ("Contact: " + mainUser.getEmail() );
        emailLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 30));

        // adding the components together
        informationTextBox.getChildren().addAll (nameLabel, universityLabel, departmentLabel, semesterLabel, emailLabel);

        // ImageView for PROFILE
        profileIcon = new ImageView (getClass().getResource("images/profileIcon.png").toString());
        profileIcon.setFitHeight (400);
        profileIcon.setFitWidth (400);

        VBox textAndProfileBox = new VBox();
        textAndProfileBox.setPadding (new Insets(10));
        textAndProfileBox.setAlignment (Pos.CENTER_RIGHT);

        if (!isFriend)
        {
            editProfileButton = new Button ("Edit Profile");
            editProfileButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));
            editProfileButton.setPrefHeight (60);
            editProfileButton.setPrefWidth (200);

            this.editProfileIsClicked(editProfileButton);

            textAndProfileBox.getChildren().addAll (informationTextBox, editProfileButton);
        }
        else
        {
            textAndProfileBox.getChildren().addAll (informationTextBox);
        }

        // adding the info pane to the profile box
        this.informationBox.getChildren().addAll (profileIcon, textAndProfileBox);
        this.informationBox.setSpacing (200);

        this.getChildren().add (this.informationBox);
    }


    private void editProfileIsClicked (Button button)
    {
        button.setOnAction (new EventHandler <ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent arg0) 
            {
                EditProfileBox editProfileBox = new EditProfileBox (mainUser, root);
                root.setCenter (editProfileBox);
            }
            
        });
    }
}
