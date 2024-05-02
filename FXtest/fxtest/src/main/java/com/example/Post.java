package com.example;

public class Post 
{
    protected String description;
    protected String date;
    protected long postID;
    protected int numberOfUpvotes;
    protected int numberOfDownvotes; 

    public void setDescription(String str)
    {
        description = str;
    }
}
