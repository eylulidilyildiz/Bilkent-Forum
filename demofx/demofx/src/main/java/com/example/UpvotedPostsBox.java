package com.example;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UpvotedPostsBox extends VBox
{
    public UpvotedPostsBox(User user)
    {
        super();

        HBox upvotedPostsBox = new HBox();
        upvotedPostsBox.setSpacing (500);

        upvotedPostsBox.getChildren().addAll();
        //this.getChildren().add();
    }    
}