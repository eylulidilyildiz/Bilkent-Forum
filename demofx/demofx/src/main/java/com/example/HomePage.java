package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
        Label forumLabel = new Label(" BILKENT FORUM ");
        forumLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        forumLabel.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));


        //labels of the menu
        Label discoverLabel = new Label("Discover");
        discoverLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));

        Label homeLabel = new Label("Home");
        homeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        Label profileLabel = new Label("Profile");
        profileLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        Label browseLabel = new Label("Browse");
        browseLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));

        Label libraryLabel = new Label("Library");
        libraryLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));

        Label upvotedPostsLabel = new Label("Upvoted Posts");
        upvotedPostsLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        Label bookmarksLabel = new Label("Bookmarks");
        bookmarksLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));


        Button logoutButton = new Button("LOGOUT");
        logoutButton.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        logoutButton.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        logoutButton.setPrefWidth(160);


        //root and scene
        BorderPane root = new BorderPane();
        
        GridPane menuPane = new GridPane();
        menuPane.setPrefWidth(200);
        menuPane.setHgap(20);
        menuPane.setVgap(100);
        //menuPane.setPadding(new Insets(-10));
        menuPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,  null, null)));
        menuPane.setStyle("-fx-background-color: #CD5C5C;");

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setPercentWidth(300);       
        menuPane.getColumnConstraints().addAll(columnConstraints);

        VBox discoverBox = new VBox();
        discoverBox.setPadding(new Insets(10));
        discoverBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        discoverBox.getChildren().addAll(discoverLabel, homeLabel, profileLabel, browseLabel);

        VBox libraryBox = new VBox();
        libraryBox.setPadding(new Insets(10));
        libraryBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        libraryBox.getChildren().addAll(libraryLabel, upvotedPostsLabel, bookmarksLabel);

        menuPane.add(forumLabel, 1, 1);
        menuPane.add(discoverBox, 1, 2);
        menuPane.add(libraryBox, 1, 3);
        menuPane.add(logoutButton, 1, 5);

        //root.setAlignment(menuPane, Pos.CENTER_LEFT);

        root.getChildren().add(menuPane);

        Scene homeScene = new Scene(root, 700, 700);

        //stage
        homeStage.setScene(homeScene);
        homeStage.setMaximized(true);
        homeStage.setTitle("Bilkent Forum Home Page");
        homeStage.show();

    }
    
}

