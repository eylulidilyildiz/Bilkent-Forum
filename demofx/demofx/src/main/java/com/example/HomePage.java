package com.example;

import org.hibernate.Session;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.*;


public class HomePage extends Application 
{
    // Main User for the application
    private User mainUser;
    private VBox allPostsBox;
    private ScrollPane postsPane;

    // home
    private ToggleButton homeButton;

    // The whole home section with the image
    private HBox homeBox;

    // profile
    private ToggleButton profileButton;
    private HBox profileBox;

    // browse
    private ToggleButton browseButton;
    private HBox browseBox;

    // upvoted
    private ToggleButton upvotedButton;
    private HBox upvotedBox;

    // bookmarks
    private ToggleButton bookmarksButton;
    private HBox bookmarksBox;

    public HomePage(User user)
    {
        mainUser = user;
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    

   @Override
    public void start (@SuppressWarnings("exports") Stage homeStage) throws Exception {

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
        this.homeButton = new ToggleButton ("Home");
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
        this.homeBox = new HBox ();
        homeBox.getChildren().addAll (homeIcon, homeButton);
        homeBox.setAlignment (Pos.CENTER); // Aligning the image and button
        colorBackground(homeButton, homeBox);

        // shadowing effect - mouse entering, exiting button
        enteringExitingButton (homeButton, homeBox);
        

        // profile
        this.profileButton = new ToggleButton ("Profile");
        profileButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        profileButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        profileButton.setPrefWidth (WIDTH_MENU_PANE);
        profileButton.setAlignment (Pos.TOP_LEFT);

        // ImageView of PROFILE
        ImageView profileIcon = new ImageView (getClass().getResource("images/profileIcon.png").toString());
        profileIcon.setFitHeight (ICON_HEIGHT);
        profileIcon.setFitWidth (ICON_WIDTH);

        this.profileBox = new HBox();
        profileBox.getChildren().addAll (profileIcon, profileButton);
        profileBox.setAlignment (Pos.CENTER);

        // shadowing effect - mouse entering, exiting button
        enteringExitingButton (profileButton, profileBox);
    

        // browse
        this.browseButton = new ToggleButton ("Browse");
        browseButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        browseButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        browseButton.setPrefWidth (WIDTH_MENU_PANE);
        browseButton.setAlignment (Pos.TOP_LEFT);

        // ImageView of BROWSE
        ImageView browseIcon = new ImageView (getClass().getResource("images/browseIcon.png").toString());
        browseIcon.setFitHeight (ICON_HEIGHT);
        browseIcon.setFitWidth (ICON_WIDTH);

        this.browseBox = new HBox();
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
        this.upvotedButton = new ToggleButton ("Upvoted Posts");
        upvotedButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        upvotedButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        upvotedButton.setPrefWidth (WIDTH_MENU_PANE);
        upvotedButton.setAlignment (Pos.TOP_LEFT);

        // ImageView of UPVOTE
        ImageView upvotedIcon = new ImageView (getClass().getResource("images/upvoteIcon.png").toString());
        upvotedIcon.setFitHeight (ICON_HEIGHT);
        upvotedIcon.setFitWidth (ICON_WIDTH);

        this.upvotedBox = new HBox();
        upvotedBox.getChildren().addAll (upvotedIcon, upvotedButton);
        upvotedBox.setAlignment (Pos.CENTER);

        // shadowing effect - mouse entering, exiting button
        enteringExitingButton (upvotedButton, upvotedBox);


        // bookmarks
        this.bookmarksButton = new ToggleButton ("Bookmarks");
        bookmarksButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        bookmarksButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        bookmarksButton.setPrefWidth (WIDTH_MENU_PANE);
        bookmarksButton.setAlignment (Pos.TOP_LEFT);

        // ImageView of BOOKMARKS
        ImageView bookmarksIcon = new ImageView (getClass().getResource("images/bookmarkIcon.png").toString());
        bookmarksIcon.setFitHeight (ICON_HEIGHT);
        bookmarksIcon.setFitWidth (ICON_WIDTH);

        this.bookmarksBox = new HBox();
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

        // LOGOUT
        Button logoutButton = new Button("LOGOUT");
        //logoutButton.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        logoutButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 22));

        // changing color ???
        logoutButton.setStyle ("-fx-background-color: pink");
        logoutButton.setEffect (new DropShadow());
        logoutButton.setPrefWidth(WIDTH_MENU_PANE);
        
        // when button is clicked it logs out, closes the home page
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) 
            { 
                DatabaseConnection.connect(); 
                try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
                {
                    homeStage.close();
                    Application login = new Login();
                    Stage loginStage = new Stage();
                    login.start(loginStage);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    DatabaseConnection.disconnect(); 
                }
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
        //friendsPane.setHgap(20);
        //friendsPane.setVgap(100);

        friendsPane.setBackground(new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        friendsPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px 1px 1px 1px;");
        
        VBox allFriendsBox = new VBox();
        ToggleGroup usersGroup = new ToggleGroup();
        ToggleButton friendButton = new ToggleButton();

        allFriendsBox.setSpacing(5);
        allFriendsBox.setAlignment(Pos.CENTER);
        allFriendsBox.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));

        FriendsBox friendBox;
        DatabaseConnection.connect(); 
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
        {
            int i = DatabaseConnection.getMaxUserID();
            int friendsDisplayed = 0;
            int totalfriendsCount = DatabaseConnection.countUsers() - 1;
            while(i > 0 && friendsDisplayed < totalfriendsCount)
            {
                if(session.get( User.class, i) != null && ( i != mainUser.getId() ) )
                {
                    User friend = session.get(User.class, i);
                    friendBox = new FriendsBox(mainUser, friend);

                    HBox friendLine = friendBox.getFriendBox();

                    friendButton = friendBox.getFriendButton();
                    allFriendsBox.getChildren().add(friendLine);
                    friendsDisplayed++;
                    usersGroup.getToggles().add(friendButton);
                    friendButton.setToggleGroup(usersGroup);
                    enteringExitingButton(friendButton, friendLine);

                    friendButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override 
                        public void handle(ActionEvent event) 
                        { 
                            DatabaseConnection.connect(); 
                            try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
                            {
                                // Friends page
                                ProfileBox friendPageBox = new ProfileBox (friend, root, true);
                                root.setCenter (friendPageBox);

                                profileButton.setSelected(true);

                                colorBackground(profileButton, profileBox);
                                discolorBackground(homeButton, homeBox);
                                discolorBackground(browseButton, browseBox);
                                discolorBackground(upvotedButton, upvotedBox);
                                discolorBackground(bookmarksButton, bookmarksBox);
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                DatabaseConnection.disconnect(); 
                            }
                        } 
                    });
                }      
                i--;
            }

        }

        HBox searchAndUsersLabel = new HBox();


        Label usersLabel = new Label(" Users ");
        usersLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 27));
        usersLabel.setTextFill (Color.rgb (101, 14, 63));
        usersLabel.setPrefWidth(248);
        usersLabel.setAlignment(Pos.TOP_LEFT);
        
        
        // search bar for the users
        HBox searchBox = new HBox();
        searchBox.setSpacing (10);
        searchBox.setAlignment (Pos.CENTER);
        searchBox.setPadding (new Insets (10));

        TextField searchField = new TextField();

        
        
        ImageView searchFieldIcon = new ImageView (getClass().getResource("images/browseIcon.png").toString());
        searchFieldIcon.setFitHeight (ICON_HEIGHT);
        searchFieldIcon.setFitWidth (ICON_WIDTH);
        
        searchBox.getChildren().addAll (searchFieldIcon, searchField);
        
        searchAndUsersLabel.getChildren().addAll (usersLabel, searchBox);
        
        friendsPane.add (searchAndUsersLabel, 0, 1);
        
        ScrollPane allUsersPane = new ScrollPane();
        
        allFriendsBox.setAlignment(Pos.CENTER_LEFT);
        allUsersPane.setContent(allFriendsBox);
        allUsersPane.setFitToWidth (true);
        allUsersPane.setFitToHeight (true);
        allUsersPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        // when a user is searched
        searchField.setOnAction (new EventHandler <ActionEvent>() 
        {
            
            @Override
            public void handle (ActionEvent event) 
            {
                searchAndShowUser (searchField.getText(), allUsersPane, root);
                
            }
            
        });

        friendsPane.add (allUsersPane, 0, 2);
        friendsPane.setVgap(20);
        friendsPane.setHgap(20);

        root.setRight(friendsPane);


        // ADD POST BUTTON
        GridPane postButtonPane = new GridPane();
        postButtonPane.setAlignment (Pos.TOP_RIGHT);
        postButtonPane.setHgap (10);
        postButtonPane.setVgap (10);
        postButtonPane.setPadding(new Insets(70));

        Button addPostButton = new Button ();
        addPostButton.setBackground (new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
        addPostButton.setPrefHeight (60);
        addPostButton.setPrefWidth (60);

        ImageView plusIcon = new ImageView (getClass().getResource("images/plusIcon.png").toString());
        plusIcon.setFitHeight (60);
        plusIcon.setFitWidth (60);

        addPostButton.setGraphic (plusIcon);
        
        AddPostBox addPostBox = new AddPostBox(mainUser);

        addPostButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                root.setCenter(addPostBox);
            }
        });


        postButtonPane.add (addPostButton, 0, 1);

        // combining the searchPane and the addPostButton 
        HBox searchAddPostBox = new HBox();
        searchAddPostBox.setSpacing (550);
        searchAddPostBox.getChildren().addAll (postButtonPane);
        
       
        //POSTS

        allPostsBox = new VBox();
        allPostsBox.setSpacing(30);
        allPostsBox.setAlignment(Pos.CENTER);

        PostBox currentPost;

        DatabaseConnection.connect(); 
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
        {
            int i = DatabaseConnection.getMaxPostID();
            int postsDisplayed = 0;
            int totalPostCount = DatabaseConnection.countPosts();
            while(i > 0 && postsDisplayed < totalPostCount)
            {
                if(session.get(Post.class, i) != null)
                {
                    Post post = session.get(Post.class, i);
                    currentPost = new PostBox(post, mainUser, session);
    
                    currentPost.setPrefSize(500, 500);
                    currentPost.setAlignment(Pos.CENTER);
    
                    allPostsBox.getChildren().add(currentPost);
                    postsDisplayed++;
                }      
                i--;
            }

            
            postsPane = new ScrollPane();
            postsPane.setPadding(new Insets(70));
            allPostsBox.setAlignment(Pos.CENTER);
            postsPane.setContent(allPostsBox);
            postsPane.setFitToWidth (true);
            postsPane.setFitToHeight (true);
            postsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            postsPane.setStyle ("-fx-border-color: #F0F0F0; -fx-border-width: 1px 1px 1px 1px;");

            // combining the searchPane and postsPane
            VBox homePageBox = new VBox();
            homePageBox.getChildren().addAll (searchAddPostBox, postsPane);

            // Profile page
            ProfileBox profilePageBox = new ProfileBox (this.mainUser, root, false);

            

            // Browse Page
            BrowseBox browsePageBox = new BrowseBox (mainUser);



            /* WHEN BUTTONS ARE CLICKED */ 
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

                    postsPane.setContent(allPostsBox);
                    root.setCenter (homePageBox);

                    allUsersPane.setContent (allFriendsBox);
                    root.setRight (friendsPane);
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

                    root.setCenter (profilePageBox);
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

                    root.setCenter (browsePageBox);
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

                    //changing displayed posts
                    VBox upvotedPostsBox = new VBox();
                    //postsPane = new ScrollPane();
                    PostBox currentPost;

                    DatabaseConnection.connect(); 
                    try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
                    {
                        String [] upvotedPostsArray = mainUser.getUpvotedPosts().split(",");
                        for( int i = upvotedPostsArray.length - 1; i >= 0; i--)
                        {
                            int id = Integer.parseInt( upvotedPostsArray[i] );
                            if(session.get(Post.class, id) != null)
                            {
                                Post post = session.get(Post.class, id);

                                currentPost = new PostBox(post, mainUser, session);
                
                                currentPost.setPrefSize(500, 500);
                                currentPost.setAlignment(Pos.CENTER);
                
                                upvotedPostsBox.getChildren().add(currentPost);

                            }      
                        }
                        postsPane.setContent(upvotedPostsBox);
                        root.setCenter (homePageBox);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        DatabaseConnection.disconnect(); 
                    }
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

                    VBox bookmarkedPostsBox = new VBox();
                    //postsPane = new ScrollPane();
                    PostBox currentPost;

                    DatabaseConnection.connect(); 
                    try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
                    {
                        String [] bookmarkedPostsArray = mainUser.getBookmarkedPosts().split(",");
                        for( int i = bookmarkedPostsArray.length - 1; i >= 0; i--)
                        {
                            int id = Integer.parseInt( bookmarkedPostsArray[i] );
                            if(session.get(Post.class, id) != null)
                            {
                                Post post = session.get(Post.class, id);

                                currentPost = new PostBox(post, mainUser, session);
                
                                currentPost.setPrefSize(500, 500);
                                currentPost.setAlignment(Pos.CENTER);
                
                                bookmarkedPostsBox.getChildren().add(currentPost);
                            }      
                        }
                        postsPane.setContent(bookmarkedPostsBox);
                        root.setCenter (homePageBox);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        DatabaseConnection.disconnect(); 
                    }


                }
            });

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
            DatabaseConnection.disconnect(); 
        }

    }
    
    /* HELPER METHODS */

    @SuppressWarnings("exports")
    public void enteringExitingButton (ToggleButton button, HBox box)
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

    @SuppressWarnings("exports")
    public void colorBackground(ToggleButton button, HBox box)
    {
        box.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
        button.setBackground (new Background (new BackgroundFill (Color.rgb (220, 220, 220), null, null)));
    }

    @SuppressWarnings("exports")
    public void discolorBackground(ToggleButton button, HBox box)
    {
        box.setBackground (new Background (new BackgroundFill (null, null, null)));
        button.setBackground (new Background (new BackgroundFill (null, null, null)));
    }


    @SuppressWarnings("exports")
    public void searchAndShowUser (String inputUser, ScrollPane usersPane, BorderPane root)
    {

        VBox searchedUsers = new VBox();
        searchedUsers.setSpacing(5);
        searchedUsers.setAlignment(Pos.CENTER);
        searchedUsers.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        searchedUsers.setAlignment(Pos.CENTER_LEFT);

        DatabaseConnection.connect(); 
        try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
        {
            int i = DatabaseConnection.getMaxUserID();
            int usersSearched = 0;
            int usersDisplayed = 0;
            int totalUserCount = DatabaseConnection.countUsers() - 1;

            while (i > 0 && usersSearched < totalUserCount)
            {
                if (session.get (User.class, i) != null && (i != mainUser.getId()))
                {
                    User user = session.get (User.class, i);
                    String nameSurname = user.getName() + " " + user.getSurname();

                    nameSurname = nameSurname.toLowerCase();
                    inputUser = inputUser.toLowerCase();

                    boolean shouldUserBeDisplayed = false;

                    if (nameSurname.contains (inputUser))
                    {
                        shouldUserBeDisplayed = true;
                    }
                   
                    if (shouldUserBeDisplayed)
                    {
                        FriendsBox searched = new FriendsBox (mainUser, user);

                        HBox searchedLine = new HBox();
                        searchedLine = searched.getFriendBox();

                        searchedUsers.getChildren().add (searchedLine);
                        usersDisplayed++;

                        enteringExitingButton (searched.getFriendButton(), searchedLine);

                        searched.getFriendButton().setOnAction (new EventHandler<ActionEvent>() 
                        {

                            @Override
                            public void handle (ActionEvent event) 
                            {
                                // Friends page
                                ProfileBox friendPageBox = new ProfileBox (user, root, true);
                                root.setCenter (friendPageBox);

                                profileButton.setSelected (true);

                                colorBackground (profileButton, profileBox);
                                discolorBackground (homeButton, homeBox);
                                discolorBackground (browseButton, browseBox);
                                discolorBackground (upvotedButton, upvotedBox);
                                discolorBackground (bookmarksButton, bookmarksBox);
                                discolorBackground (searched.getFriendButton(), searched.getFriendBox());
                                searched.getFriendButton().setSelected (false);

                            }
                            
                        });;
                    }
                }
                i--;

                
            }

            if (usersDisplayed == 0)
            {
                Label noSuchUserLabel = new Label ("There aren't any matching users.");
                noSuchUserLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 15));
                searchedUsers.getChildren().add (noSuchUserLabel);
            }

            Label goingBackLabel = new Label ("Click home to go back.");
            goingBackLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 15));

            searchedUsers.getChildren().add (goingBackLabel);
            usersPane.setContent (searchedUsers);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            usersPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            //postsAndFilters.setContent(postsBox);
            DatabaseConnection.disconnect(); 
        }
    }
}

    

    

