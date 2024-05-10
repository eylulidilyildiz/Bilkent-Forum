package com.example;


import org.hibernate.Session;
import org.hibernate.Transaction;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
import javafx.scene.layout.StackPane;
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
    // Instance Variables for buttons
    private boolean isHomeClicked = false;
    private boolean isProfileClicked = false;
    private boolean isBrowseClicked = false;
    private boolean isUpvotedClicked = false;
    private boolean isBookmarksClicked = false;

    // Main User for the application
    User mainUser;

    public HomePage(User user)
    {
        mainUser = user;
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    

   @Override
    public void start (Stage homeStage) throws Exception {

        // CONSTANTS
        final int WIDTH_MENU_PANE = 250;
        final int ICON_WIDTH = 20;
        final int ICON_HEIGHT = 20;

        //MENU ON THE LEFT

        //Components of the Menu
        Label forumLabel = new Label(" BILKENT FORUM ");
        forumLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 27));
        forumLabel.setBackground(new Background(new BackgroundFill (Color.rgb (248, 200, 220), null, null))); // pink
        forumLabel.setPrefWidth(248);
        forumLabel.setAlignment(Pos.CENTER);

        // SECTIONS OF THE MENU
        // discovery
        ToggleButton discoverButton = new ToggleButton ("Discovery");
        discoverButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));
        discoverButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        discoverButton.setPrefWidth (WIDTH_MENU_PANE);
        discoverButton.setAlignment (Pos.TOP_LEFT);
        discoverButton.setTextFill (Color.rgb (101, 14, 63));

        // home
        ToggleButton homeButton = new ToggleButton ("Home");
        homeButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        homeButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        homeButton.setPrefWidth (WIDTH_MENU_PANE);
        homeButton.setAlignment (Pos.TOP_LEFT);
        homeButton.setSelected(true);


        // ImageView of HOME
        ImageView homeIcon = new ImageView (getClass().getResource("images/homeIcon.png").toString());
        homeIcon.setFitHeight (ICON_HEIGHT);
        homeIcon.setFitWidth (ICON_WIDTH);
        

        // The whole home section with the image
        HBox homeBox = new HBox ();
        homeBox.getChildren().addAll (homeIcon, homeButton);
        homeBox.setAlignment (Pos.CENTER); // Aligning the image and button
        colorBackground(homeButton, homeBox);

        // shadowing effect - mouse entering, exiting button
        enteringExitingButton (homeButton, homeBox);
        

        // profile
        ToggleButton profileButton = new ToggleButton ("Profile");
        profileButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        profileButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        profileButton.setPrefWidth (WIDTH_MENU_PANE);
        profileButton.setAlignment (Pos.TOP_LEFT);

        // ImageView of PROFILE
        ImageView profileIcon = new ImageView (getClass().getResource("images/profileIcon.png").toString());
        profileIcon.setFitHeight (ICON_HEIGHT);
        profileIcon.setFitWidth (ICON_WIDTH);

        HBox profileBox = new HBox();
        profileBox.getChildren().addAll (profileIcon, profileButton);
        profileBox.setAlignment (Pos.CENTER);

        // shadowing effect - mouse entering, exiting button
        enteringExitingButton (profileButton, profileBox);
    

        // browse
        ToggleButton browseButton = new ToggleButton ("Browse");
        browseButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        browseButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        browseButton.setPrefWidth (WIDTH_MENU_PANE);
        browseButton.setAlignment (Pos.TOP_LEFT);

        // ImageView of BROWSE
        ImageView browseIcon = new ImageView (getClass().getResource("images/browseIcon.png").toString());
        browseIcon.setFitHeight (ICON_HEIGHT);
        browseIcon.setFitWidth (ICON_WIDTH);

        HBox browseBox = new HBox();
        browseBox.getChildren().addAll (browseIcon, browseButton);
        browseBox.setAlignment (Pos.CENTER);

        // shadowing effect - mouse entering, exiting button
        enteringExitingButton (browseButton, browseBox);
        

        // library
        ToggleButton libraryButton = new ToggleButton ("Library");
        libraryButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));
        libraryButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        libraryButton.setPrefWidth (WIDTH_MENU_PANE);
        libraryButton.setAlignment (Pos.TOP_LEFT);
        libraryButton.setTextFill (Color.rgb (101, 14, 63));

        // upvoted
        ToggleButton upvotedButton = new ToggleButton ("Upvoted Posts");
        upvotedButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        upvotedButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        upvotedButton.setPrefWidth (WIDTH_MENU_PANE);
        upvotedButton.setAlignment (Pos.TOP_LEFT);

        // ImageView of UPVOTE
        ImageView upvotedIcon = new ImageView (getClass().getResource("images/upvoteIcon.png").toString());
        upvotedIcon.setFitHeight (ICON_HEIGHT);
        upvotedIcon.setFitWidth (ICON_WIDTH);

        HBox upvotedBox = new HBox();
        upvotedBox.getChildren().addAll (upvotedIcon, upvotedButton);
        upvotedBox.setAlignment (Pos.CENTER);

        // shadowing effect - mouse entering, exiting button
        enteringExitingButton (upvotedButton, upvotedBox);


        // bookmarks
        ToggleButton bookmarksButton = new ToggleButton ("Bookmarks");
        bookmarksButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        bookmarksButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        bookmarksButton.setPrefWidth (WIDTH_MENU_PANE);
        bookmarksButton.setAlignment (Pos.TOP_LEFT);

        // ImageView of BOOKMARKS
        ImageView bookmarksIcon = new ImageView (getClass().getResource("images/bookmarkIcon.png").toString());
        bookmarksIcon.setFitHeight (ICON_HEIGHT);
        bookmarksIcon.setFitWidth (ICON_WIDTH);

        HBox bookmarksBox = new HBox();
        bookmarksBox.getChildren().addAll (bookmarksIcon, bookmarksButton);
        bookmarksBox.setAlignment (Pos.CENTER);

        // shadowing effect - mouse entering, exiting button
        enteringExitingButton (bookmarksButton, bookmarksBox);
        

        ToggleGroup menuPaneGroup = new ToggleGroup();
        menuPaneGroup.getToggles().addAll(homeButton, profileButton, browseButton, upvotedButton, bookmarksButton);
        //homeButton.setSelected(true);
        
        homeButton.setToggleGroup(menuPaneGroup);
        profileButton.setToggleGroup(menuPaneGroup);
        browseButton.setToggleGroup(menuPaneGroup);
        upvotedButton.setToggleGroup(menuPaneGroup);
        bookmarksButton.setToggleGroup(menuPaneGroup);

        homeButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // UI way of showing the button is selected
                homeButton.setSelected(true);

                colorBackground(homeButton, homeBox);
                discolorBackground(profileButton, profileBox);
                discolorBackground(browseButton, browseBox);
                discolorBackground(upvotedButton, upvotedBox);
                discolorBackground(bookmarksButton, bookmarksBox);
            }
        });

        profileButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // UI way of showing the button is selected
                profileButton.setSelected(true);

                colorBackground(profileButton, profileBox);
                discolorBackground(homeButton, homeBox);
                discolorBackground(browseButton, browseBox);
                discolorBackground(upvotedButton, upvotedBox);
                discolorBackground(bookmarksButton, bookmarksBox);
            }
        });

        browseButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // UI way of showing the button is selected
                browseButton.setSelected(true);

                colorBackground(browseButton, browseBox);
                discolorBackground(homeButton, homeBox);
                discolorBackground(profileButton, profileBox);
                discolorBackground(upvotedButton, upvotedBox);
                discolorBackground(bookmarksButton, bookmarksBox);
            }
        });

        upvotedButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // UI way of showing the button is selected
                upvotedButton.setSelected(true);

                colorBackground(upvotedButton, upvotedBox);
                discolorBackground(profileButton, profileBox);
                discolorBackground(browseButton, browseBox);
                discolorBackground(homeButton, homeBox);
                discolorBackground(bookmarksButton, bookmarksBox);

            }
        });

        bookmarksButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // UI way of showing the button is selected
                bookmarksButton.setSelected(true);

                colorBackground(bookmarksButton, bookmarksBox);
                discolorBackground(profileButton, profileBox);
                discolorBackground(browseButton, browseBox);
                discolorBackground(homeButton, homeBox);
                discolorBackground(upvotedButton, upvotedBox);


            }
        });

        // LOGOUT
        Button logoutButton = new Button("LOGOUT");
        //logoutButton.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        logoutButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));

        // changing color ???
        logoutButton.setStyle ("-fx-background-color: pink");
        logoutButton.setEffect (new DropShadow());
        logoutButton.setPrefWidth(WIDTH_MENU_PANE);
        
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
        menuPane.setPrefWidth(WIDTH_MENU_PANE);
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
        friendsPane.setPrefWidth(WIDTH_MENU_PANE);
        friendsPane.setHgap(20);
        friendsPane.setVgap(100);

        friendsPane.setBackground(new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        friendsPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px 1px 1px 1px;");

        root.setRight(friendsPane);


        // SEARCH BAR
        GridPane searchPane = new GridPane();
        searchPane.setAlignment (Pos.TOP_LEFT);
        searchPane.setHgap (10);
        searchPane.setVgap (10);
        searchPane.setPadding(new Insets(70));

        TextField searchBar = new TextField();
        searchBar.setPrefHeight (40);
        searchBar.setPrefWidth (400);
        searchPane.add (searchBar, 2, 1);
        
        ImageView searchIcon = new ImageView (getClass().getResource("images/browseIcon.png").toString());
        searchIcon.setFitHeight (ICON_HEIGHT);
        searchIcon.setFitWidth (ICON_WIDTH);
        searchPane.add (searchIcon, 0, 1); // image

        Label searchLabel = new Label ("Search:");
        searchLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        searchPane.add (searchLabel, 1, 1);


        // ADD POST BUTTON
        GridPane postButtonPane = new GridPane();
        postButtonPane.setAlignment (Pos.TOP_RIGHT);
        postButtonPane.setHgap (10);
        postButtonPane.setVgap (10);
        postButtonPane.setPadding(new Insets(70));

        ToggleButton addPostButton = new ToggleButton ();
        addPostButton.setBackground (new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
        addPostButton.setPrefHeight (60);
        addPostButton.setPrefWidth (60);

        ImageView plusIcon = new ImageView (getClass().getResource("images/plusIcon.png").toString());
        plusIcon.setFitHeight (60);
        plusIcon.setFitWidth (60);

        addPostButton.setGraphic (plusIcon);

        postButtonPane.add (addPostButton, 0, 1);

        // combining the searchPane and the addPostButton 
        HBox searchAddPostBox = new HBox();
        searchAddPostBox.setSpacing (550);
        searchAddPostBox.getChildren().addAll (searchPane, postButtonPane);
        
       
        //POSTS

        VBox postsBox = new VBox();
        postsBox.setSpacing(30);
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

                currentPost.setPrefSize(500, 500);
                createPost(currentPost, post, username);
                currentPost.setAlignment(Pos.CENTER);

                postsBox.getChildren().add(currentPost);
        
                i++;
            }

        //while
        
            
        ScrollPane postsPane = new ScrollPane();
        postsPane.setPadding(new Insets(70));
        postsBox.setAlignment(Pos.CENTER);
        postsPane.setContent(postsBox);
        postsPane.setFitToWidth (true);
        postsPane.setFitToHeight (true);
        postsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // combining the searchPane and postsPane
        VBox homePageBox = new VBox();
        homePageBox.getChildren().addAll (searchAddPostBox, postsPane);

        root.setCenter (homePageBox);

        

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
            //DatabaseConnection.disconnect(); 
        }

    }



    //UPVOTE AND DOWNVOTE BUTTONS
    class UpvoteButton extends ToggleButton{
        private int postID;

        public UpvoteButton(String str, int postID)
        {
            super(str);
            this.postID = postID; 
            setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 14));
            if(isPostUpvoted(postID))
            {
                setSelected(true);
            }
            else if(isPostDownvoted(postID))
            {
                setDisable(true);
            }
        }

        public int getPostID()
        {
            return this.postID;
        }
    }

    class DownvoteButton extends ToggleButton{
        private int postID;

        public DownvoteButton(String str, int postID)
        {
            super(str);
            this.postID = postID; 
            setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 14));
            if(isPostDownvoted(postID))
            {
                setSelected(true);
            }
            else if(isPostUpvoted(postID))
            {
                setDisable(true);
            }
        }

        public int getPostID()
        {
            return this.postID;
        }
    }


    /* HELPER METHODS */
    public void createPost(VBox box, Post post, String username)
    {
        String description = post.getContent();
        String date = post.getDate();
        boolean isSalePost = post.getIsSalePost();

        int postID = post.getId();

        UpvoteButton upvoteButton = new UpvoteButton("Upvote", postID);
        Label upvotesLabel = new Label("" + post.getUpvotes());
        upvotesLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 14));

        DownvoteButton downvoteButton = new DownvoteButton("Downvote", postID);
        Label downvotesLabel = new Label("" + post.getDownvotes());
        downvotesLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 14));

        ToggleButton neitherIsSelected = new ToggleButton();

        ToggleGroup upvoteAndDownvote = new ToggleGroup();
        upvoteButton.setToggleGroup(upvoteAndDownvote);
        downvoteButton.setToggleGroup(upvoteAndDownvote);
        neitherIsSelected.setToggleGroup(upvoteAndDownvote);

        if(isPostUpvoted(postID))
        {
            upvoteButton.setSelected(true);
            downvoteButton.setDisable(true);
        }
        else if(isPostDownvoted(postID))
        {
            downvoteButton.setSelected(true);
            upvoteButton.setDisable(true);
        }
        else{
            neitherIsSelected.setSelected(true);
        }
        
                
        upvoteButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override 
            public void handle(ActionEvent event) 
            {
                int currentid = upvoteButton.getPostID();
                boolean isPostUpvoted = isPostUpvoted(currentid);

                DatabaseConnection.connect(); 

                Session session = DatabaseConnection.getSessionFactory().openSession();
                Transaction tx = null;

                try{
                    tx = session.beginTransaction();
                    Post currentpost = session.get(Post.class, currentid);

                    if(isPostUpvoted)
                    {
                        currentpost.decreaseUpvotes();
                        mainUser.removeUpvotedPosts("" + currentid);
                        upvoteButton.setSelected(false);
                        neitherIsSelected.setSelected(true);
                        downvoteButton.setDisable(false);
                    }
                    else if(!isPostDownvoted(currentid)){
                        currentpost.increaseUpvotes();
                        mainUser.addUpvotedPosts(""+ currentid);
                        upvoteButton.setSelected(true);
                        downvoteButton.setDisable(true);
                    }
                    tx.commit();

                    upvotesLabel.setText("" + currentpost.getUpvotes());
  
                } catch (Exception e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
            } 
        });

        downvoteButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override 
            public void handle(ActionEvent event) 
            {
                int currentid = downvoteButton.getPostID();
                boolean isPostDownvoted = isPostDownvoted(currentid);

                DatabaseConnection.connect(); 

                Session session = DatabaseConnection.getSessionFactory().openSession();
                Transaction tx = null;

                try{
                    tx = session.beginTransaction();
                    Post currentpost = session.get(Post.class, currentid);

                    if(isPostDownvoted)
                    {
                        currentpost.decreaseDownvotes();
                        mainUser.removeDownvotedPosts("" + currentid);
                        downvoteButton.setSelected(false);
                        neitherIsSelected.setSelected(true);
                        upvoteButton.setDisable(false);
                    }
                    else if(!isPostUpvoted(currentid)){
                        currentpost.increaseDownvotes();
                        mainUser.addDownvotedPosts(""+ currentid);
                        downvoteButton.setSelected(true);
                        upvoteButton.setDisable(true);
                    }
                    tx.commit();

                    downvotesLabel.setText("" + currentpost.getDownvotes());

  
                } catch (Exception e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
                
            } 
        });

        Label usernameLabel = new Label(username);
        Label dateLabel = new Label(date);
        BorderPane usernameAndDateBox = new BorderPane();
        usernameAndDateBox.setLeft(usernameLabel);
        usernameAndDateBox.setRight(dateLabel);

        usernameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        dateLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 16));

        TextArea postContent = new TextArea();
        postContent.setMinSize (450, 100);
        String content = description;
        if(isSalePost)
        {
            postContent.setMinSize(450, 250);
            String bookTitle = post.getBookTitle();
            String author = post.getAuthorName();
            String courseName = post.getCourseName();
            double price = post.getPrice();
            int usageAmount = post.getUsageAmount();
            String usageInfo = "";
            if(usageAmount == 1)
            {
                usageInfo += "Brand New";
            }
            else if(usageAmount == 2)
            {
                usageInfo += "Under-used";
            }
            else if(usageAmount == 3)
            {
                usageInfo += "Over-used";
            }

            String publisher = post.getPublisherName();
            String edition = post.getBookEdition();
            content += "\n\n• Title: " + bookTitle + "\n• Author: " + author + "\n• Publisher: " + publisher + "\n• Edition: " + edition 
            + "\n• Course Code: " + courseName + "\n• Price: " + price + " TL\n• Usage Amount: " + usageInfo;
            
        }
        postContent.setText(content);

        postContent.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        postContent.setEditable(false);

        HBox upvoteDownvoteBox = new HBox();
        upvoteDownvoteBox.setAlignment(Pos.BASELINE_LEFT);
        upvoteDownvoteBox.setSpacing(10);
        upvoteDownvoteBox.getChildren().addAll(upvoteButton, upvotesLabel, downvoteButton, downvotesLabel);
        
        box.setSpacing(10);
        box.getChildren().addAll(usernameAndDateBox, postContent, upvoteDownvoteBox);


    }

    private void enteringExitingButton (ToggleButton button, HBox box)
    {
        // when mouse enters the button
        button.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle (MouseEvent arg0) 
            {
                button.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        // when mouse enters the box
        box.setOnMouseEntered (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle (MouseEvent arg0) 
            {
                box.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
            }
            
        });

        // when mouse exists the button
        button.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle (MouseEvent arg0) 
            {
                if (!button.isSelected())
                {
                    button.setBackground (new Background (new BackgroundFill (null, null, null)));

                }
            }
            
        });

        // when mouse exists the box
        box.setOnMouseExited (new EventHandler <MouseEvent>() 
        {

            @Override
            public void handle (MouseEvent arg0) 
            {
                if (!button.isSelected())
                {
                    box.setBackground (new Background (new BackgroundFill (null, null, null)));

                }
            }
            
        });
    }

    public void colorBackground(ToggleButton button, HBox box)
    {
        box.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
        button.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
    }

    public void discolorBackground(ToggleButton button, HBox box)
    {
        box.setBackground (new Background (new BackgroundFill (null, null, null)));
        button.setBackground (new Background (new BackgroundFill (null, null, null)));
    }

    public boolean isPostUpvoted(int postID)
    {
        String upvotedPosts = mainUser.getUpvotedPosts();
        if(upvotedPosts == null)
        {
            return false;
        }

        String postIDString = "" + postID;
        String [] upvotedPostsArray = upvotedPosts.split(",");
        for(int i = 0; i < upvotedPostsArray.length; i++)
        {
            if(postIDString.equals(upvotedPostsArray[i]))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isPostDownvoted(int postID)
    {
        String downvotedPosts = mainUser.getDownvotedPosts();
        if(downvotedPosts == null)
        {
            return false;
        }
        String [] downvotedPostsArray = downvotedPosts.split(",");
        String postIDString = "" + postID;
        for(int i = 0; i < downvotedPostsArray.length; i++)
        {
            if(postIDString.equals(downvotedPostsArray[i]))
            {
                return true;
            }
        }
        return false;
    }
}

    

    

