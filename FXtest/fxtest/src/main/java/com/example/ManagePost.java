package com.example;

public class ManagePost 
{
    private String title;
    private String authorName;
    private String publisher;
    private String courseCode;
    private double price;
    private boolean isSalesPost;
    private int usageIndex;
    private int edition;
    private String date;
    
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public void setAuthorName(String authorName) 
    {
        this.authorName = authorName;
    }

    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }

    public void setCourseCode(String courseCode) 
    {
        this.courseCode = courseCode;
    }

    public void setPrice(double price) 
    {
        this.price = price;
    }

    public void setIsSalesPost(boolean isSalesPost) 
    {
        this.isSalesPost = isSalesPost;
    }

    public void setUsageIndex(int usageIndex) 
    {
        this.usageIndex = usageIndex;
    }

    public void setEdition(int edition) 
    {
        this.edition = edition;
    }

    public void setDate(String date) 
    {
        this.date = date;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getAuthorName() 
    {
        return authorName;
    }

    public String getPublisher() 
    {
        return publisher;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }

    public double getPrice() 
    {
        return price;
    }

    public boolean isSalesPost() 
    {
        return isSalesPost;
    }

    public int getUsageIndex() 
    {
        return usageIndex;
    }

    public int getEdition() 
    {
        return edition;
    }

    public String getDate() 
    {
        return date;
    }
}
