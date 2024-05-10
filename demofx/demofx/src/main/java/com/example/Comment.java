package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comment")

// POJO class
public class Comment 
{
	@Id @Column(name = "commentID") private int id;

	@Column(name = "context") private String content;

	@Column(name = "ownerID") private int ownerID;

	@Column(name = "commentedPostID") private int commentedPostID;

    public int getId() {return id;}
    public String getContent() {return content;}
    public int getOwnerID() {return ownerID;}    
    public int getCommentedPostID() {return commentedPostID;}

    public void setId(int id){this.id=id;}

    public void setContent(String content){this.content=content;}

    public void setOwnerID(int ownerID){this.ownerID=ownerID;}

    public void setCommentedPostID(int commentedPostID){this.commentedPostID=commentedPostID;}
}