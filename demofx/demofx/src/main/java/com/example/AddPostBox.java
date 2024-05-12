package com.example;




import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
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
    private HBox bookDetailsBox;
    private VBox infoFieldsBox;
    private VBox courseUsageAndPriceBox;
    private Button createPostButton;

    public AddPostBox(User user)
    {
        super();
        mainUser = user;
        this.createSearchBox();

        HBox addPostBox = new HBox();
        addPostBox.setSpacing (500);

        this.createDescriptionAndPostTypeBox();
        //TODO
        addPostBox.getChildren().addAll ();
        this.getChildren().add (descriptionAndPostTypeBox);
    }


    private void createDescriptionAndPostTypeBox ()
    {
        descriptionAndPostTypeBox = new VBox();
        descriptionAndPostTypeBox.setSpacing (15);
        descriptionAndPostTypeBox.setPadding (new Insets(50));

        // description
        Label descriptionLabel = new Label ("Description");
        descriptionLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefHeight (AREA_HEIGHT);
        descriptionArea.setPrefWidth (AREA_WIDTH);
        descriptionArea.setText (mainUser.getName());
        descriptionArea.setFont (Font.font ("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 20));


        // type choosing
        Label typeLabel = new Label ("Choose Type");
        typeLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        // radio buttons: book or q&a
        RadioButton bookRadioButton = new RadioButton ("Book");

        RadioButton questionRadioButton = new RadioButton ("Q&A");

        // creating button group 
        ToggleGroup bookQuestionGroup = new ToggleGroup();

        bookRadioButton.setToggleGroup (bookQuestionGroup);
        questionRadioButton.setToggleGroup (bookQuestionGroup);

        // Hbox for types
        HBox typeBox = new HBox();
        typeBox.getChildren().addAll (bookRadioButton, questionRadioButton);
        typeBox.setSpacing (20);

        this.descriptionAndPostTypeBox.getChildren().addAll (descriptionLabel, descriptionArea, typeLabel, typeBox);

    }

    @SuppressWarnings("unchecked")
    private void bookIsClicked (ToggleGroup buttonGroup, VBox box) 
    {
        

        bookDetailsBox = new HBox();

        // title
        Label titleLabel = new Label ("Title");
        titleLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField titleField = new TextField();
        titleField.setPrefHeight (FIELD_HEIGHT);
        titleField.setPrefWidth (FIELD_WIDTH);

        // author
        Label authorLabel = new Label ("Author");
        authorLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField authorField = new TextField();
        authorField.setPrefHeight (FIELD_HEIGHT);
        authorField.setPrefWidth (FIELD_WIDTH);

        // publisher 
        Label publisherLabel = new Label ("Publisher");
        publisherLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField publisherField = new TextField();
        publisherField.setPrefHeight (FIELD_HEIGHT);
        publisherField.setPrefWidth (FIELD_WIDTH);

        // edition
        Label editionLabel = new Label ("Edition");
        editionLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField editionField = new TextField();
        editionField.setPrefHeight (FIELD_HEIGHT);
        editionField.setPrefWidth (FIELD_WIDTH);

        infoFieldsBox = new VBox();
        infoFieldsBox.setSpacing (15);
        infoFieldsBox.setPadding (new Insets(50));

        infoFieldsBox.getChildren().addAll (titleLabel, titleField, authorLabel, authorField, publisherLabel, publisherField, editionLabel, editionField);


        // course, usage, budget
        courseUsageAndPriceBox = new VBox();

        // course
        Label courseLabel = new Label ("Course");
        courseLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        TextField courseField = new TextField();
        courseField.setPrefHeight (FIELD_HEIGHT);
        courseField.setPrefWidth (FIELD_WIDTH);

        // usage
        Label usageLabel = new Label ("Course");
        usageLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
                    
        @SuppressWarnings("rawtypes")
        ChoiceBox usageBox = new ChoiceBox <String>();
        usageBox.getItems().add ("New");
        usageBox.getItems().add ("Under-used");
        usageBox.getItems().add ("Over-used");

        // when a choice is made
        usageBox.setOnAction (new EventHandler<Event>() 
        {

            @Override
            public void handle(Event event) 
            {
                String usage = (String) usageBox.getValue();
            }
                        
        });

        // price
        Label priceLabel = new Label ("Price");
        priceLabel.setFont (Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));

        // radio buttons for price 
        RadioButton freeButton = new RadioButton ("Free");

        RadioButton priceButton = new RadioButton ("Enter Price:");
        TextField priceField = new TextField();
        priceField.setPrefHeight (10);
        priceField.setPrefWidth (40);

        HBox priceBox = new HBox();
        priceBox.getChildren().addAll (freeButton, priceButton, priceField);

        courseUsageAndPriceBox.getChildren().addAll (courseLabel, courseField, usageLabel, usageBox, priceBox);

        bookDetailsBox.getChildren().addAll (infoFieldsBox, courseUsageAndPriceBox);
        box.getChildren().add (bookDetailsBox);
        
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
