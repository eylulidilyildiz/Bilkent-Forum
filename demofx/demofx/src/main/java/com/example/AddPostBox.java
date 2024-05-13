package com.example;




import org.hibernate.Session;
import org.hibernate.Transaction;

import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class AddPostBox extends VBox 
{
    // Constants
    private static final double FIELD_HEIGHT = 40;
    private static final double FIELD_WIDTH = 300;
    private static final double AREA_HEIGHT = 200;
    private static final double AREA_WIDTH = 700;

    // Instance Variables
    private User mainUser;
    private HBox searchBox;
    private VBox postInfoBox;
    private VBox descriptionAndPostTypeBox;
    private HBox typeBox;
    private HBox bookDetailsBox;
    private VBox infoFieldsBox;
    private VBox courseUsageAndPriceBox;
    private Button createPostButton;

    private TextArea descriptionArea;
    private TextField titleField;
    private TextField authorField;
    private TextField publisherField;
    private TextField editionField;
    private TextField courseField;
    private ChoiceBox <String> usageBox;
    private TextField priceField;

    private boolean isBookSelected;
    private String usage;
    private boolean isBookFree;

    public AddPostBox(User user)
    {
        super();
        mainUser = user;
        this.createSearchBox();
        this.setSpacing(20);
        
        createPostButton = new Button("Create Post");
        createPostButton.setAlignment(Pos.BASELINE_RIGHT);
        createPostButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        createPostButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0) 
            {                       
                DatabaseConnection.connect(); 
                try (Session session = DatabaseConnection.getSessionFactory().openSession()) 
                {
                    Transaction tx = session.beginTransaction();

                    int postID = DatabaseConnection.getMaxPostID() + 1;
                    int ownerID = mainUser.getId();
                    String content = descriptionArea.getText();
                    //TODO
                    // HOW DO I GET THE DATE?
                    String date = "";
                    int initialUpvotes = 0;
                    int initialDownvotes = 0;
                    String commentIDs = null; //no comments initially

                    boolean isSalesPost = false;
                    String bookTitle = null;
                    String authorName = null;
                    String courseName = null;
                    Double price = null;
                    Integer usageAmount = null;
                    String publisherName = null;
                    String bookEdition = null;

                    if(isBookSelected)
                    {
                        isSalesPost = true;
                        bookTitle = titleField.getText();
                        authorName = authorField.getText();
                        courseName = courseField.getText();
                        publisherName = publisherField.getText();
                        bookEdition = editionField.getText();
                        if(usage.equals("New"))
                        {
                            usageAmount = 1;
                        }
                        else if(usage.equals("Under-used"))
                        {
                            usageAmount = 2;
                        }
                        else{
                            usageAmount = 3;
                        }

                        if(isBookFree)
                        {
                            price = 0.0;
                        }
                        else{
                            price = Double.parseDouble(priceField.getText());
                        }

                    }
                    
                    PostManager postManager = new PostManager();
                    postManager.createPost( postID, content, ownerID, initialUpvotes, initialDownvotes, commentIDs, isSalesPost,
                                            bookTitle, authorName, courseName, price, usageAmount, publisherName, bookEdition );
                } 
                catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    DatabaseConnection.disconnect(); 
                }
            }

        });

        HBox addPostBox = new HBox();
        addPostBox.setSpacing (500);

        this.createDescriptionAndPostTypeBox();
        
        //TODO
        addPostBox.getChildren().addAll ();
        
    }


    private void createDescriptionAndPostTypeBox ()
    {
        descriptionAndPostTypeBox = new VBox();
        descriptionAndPostTypeBox.setSpacing (15);
        descriptionAndPostTypeBox.setPadding (new Insets(50));

        // description
        Label descriptionLabel = new Label ("Description");
        descriptionLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        this.descriptionArea = new TextArea();
        descriptionArea.setPrefHeight (AREA_HEIGHT);
        descriptionArea.setPrefWidth (AREA_WIDTH);
        descriptionArea.setText (mainUser.getName());
        descriptionArea.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));


        // type choosing
        Label typeLabel = new Label ("Choose Type");
        typeLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        // radio buttons: book or q&a
        RadioButton bookRadioButton = new RadioButton ("Book");
        bookRadioButton.setFont(Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        RadioButton questionRadioButton = new RadioButton ("Q&A");
        questionRadioButton.setFont(Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        // creating button group 
        ToggleGroup bookQuestionGroup = new ToggleGroup();

        bookRadioButton.setToggleGroup (bookQuestionGroup);
        questionRadioButton.setToggleGroup (bookQuestionGroup);
        questionRadioButton.setSelected(true);
        this.isBookSelected = false;

        bookRadioButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if(bookRadioButton.isSelected())
                {
                    bookIsClicked();
                    isBookSelected = true;
                }
            }
            
        });

        questionRadioButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if(questionRadioButton.isSelected())
                {
                    if(isBookSelected)
                    {
                        getChildren().remove(bookDetailsBox);
                        isBookSelected = false;
                    }
                    int lastIndex = getChildren().size() - 1;
                    typeBox.getChildren().add(createPostButton);

                }
            }
            
        });



        // Hbox for types
        typeBox = new HBox();
        typeBox.getChildren().addAll (bookRadioButton, questionRadioButton, createPostButton);
        typeBox.setSpacing (20);

        this.descriptionAndPostTypeBox.getChildren().addAll (descriptionLabel, descriptionArea, typeLabel, typeBox);
        this.getChildren().add(descriptionAndPostTypeBox);
    }

    @SuppressWarnings("unchecked")
    private void bookIsClicked () 
    {
        bookDetailsBox = new HBox();

        // title
        Label titleLabel = new Label ("Title");
        titleLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        this.titleField = new TextField();
        titleField.setPrefHeight (FIELD_HEIGHT);
        titleField.setPrefWidth (FIELD_WIDTH);

        // author
        Label authorLabel = new Label ("Author");
        authorLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        this.authorField = new TextField();
        authorField.setPrefHeight (FIELD_HEIGHT);
        authorField.setPrefWidth (FIELD_WIDTH);

        // publisher 
        Label publisherLabel = new Label ("Publisher");
        publisherLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        this.publisherField = new TextField();
        publisherField.setPrefHeight (FIELD_HEIGHT);
        publisherField.setPrefWidth (FIELD_WIDTH);

        // edition
        Label editionLabel = new Label ("Edition");
        editionLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        this.editionField = new TextField();
        editionField.setPrefHeight (FIELD_HEIGHT);
        editionField.setPrefWidth (FIELD_WIDTH);

        infoFieldsBox = new VBox();
        infoFieldsBox.setSpacing (15);
        infoFieldsBox.setPadding (new Insets(50));

        infoFieldsBox.getChildren().addAll (titleLabel, titleField, authorLabel, authorField, publisherLabel, publisherField, editionLabel, editionField);


        // course, usage, budget
        courseUsageAndPriceBox = new VBox();
        courseUsageAndPriceBox.setPadding (new Insets(50));

        // course
        Label courseLabel = new Label ("Course");
        courseLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        this.courseField = new TextField();
        courseField.setPrefHeight (FIELD_HEIGHT);
        courseField.setPrefWidth (FIELD_WIDTH);

        // usage
        Label usageLabel = new Label ("Usage");
        usageLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
                    

        this.usageBox = new ChoiceBox <String>();
        usageBox.getItems().add ("New");
        usageBox.getItems().add ("Under-used");
        usageBox.getItems().add ("Over-used");

        usageBox.setPrefHeight (FIELD_HEIGHT);
        usageBox.setPrefWidth (FIELD_WIDTH);

        // when a choice is made
        usageBox.setOnAction (new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                usage = (String) usageBox.getValue();
            }
                        
        });

        // price
        Label priceLabel = new Label ("Price");
        priceLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        // radio buttons for price 
        RadioButton freeButton = new RadioButton ("Free");
        freeButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        RadioButton priceButton = new RadioButton ("Enter Price:");
        priceButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        this.priceField = new TextField();
        priceField.setPrefHeight (10);
        priceField.setPrefWidth (40);

        HBox priceBox = new HBox();
        priceBox.getChildren().addAll (freeButton, priceButton, priceField);
        priceBox.setSpacing (20);

        courseUsageAndPriceBox.getChildren().addAll (courseLabel, courseField, usageLabel, usageBox, priceLabel, priceBox, createPostButton);
        courseUsageAndPriceBox.setSpacing (20);

        bookDetailsBox.getChildren().addAll (infoFieldsBox, courseUsageAndPriceBox);
        bookDetailsBox.setSpacing (400);
        bookDetailsBox.setAlignment (Pos.CENTER_LEFT);

        this.getChildren().remove(createPostButton);
        this.getChildren().add (bookDetailsBox);
        
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

        Label searchLabel = new Label ("Search:");
        searchLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        searchPane.add (searchLabel, 1, 1);

        searchBox.setSpacing (550);
        searchBox.getChildren().add(searchPane);
        this.getChildren().add (searchBox);
        
    }


  

}
