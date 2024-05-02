package com.example;

public class SalesPost extends Post{
    private String authorName;
    private String bookName;
    private String courseCode;
    private String publisherName;
    private boolean isSellingABook;
    private int bookEdition;
    private double price;
    private int usageAmount;

    public SalesPost()
    {
        
    }

    public void setAuthorName(String authorName) 
    {
        this.authorName = authorName;
    }

    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public void setCourseCode(String courseCode) 
    {
        this.courseCode = courseCode;
    }

    public void setPublisherName(String publisherName) 
    {
        this.publisherName = publisherName;
    }

    public void setSellingABook(boolean sellingABook) 
    {
        isSellingABook = sellingABook;
    }

    public void setBookEdition(int bookEdition) 
    {
        this.bookEdition = bookEdition;
    }

    public void setPrice(double price) 
    {
        this.price = price;
    }

    public void setUsageAmount(int usageAmount) 
    {
        this.usageAmount = usageAmount;
    }

    public String getAuthorName() 
    {
        return authorName;
    }

    public String getBookName() 
    {
        return bookName;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }

    public String getPublisherName() 
    {
        return publisherName;
    }

    public boolean isSellingABook() 
    {
        return isSellingABook;
    }

    public int getBookEdition() 
    {
        return bookEdition;
    }

    public double getPrice() 
    {
        return price;
    }

    public int getUsageAmount() 
    {
        return usageAmount;
    }
}
