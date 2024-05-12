package com.example;

import org.hibernate.Session;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

public class PostBox 
{
    public PostBox(User mainUser)
    {
        VBox postsBox = new VBox();
        postsBox.setSpacing(30);
        postsBox.setAlignment(Pos.CENTER);
        VBox currentPost;

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
                    int ownerID = post.getOwnerID();
                    String username = session.get(User.class, ownerID).getUsername();
    
                    currentPost = new VBox();
    
                    currentPost.setPrefSize(500, 500);
                    createPost(currentPost, post, username);
                    currentPost.setAlignment(Pos.CENTER);
    
                    postsBox.getChildren().add(currentPost);
                    postsDisplayed++;
                }      
                i--;
            }
            
            ScrollPane postsPane = new ScrollPane();
            postsPane.setPadding(new Insets(70));
            postsBox.setAlignment(Pos.CENTER);
            postsPane.setContent(postsBox);
            postsPane.setFitToWidth (true);
            postsPane.setFitToHeight (true);
            postsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            // combining the searchPane and postsPane
            VBox homePageBox = new VBox();
            homePageBox.getChildren().addAll (postsPane);

            // Profile page
            ProfileBox profilePageBox = new ProfileBox (this.mainUser, root);

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

//BOOKMARK BUTTON

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