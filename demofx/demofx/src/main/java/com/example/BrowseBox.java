package com.example;

import org.hibernate.Session;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class BrowseBox extends VBox
{
    private static final double ICON_HEIGHT = 20;
    private static final double ICON_WIDTH = 20;


    // Instance Variables
    private VBox filtrationBox;
    private VBox bookBox;
    private VBox searchAndFilterBox;
    private HBox searchBox;
    private ScrollPane postsAndFilters;
    private User mainUser;
    private ScrollPane scrollPane;

    @SuppressWarnings("rawtypes")
    private ChoiceBox priceBox;

    private CheckBox newBox;
    private CheckBox underUsedBox;
    private CheckBox overUsedBox;

    @SuppressWarnings("rawtypes")
    private ChoiceBox postTypeBox;
   


    // Constructor
    public BrowseBox (User user)
    {
        this.mainUser = user;
        this.createSearchBox();
        this.createFiltrationBox();

        searchAndFilterBox = new VBox();
        searchAndFilterBox.setSpacing (30);
        searchAndFilterBox.getChildren().addAll (this.searchBox, this.postsAndFilters);
        this.getChildren().add (searchAndFilterBox);
    }

    @SuppressWarnings("unchecked")
    private void createFiltrationBox ()
    {
        filtrationBox = new VBox();
        filtrationBox.setSpacing (15);
        filtrationBox.setAlignment (Pos.CENTER_LEFT);
        filtrationBox.setPadding (new Insets (70));
        

        // course


        // post type
        Label postTypeLabel = new Label ("Post Type");
        postTypeLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));


        postTypeBox = new ChoiceBox <String>();
        postTypeBox.getItems().add ("Book");
        postTypeBox.getItems().add ("Q&A");

        // price
        Label priceLabel = new Label ("Price");
        priceLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
 
 
        priceBox = new ChoiceBox <>();
 
        // usage 
        Label usageLabel = new Label ("Usage");
        usageLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
 
         
        newBox = new CheckBox ("New");
        underUsedBox = new CheckBox ("Under-used");
        overUsedBox = new CheckBox ("Over-used");
         
        TilePane usagePane = new TilePane();
        usagePane.getChildren().addAll (newBox, underUsedBox, overUsedBox);

        filtrationBox.getChildren().addAll (postTypeLabel, postTypeBox);
       

        postTypeBox.setOnAction (new EventHandler <Event>() 
        {

            @Override
            public void handle (Event arg0) 
            {
                String postType = (String) postTypeBox.getValue();

                if (postType.equals ("Book"))
                {
                    bookBox = new VBox();
                    bookBox.setAlignment (Pos.CENTER_LEFT);
                    bookBox.setSpacing (15);
                    bookBox.getChildren().addAll (priceLabel, priceBox, usageLabel, usagePane);


                    filtrationBox.getChildren().addAll (bookBox);
                    

                }
                else
                {
                    filtrationBox.getChildren().remove (bookBox);
                }
            }
            
        });


        postsAndFilters = new ScrollPane();
        postsAndFilters.setContent (filtrationBox);
        postsAndFilters.setFitToHeight (true);
        postsAndFilters.setFitToWidth (true);
        postsAndFilters.setStyle ("-fx-border-color: #F0F0F0; -fx-border-width: 1px 1px 1px 1px;");

        VBox.setVgrow (postsAndFilters, Priority.ALWAYS);
        
    }
    
    private void createSearchBox ()
    {
        this.searchBox = new HBox();

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

        searchBox.setSpacing (550);
        searchBox.getChildren().add(searchPane);

        searchBar.setOnAction(event -> searchAndPrint(searchBar.getText()));
    }

    public void searchAndPrint(String input)
    {
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
                    String content = post.getContent();

                    content = content.toLowerCase();
                    input = input.toLowerCase();

                    String bookProperties = post.getAuthorName() + " " + 
                    post.getBookEdition() + " " + post.getBookTitle() + " "
                    + post.getPublisherName();

                    bookProperties = bookProperties.toLowerCase();

                    if(content.contains(input) || bookProperties.contains(input))
                    {
                        currentPost = new PostBox(post, mainUser, session);
        
                        currentPost.setPrefSize(500, 500);
                        currentPost.setAlignment(Pos.CENTER);
                        
                        postsAndFilters.setContent(currentPost);
                        
                        postsDisplayed++;
                    }
                }      
                i--;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            postsAndFilters.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            DatabaseConnection.disconnect(); 
        }
    }
}