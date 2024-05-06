package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.*;

public class HomePage extends Application 
{

    public static void main(String[] args) 
    {
        launch(args);
    }
    

    @Override
    public void start(Stage homeStage) throws Exception {

        //Components of the Menu
        Label forumLabel = new Label("BILKENT FORUM");
        forumLabel.setPrefSize(100, 50);

        Label discoverLabel = new Label("Discover");
        discoverLabel.setPrefWidth(50);
        Label homeLabel = new Label("Home");
        Label profileLabel = new Label("Profile");
        Label browseLabel = new Label("Browse");

        Label libraryLabel = new Label("Library");
        Label upvotedPostsLabel = new Label("Upvoted Posts");
        Label bookmarksLabel = new Label("Bookmarks");

        Button logoutButton = new Button("LOGOUT");


        //root and scene
        BorderPane root = new BorderPane();
        
        GridPane menuPane = new GridPane();
        menuPane.setPrefWidth(200);
        menuPane.setHgap(10);
        menuPane.setVgap(100);
        menuPane.setPadding(new Insets(-10));

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(300); // Adjust as needed
        menuPane.getColumnConstraints().addAll(columnConstraints);

        VBox discoverBox = new VBox();
        discoverBox.getChildren().addAll(discoverLabel, homeLabel, profileLabel, browseLabel);

        VBox libraryBox = new VBox();
        libraryBox.getChildren().addAll(libraryLabel, upvotedPostsLabel, bookmarksLabel);

        menuPane.add(forumLabel, 1, 1);
        menuPane.add(discoverBox, 1, 2);
        menuPane.add(libraryBox, 1, 3);
        menuPane.add(logoutButton, 1, 5);

        root.setAlignment(menuPane, Pos.CENTER_LEFT);

        root.getChildren().add(menuPane);

        Scene homeScene = new Scene(root, 700, 700);

        //stage
        homeStage.setScene(homeScene);
        homeStage.setFullScreen(true);
        homeStage.setTitle("Login to Bilkent Forum");
        homeStage.show();

    }
    
}

