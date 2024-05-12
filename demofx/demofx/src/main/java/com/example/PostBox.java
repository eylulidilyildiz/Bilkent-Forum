package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.geometry.*;

public class PostBox extends VBox 
{
    private Post currentPost;
    private User mainUser;
    private Session session;

    public PostBox(Post post, User user, Session session)
    {
        super();
        this.currentPost = post;
        this.mainUser = user;
        this.session = session;

        setSpacing(30);
        setAlignment(Pos.CENTER);

        int ownerId = post.getOwnerID();

        String username = session.get(User.class, ownerId).getUsername();
        createPost(post, username);
    }

    //BUTTONS
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

    class BookmarkButton extends Button{
        private int postID;
        final int ICON_HEIGHT = 30;
        final int ICON_WIDTH = 35;
        ImageView filledBookmarkIcon;
        ImageView emptyBookmarkIcon;

        public BookmarkButton(int postID)
        {
            super();
            this.postID = postID;
            setBackground (new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
            setPrefHeight (ICON_HEIGHT);
            setPrefWidth (ICON_WIDTH);

            filledBookmarkIcon = new ImageView (getClass().getResource("images/filledBookmark.png").toString());
            filledBookmarkIcon.setFitHeight (ICON_HEIGHT);
            filledBookmarkIcon.setFitWidth (ICON_WIDTH);
            emptyBookmarkIcon = new ImageView (getClass().getResource("images/emptyBookmark.png").toString());
            emptyBookmarkIcon.setFitHeight (ICON_HEIGHT);
            emptyBookmarkIcon.setFitWidth (ICON_WIDTH);
            if(isPostBookmarked(postID))
            {
                setGraphic(filledBookmarkIcon);
            }
            else{
                setGraphic(emptyBookmarkIcon);
            }
        }

        public int getPostID()
        {
            return this.postID;
        }
        public ImageView getFilledBookmarkIcon()
        {
            return filledBookmarkIcon;
        }
        public ImageView getEmptyBookmarkIcon()
        {
            return emptyBookmarkIcon;
        }
    }

    /* HELPER METHODS */
    public void createPost(Post post, String username)
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

                try(Session session = DatabaseConnection.getSessionFactory().openSession()) {
                    Transaction tx = session.beginTransaction();
                    Post currentpost = session.get(Post.class, currentid);
                    
                    if(isPostUpvoted)
                    {
                        currentpost.decreaseUpvotes();
                        mainUser.removeUpvotedPosts("" + currentid);
                        upvoteButton.setSelected(false);
                        neitherIsSelected.setSelected(true);
                        downvoteButton.setDisable(false);
                    }
                    else if(!isPostDownvoted(currentid))
                    {
                        currentpost.increaseUpvotes();
                        mainUser.addUpvotedPosts("" + currentid);
                        upvoteButton.setSelected(true);
                        downvoteButton.setDisable(true);
                    }
                    session.merge("User", mainUser);
                    tx.commit();

                    upvotesLabel.setText("" + currentpost.getUpvotes());
  
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    DatabaseConnection.disconnect();
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

                try(Session session = DatabaseConnection.getSessionFactory().openSession()) {
                    Transaction tx = session.beginTransaction();
                    Post currentpost = session.get(Post.class, currentid);

                    if(isPostDownvoted)
                    {
                        currentpost.decreaseDownvotes();
                        mainUser.removeDownvotedPosts("" + currentid);
                        downvoteButton.setSelected(false);
                        neitherIsSelected.setSelected(true);
                        upvoteButton.setDisable(false);
                    }
                    else if(!isPostUpvoted(currentid))
                    {
                        currentpost.increaseDownvotes();
                        mainUser.addDownvotedPosts(""+ currentid);
                        downvoteButton.setSelected(true);
                        upvoteButton.setDisable(true);
                    }
                    session.merge("User", mainUser);
                    tx.commit();

                    downvotesLabel.setText("" + currentpost.getDownvotes());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    DatabaseConnection.disconnect();
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

        BookmarkButton bookmarkButton = new BookmarkButton(postID);
        bookmarkButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override 
            public void handle(ActionEvent event) 
            {
                int currentid = bookmarkButton.getPostID();
                boolean isPostBookmarked = isPostBookmarked(currentid);

                DatabaseConnection.connect(); 

                try(Session session = DatabaseConnection.getSessionFactory().openSession()) {
                    Transaction tx = session.beginTransaction();
                    
                    if(!isPostBookmarked)
                    {
                        mainUser.addBookmarkedPost( "" + currentid );
                        bookmarkButton.setGraphic(bookmarkButton.getFilledBookmarkIcon());
                    }
                    else{
                        mainUser.removeBookmarkedPosts( "" + currentid );
                        bookmarkButton.setGraphic(bookmarkButton.getEmptyBookmarkIcon());
                    }
                    session.merge("User", mainUser);
                    tx.commit();
  
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    DatabaseConnection.disconnect();
                }
            } 
        });

        HBox upvoteDownvoteBox = new HBox();
        upvoteDownvoteBox.setAlignment(Pos.BASELINE_LEFT);
        upvoteDownvoteBox.setSpacing(10);
        upvoteDownvoteBox.getChildren().addAll(upvoteButton, upvotesLabel, downvoteButton, downvotesLabel, bookmarkButton);

        
        this.setSpacing(10);
        this.getChildren().addAll(usernameAndDateBox, postContent, upvoteDownvoteBox);
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

    public boolean isPostBookmarked(int postID)
    {
        String bookmarkedPosts = mainUser.getBookmarkedPosts();
        if(bookmarkedPosts == null)
        {
            return false;
        }
        String [] bookmarkedPostsArray = bookmarkedPosts.split(",");
        String postIDString = "" + postID;
        for(int i = 0; i < bookmarkedPostsArray.length; i++)
        {
            if(postIDString.equals(bookmarkedPostsArray[i]))
            {
                return true;
            }
        }
        return false;
    }
}