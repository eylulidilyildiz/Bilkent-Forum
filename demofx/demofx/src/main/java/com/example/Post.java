package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Post")

// POJO class
public class Post 
{
	@Id @Column(name = "postID") private int id;

	@Column(name = "content") private String content;

	@Column(name = "date") private String date;

	@Column(name = "ownerID") private int ownerID;

	@Column(name = "upvotes") private int upvotes;

	@Column(name = "downvotes") private int downvotes;

	@Column(name = "commentIDs") private String commentIDs;

	@Column(name = "isSalePost") private boolean isSalePost;

	@Column(name = "bookTitle") private String bookTitle;

	@Column(name = "authorName") private String authorName;

    @Column(name = "courseName") private String courseName;

    @Column(name = "price") private Double price;

    @Column(name = "usageAmount") private Integer usageAmount;

    @Column(name = "publisherName") private String publisherName;

    @Column(name = "bookEdition") private String bookEdition;

    public int getId() {return id;}
    public String getContent() {return content;}
    public String getDate() {return date;}    
    public int getOwnerID() {return ownerID;}
    public int getUpvotes() {return upvotes;}
    public int getDownvotes() {return downvotes;}
    public String getCommentIDs() {return commentIDs;}
    public boolean getIsSalePost() {return isSalePost;}
    public String getBookTitle() {return bookTitle;}
    public String getAuthorName() {return authorName;}
    public String getCourseName() {return courseName;}
    public Double getPrice() {return price;}
    public Integer getUsageAmount() {return usageAmount;}
    public String getPublisherName() {return publisherName;}
    public String getBookEdition() {return bookEdition;}

    public void setId(int id){this.id=id;}

    public void setContent(String content){this.content=content;}

    public void setDate(String date){this.date=date;}

    public void setOwnerID(int ownerID){this.ownerID=ownerID;}

    public void setUpvotes(int upvotes){this.upvotes=upvotes;}

    public void increaseUpvotes(){this.upvotes++;}

    public void decreaseUpvotes(){this.upvotes--;}

    public void increaseDownvotes(){this.downvotes++;}

    public void decreaseDownvotes(){this.downvotes--;}

    public void setDownvotes(int downvotes){this.downvotes=downvotes;}

    public void setCommentIDs(String commentIDs){this.commentIDs=commentIDs;}

    public void setIsSalePost(boolean isSalePost){this.isSalePost=isSalePost;}

    public void setBookTitle(String bookTitle){this.bookTitle=bookTitle;}

    public void setAuthorName(String authorName){this.authorName=authorName;}

    public void setCourseName(String courseName){this.courseName=courseName;}

    public void setPrice(double price){this.price=price;}

    public void setUsageAmount(int usageAmount){this.usageAmount=usageAmount;}

    public void setPublisherName(String publisherName){this.publisherName=publisherName;}

    public void setBookEdition(String bookEdition){this.bookEdition=bookEdition;}
}