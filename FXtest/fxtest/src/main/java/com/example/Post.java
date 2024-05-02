package com.example;

public class Post 
{
    protected String description;
    protected String date;

    protected long postID;
    protected long ownerID;

    protected int numberOfUpvotes;
    protected int numberOfDownvotes;
     
    protected boolean isSalesPost; 

    public Post(String description, boolean isSalesPost)
    {
        this.description = description;
        this.isSalesPost = isSalesPost;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getDate()
    {
        return this.date;
    }

    public long getPostID()
    {
        return this.postID;
    }

    public long getOwnerID()
    {
        return this.ownerID;
    }

    public boolean isSalesPost()
    {
        return isSalesPost;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setIsSalesPost(boolean isSalesPost)
    {
        this.isSalesPost = isSalesPost;
    }
}
