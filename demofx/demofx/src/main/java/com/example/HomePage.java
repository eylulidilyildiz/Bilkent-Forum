package com.example;

import org.hibernate.Session;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
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
        forumLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 27));
        forumLabel.setBackground(new Background(new BackgroundFill (Color.rgb (248, 200, 220), null, null))); // pink
        forumLabel.setPrefWidth(248);
        forumLabel.setAlignment(Pos.CENTER);

        //labels of the menu
        Label discoverLabel = new Label("Discover");
        discoverLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 24));
        discoverLabel.setTextFill (Color.rgb (101, 14, 63));

        // home, profile, browse
        Label homeLabel = new Label("Home");
        homeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        Label profileLabel = new Label("Profile");
        profileLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        Label browseLabel = new Label("Browse");
        browseLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

       
        // library
        Label libraryLabel = new Label("Library");
        libraryLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 24));
        libraryLabel.setTextFill (Color.rgb (101, 14, 63));

        // upvoted, bookmarks
        Label upvotedPostsLabel = new Label("Upvoted Posts");
        upvotedPostsLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        Label bookmarksLabel = new Label("Bookmarks");
        bookmarksLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));


        // PROFILE PAGE / SCENE
        // when profile is clicked
        profileLabel.setOnMouseClicked (new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle (MouseEvent event)
            {
               
            }
        });

        
        // BROWSE PAGE / SCENE
        // when browse is clicked
        browseLabel.setOnMouseClicked(new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle (MouseEvent event)
            {
               
            }

        });
        
        // UPVOTED SCENE
        // when upvoted is clicked
        upvotedPostsLabel.setOnMouseClicked(new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle (MouseEvent event)
            {
               
            }

        });
        
        // BOOKMARKED SCENE
        // when bookmarks is clicked
        bookmarksLabel.setOnMouseClicked(new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle (MouseEvent event)
            {
               
            }

        });
        
        
        
        // LOGOUT
        Button logoutButton = new Button("LOGOUT");
        //logoutButton.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        logoutButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));

        // changing color ???
        logoutButton.setStyle ("-fx-background-color: pink");
        logoutButton.setEffect (new DropShadow());
        logoutButton.setPrefWidth(250);
        
        // when button is clicked it logs out, closes the home page
        logoutButton.setOnAction (new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle (ActionEvent arg0) 
            {
                homeStage.close();
                
                // OR maybe open the login page ???
            }
            
        });

        

        /* SHADOWING OF LABELS */

        // Profile Label
        // when the mouse enters the profile label
        profileLabel.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle (MouseEvent event)
            {
                profileLabel.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
        });

        // when the mouse exists the profile label
        profileLabel.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle (MouseEvent event) 
            {
                profileLabel.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
            
        });

        // Browse label
        // when the mouse enters the browse label
        browseLabel.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle (MouseEvent event)
            {
                browseLabel.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
        });

        // when the mouse exists the browse label
        browseLabel.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle (MouseEvent event) 
            {
                browseLabel.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
            
        });


        // Upvoted label
        // when the mouse enters the upvoted label
        upvotedPostsLabel.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle (MouseEvent event)
            {
                upvotedPostsLabel.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
         });
 
        // when the mouse exists the upvoted label
        upvotedPostsLabel.setOnMouseExited (new EventHandler <MouseEvent>() 
        {
 
            @Override
            public void handle (MouseEvent event) 
            {
                upvotedPostsLabel.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
             
             
        });

        // Bookmarks label
        // when the mouse enters the bookmarks label
        bookmarksLabel.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle (MouseEvent event)
            {
               bookmarksLabel.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
        });
    
        // when the mouse exists the bookmarks label
        bookmarksLabel.setOnMouseExited (new EventHandler <MouseEvent>() 
        {
    
           @Override
            public void handle (MouseEvent event) 
            {
              bookmarksLabel.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
                
                
        });

        //root and scene
        BorderPane root = new BorderPane();
        
        GridPane menuPane = new GridPane();
        menuPane.setPrefWidth(250);
        menuPane.setHgap(20);
        menuPane.setVgap(100);

        menuPane.setBackground(new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        menuPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px 1px 0 0;");

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

        friendsPane.setBackground(new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        friendsPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px 1px 1px 1px;");

        root.setRight(friendsPane);


        //POSTS

        VBox postsBox = new VBox();
        postsBox.setAlignment(Pos.CENTER);
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
                //currentPost.setAlignment(Pos.CENTER);

                postsBox.getChildren().add(currentPost);
                i++;
            }

        //while
        
            
        ScrollPane postsPane = new ScrollPane();
        postsBox.setAlignment(Pos.CENTER);
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



    /* HELPER METHODS */
    public void createPost(VBox box, Post post, String username)
    {
        String description = post.getContent();
        String date = post.getDate();
        boolean isSalePost = post.getIsSalePost();
        int numOfUpvotes = post.getUpvotes();
        int numOfDownvotes = post.getDownvotes();

        Button upvoteButton = new Button("Upvote");
        Label upvotesLabel = new Label("" + numOfUpvotes);
                
        upvoteButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override 
            public void handle(ActionEvent event) 
            {
                post.setUpvotes(numOfUpvotes + 1);
                upvotesLabel.setText("" + numOfUpvotes);
            } 
        });

        Label usernameLabel = new Label(username + " " + date);

        TextArea postContent = new TextArea();
        postContent.setPrefSize(500, 100);
        postContent.setText(description);
        postContent.setEditable(false);

        box.getChildren().addAll(usernameLabel, postContent, upvoteButton, upvotesLabel);
    }
    
}

