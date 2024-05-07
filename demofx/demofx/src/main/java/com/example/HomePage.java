package com.example;

import org.hibernate.Session;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.shape.Box;
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

        //MENU ON THE LEFT

        //Components of the Menu
        Label forumLabel = new Label(" BILKENT FORUM ");
        forumLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 27));
        forumLabel.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        forumLabel.setPrefWidth(248);
        forumLabel.setAlignment(Pos.CENTER);

        //labels of the menu
        Label discoverLabel = new Label("Discover");
        discoverLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));

        Label homeLabel = new Label("Home");
        homeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        Label profileLabel = new Label("Profile");
        profileLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        Label browseLabel = new Label("Browse");
        browseLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        Label libraryLabel = new Label("Library");
        libraryLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));

        Label upvotedPostsLabel = new Label("Upvoted Posts");
        upvotedPostsLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        Label bookmarksLabel = new Label("Bookmarks");
        bookmarksLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        Button logoutButton = new Button("LOGOUT");
        //logoutButton.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        logoutButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 22));
        logoutButton.setPrefWidth(250);

     
        //root and scene
        BorderPane root = new BorderPane();
        
        GridPane menuPane = new GridPane();
        menuPane.setPrefWidth(250);
        menuPane.setHgap(20);
        menuPane.setVgap(100);

        menuPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        menuPane.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0 0;");

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setPercentWidth(500);       
        menuPane.getColumnConstraints().addAll(columnConstraints);

        VBox discoverBox = new VBox();
        discoverBox.setPadding(new Insets(10));
        discoverBox.getChildren().addAll(discoverLabel, homeLabel, profileLabel, browseLabel);

        VBox libraryBox = new VBox();
        libraryBox.setPadding(new Insets(10));
        libraryBox.getChildren().addAll(libraryLabel, upvotedPostsLabel, bookmarksLabel);

        menuPane.add(forumLabel, 0, 1);
        menuPane.add(discoverBox, 0, 2);
        menuPane.add(libraryBox, 0, 3);
        menuPane.add(logoutButton, 0, 5);

        root.setLeft(menuPane);

        
        GridPane friendsPane = new GridPane();
        friendsPane.setPrefWidth(250);
        friendsPane.setHgap(20);
        friendsPane.setVgap(100);

        friendsPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        friendsPane.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px;");

        root.setRight(friendsPane);


        //POSTS

        VBox postsBox = new VBox();
        VBox currentPost;

        DatabaseConnection.connect(); 

        try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
        {
            int i = 1;
            while(session.get(Post.class, i) != null)
            {
                Post post = session.get(Post.class, i);
                int ownerID = post.getOwnerID();
                String username = session.get(User.class, ownerID).getUsername();

                
                currentPost = new VBox();

                createPost(currentPost, post, username);

                postsBox.getChildren().add(currentPost);
                i++;
            }

        //while
        
            
        ScrollPane postsPane = new ScrollPane();
        postsPane.setContent(postsBox);
        postsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        root.setCenter(postsPane);



        //stage
        Scene homeScene = new Scene(root, 700, 700);

        homeStage.setScene(homeScene);
        homeStage.setMaximized(true);
        homeStage.setTitle("Bilkent Forum Home Page");
        homeStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect(); 
        }

    }

    public void createPost(VBox box, Post post, String username)
    {
        String description = post.getContent();
        String date = post.getDate();
        boolean isSalePost = post.getIsSalePost();
        int numOfUpvotes = post.getUpvotes();
        int numOfDownvotes = post.getDownvotes();

        Label usernameLabel = new Label(username);

        box.getChildren().add(usernameLabel);
    }
    
}

