package com.example;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class FriendsPane extends GridPane
{
    private static final double WIDTH_PANE = 250;

    // Constructor
    public FriendsPane ()
    {
        this.setPrefWidth (WIDTH_PANE);
        this.setHgap(20);
        this.setVgap(100);

        this.setBackground(new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setStyle("-fx-border-color: gray; -fx-border-width: 1px 1px 1px 1px;");

    }
}
