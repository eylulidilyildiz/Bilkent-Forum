package com.example;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookmarkedPostsBox extends VBox
{
    private User mainUser;

    public BookmarkedPostsBox(User user)
    {
        super();
        mainUser = user;

        HBox bookmarkedPostsBox = new HBox();
        bookmarkedPostsBox.setSpacing (500);

        bookmarkedPostsBox.getChildren().addAll();
        //this.getChildren().add();
    }    
}