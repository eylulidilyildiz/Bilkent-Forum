package com.example;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookmarkedPostsBox extends VBox
{
    public BookmarkedPostsBox(User user)
    {
        super();

        HBox bookmarkedPostsBox = new HBox();
        bookmarkedPostsBox.setSpacing (500);

        bookmarkedPostsBox.getChildren().addAll();
        //this.getChildren().add();
    }    
}