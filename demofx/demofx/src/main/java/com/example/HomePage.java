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
import javafx.scene.image.ImageView;
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


        // SECTIONS OF THE MENU
        // discovery
        Button discoverButton = new Button ("Discovery");
        discoverButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));
        discoverButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        discoverButton.setPrefWidth (250);
        discoverButton.setAlignment (Pos.TOP_LEFT);
        discoverButton.setTextFill (Color.rgb (101, 14, 63));

        // home
        Button homeButton = new Button ("Home");
        homeButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        homeButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        homeButton.setPrefWidth (250);
        homeButton.setAlignment (Pos.TOP_LEFT);

        // when mouse enters the home button
        homeButton.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               homeButton.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        // when mouse exits the home button
        homeButton.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               homeButton.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });

        // ImageView of HOME
        ImageView homeIcon = new ImageView (getClass().getResource("images/homeIcon.png").toString());
        homeIcon.setFitHeight (20);
        homeIcon.setFitWidth (20);
        

        // The whole home section with the image
        HBox homeBox = new HBox ();
        homeBox.getChildren().addAll (homeIcon, homeButton);
        homeBox.setAlignment (Pos.CENTER); // Aligning the image and button

        homeBox.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent arg0) 
            {
               homeBox.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        homeBox.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               homeBox.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });


        // profile
        Button profileButton = new Button ("Profile");
        profileButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        profileButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        profileButton.setPrefWidth (250);
        profileButton.setAlignment (Pos.TOP_LEFT);

        // when mouse enters the profile button
        profileButton.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               profileButton.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        // when mouse exits the profile button
        profileButton.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               profileButton.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });

        // ImageView of PROFILE
        ImageView profileIcon = new ImageView (getClass().getResource("images/profileIcon.png").toString());
        profileIcon.setFitHeight (20);
        profileIcon.setFitWidth (20);

        HBox profileBox = new HBox();
        profileBox.getChildren().addAll (profileIcon, profileButton);
        profileBox.setAlignment (Pos.CENTER);

        profileBox.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               profileBox.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        profileBox.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               profileBox.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });

        // browse
        Button browseButton = new Button ("Browse");
        browseButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        browseButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        browseButton.setPrefWidth (250);
        browseButton.setAlignment (Pos.TOP_LEFT);

        // when mouse enters the browse button 
        browseButton.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               browseButton.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        // when mouse exists the browse button 
        browseButton.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               browseButton.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });

        // ImageView of BROWSE
        ImageView browseIcon = new ImageView (getClass().getResource("images/browseIcon.png").toString());
        browseIcon.setFitHeight (20);
        browseIcon.setFitWidth (20);

        HBox browseBox = new HBox();
        browseBox.getChildren().addAll (browseIcon, browseButton);
        browseBox.setAlignment (Pos.CENTER);

        browseBox.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               browseBox.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        browseBox.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               browseBox.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });

        // library
        Button libraryButton = new Button ("Library");
        libraryButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));
        libraryButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        libraryButton.setPrefWidth (250);
        libraryButton.setAlignment (Pos.TOP_LEFT);
        libraryButton.setTextFill (Color.rgb (101, 14, 63));


        // upvoted
        Button upvotedButton = new Button ("Upvoted Posts");
        upvotedButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        upvotedButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        upvotedButton.setPrefWidth (250);
        upvotedButton.setAlignment (Pos.TOP_LEFT);

        // when mouse enters the upvoted button 
        upvotedButton.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               upvotedButton.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        // when mouse exists the upvoted button
        upvotedButton.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               upvotedButton.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });

        // ImageView of UPVOTE
        ImageView upvotedIcon = new ImageView (getClass().getResource("images/upvoteIcon.png").toString());
        upvotedIcon.setFitHeight (20);
        upvotedIcon.setFitWidth (20);

        HBox upvotedBox = new HBox();
        upvotedBox.getChildren().addAll (upvotedIcon, upvotedButton);
        upvotedBox.setAlignment (Pos.CENTER);

        upvotedBox.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               upvotedBox.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        upvotedBox.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               upvotedBox.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });

        // bookmarks
        Button bookmarksButton = new Button ("Bookmarks");
        bookmarksButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        bookmarksButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        bookmarksButton.setPrefWidth (250);
        bookmarksButton.setAlignment (Pos.TOP_LEFT);

        // when mouse enters the bookmark button 
        bookmarksButton.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               bookmarksButton.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });
        
        // when mouse exists the bookmarks button 
        bookmarksButton.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               bookmarksButton.setBackground (new Background (new BackgroundFill (null, null, null)));
            }
            
        });

        // ImageView of BOOKMARKS
        ImageView bookmarksIcon = new ImageView (getClass().getResource("images/bookmarkIcon.png").toString());
        bookmarksIcon.setFitHeight (20);
        bookmarksIcon.setFitWidth (20);

        HBox bookmarksBox = new HBox();
        bookmarksBox.getChildren().addAll (bookmarksIcon, bookmarksButton);
        bookmarksBox.setAlignment (Pos.CENTER);

        bookmarksBox.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               bookmarksBox.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        bookmarksBox.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent arg0) 
            {
               bookmarksBox.setBackground (new Background (new BackgroundFill (null, null, null)));
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
        menuPane.getColumnConstraints().addAll (columnConstraints);

        VBox discoverBox = new VBox();
        discoverBox.setPadding(new Insets(10));
        discoverBox.getChildren().addAll (discoverButton, homeBox, profileBox, browseBox);

        VBox libraryBox = new VBox();
        libraryBox.setPadding(new Insets(10));
        libraryBox.getChildren().addAll (libraryButton, upvotedBox, bookmarksBox);

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

