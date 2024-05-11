package com.example;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EditProfileBox extends GridPane
{
    // Instance Variables
    private User mainUser;
    private VBox nameDepartmentSemesterBox;
    private VBox mailPasswordBox;
    private Button saveButton;

    // Constructor
    public EditProfileBox (User user)
    {
        super();
        this.mainUser = user;


    }

    
}
