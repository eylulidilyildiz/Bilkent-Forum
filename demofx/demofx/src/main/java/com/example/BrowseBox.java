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
    private VBox postsBox;
    private HBox searchBox;
    private ScrollPane postsAndFilters;
    private User mainUser;

    private int range;

    private boolean isNew;
    private boolean isUnderused;
    private boolean isOverused;

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
        this.postsBox = new VBox();

        this.isNew = false;
        this.isUnderused = false;
        this.isOverused = false;

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
        priceBox.getItems().addAll("0-50", "50-100", "100+");

        // usage 
        Label usageLabel = new Label ("Usage");
        usageLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
         
        newBox = new CheckBox ("New");
        underUsedBox = new CheckBox ("Under-used");
        overUsedBox = new CheckBox ("Over-used");

        TilePane usagePane = new TilePane();
        usagePane.getChildren().addAll (newBox, underUsedBox, overUsedBox);
    
        filtrationBox.getChildren().addAll (postTypeLabel, postTypeBox);

        priceBox.setOnAction(new EventHandler <Event>()
        {
            @Override
            public void handle (Event arg0) 
            {
                String priceInput = (String) priceBox.getValue();

                if (priceInput.equals("0-50")){
                    range = 1;
                }
                else if (priceInput.equals("50-100")){
                    range = 2;
                }
                else if (priceInput.equals("100+")){
                    range = 3;                
                }
                else{
                    range = 0;
                }
            }
        });

        newBox.setOnAction(event -> {
            if (newBox.isSelected()) {
                isNew = true;
            } else {
                isNew = false;
            }
        });

        underUsedBox.setOnAction(event -> {
            if (underUsedBox.isSelected()) {
                isUnderused = true;
            } else {
                isUnderused = false;
            }
        });

        overUsedBox.setOnAction(event -> {
            if (overUsedBox.isSelected()) {
                isOverused = true;
            } else {
                isOverused = false;
            }
        });

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
        searchBar.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));
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

        // Clear previous search results    
        postsBox.getChildren().clear();
        
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
                    + post.getPublisherName() +  " " + post.getCourseName();

                    bookProperties = bookProperties.toLowerCase();

                    if((content.contains(input) || bookProperties.contains(input)) &&
                    ((isNew && post.getUsageAmount() == 1) ||
                    (isUnderused && post.getUsageAmount() == 2) ||
                    (isOverused && post.getUsageAmount() == 3)))
                    {
                        if ((range == 1 && post.getPrice() < 50) || 
                        (range == 2 && (post.getPrice() < 100 && post.getPrice() >= 50)) || 
                        (range == 3 && post.getPrice() >= 100)) {
                        
                            currentPost = new PostBox(post, mainUser, session);
                            currentPost.setPrefSize(500, 500);
                            currentPost.setAlignment(Pos.CENTER);
                            
                            postsBox.getChildren().add(currentPost);
                            postsDisplayed++;
                        }
                    }
                }      
                i--;
            }
            //postsAndFilters.setContent(postsBox);
            searchAndFilterBox.getChildren().add(postsBox);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            postsAndFilters.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            postsAndFilters.setContent(postsBox);
            DatabaseConnection.disconnect(); 
        }
    }
}