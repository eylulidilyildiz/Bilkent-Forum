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
    }
}
