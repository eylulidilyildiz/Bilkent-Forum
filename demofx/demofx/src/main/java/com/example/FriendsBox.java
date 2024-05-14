package com.example;

import javafx.scene.image.ImageView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.geometry.*;

public class FriendsBox extends HBox 
{
    private String friendName;
    private String friendSurname;
    private HBox friendBox;
    private ToggleButton friendButton;

    public FriendsBox(User mainUser, User friend)
    {
        super();
        this.friendName = friend.getName();
        this.friendSurname = friend.getSurname();

        String nameAndSurname = friendName + " " + friendSurname;

        friendButton = new ToggleButton (nameAndSurname);
        friendButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, FontPosture.REGULAR, 22));
        friendButton.setTextFill (Color.BLACK);
        friendButton.setBackground (new Background(new BackgroundFill(Color.rgb (236, 231, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        friendButton.setPrefWidth (250);
        friendButton.setAlignment (Pos.TOP_LEFT);
        friendButton.setTextFill (Color.BLACK);

        ImageView friendIcon = new ImageView (getClass().getResource("images/profileIcon.png").toString());
        friendIcon.setFitHeight (40);
        friendIcon.setFitWidth (40);

        friendBox = new HBox();
        friendBox.getChildren().addAll (friendIcon, friendButton);
        friendBox.setAlignment (Pos.CENTER_LEFT);
    }

    @SuppressWarnings("exports")
    public HBox getFriendBox()
    {
        return friendBox;
    }

    @SuppressWarnings("exports")
    public ToggleButton getFriendButton()
    {
        return friendButton;
    }
}