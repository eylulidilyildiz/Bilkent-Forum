package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")

// POJO class
public class User 
{
	@Id @Column(name = "userID") private int id;

	@Column(name = "username") private String username;

	@Column(name = "password") private String password;

	@Column(name = "email") private String email;

	@Column(name = "postIDs") private String postIDs;

	@Column(name = "commentIDs") private String commentIDs;

	@Column(name = "Name") private String name;

	@Column(name = "Surname") private String surname;

	@Column(name = "Semester") private int semester;

	@Column(name = "Department") private String department;

	@Column(name = "upvotedPosts") private String upvotedPosts;

	@Column(name = "downvotedPosts") private String downvotedPosts;

	@Column(name = "bookmarkedPosts") private String bookmarkedPosts;

	public int getId() { return id; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getEmail() { return email; }
	public String getPostIDs() { return postIDs; }
	public String getCommentIDs() { return commentIDs; }
	public String getName() { return name; }
	public String getSurname() { return surname; }
	public int getSemester() { return semester; }
	public String getDepartment() { return department; }
	public String getUpvotedPosts() { return upvotedPosts; }
	public String getDownvotedPosts() { return downvotedPosts; }
	public String getBookmarkedPosts() { return bookmarkedPosts; }


	public void setPostIDs(String newIDs){
		this.postIDs = newIDs;
	}

	public void setCommentIDs(String newIDs){
		this.commentIDs = newIDs;
	}

	public void setEmail(String newMail){
		this.email = newMail;
	}

	public void setPassword(String newPassword){
		this.password = newPassword;
	}

	public void setUsername(String newUsername){
		this.username = newUsername;
	}

	public void setName(String newName){
		this.name = newName;
	}

	public void setSurname(String newSurname){
		this.surname = newSurname;
	}

	public void setSemester(int newSemester){
		this.semester = newSemester;
	}

	public void setDepartment(String newDepartment){
		this.department = newDepartment;
	}

	public void addUpvotedPosts(String postID){
		if(this.upvotedPosts != null)
		{
			this.upvotedPosts += ",";
		}
		this.upvotedPosts +=postID;
	}

	public void removeUpvotedPosts(String postID)
	{
		String [] currentUpvotedPosts =  this.upvotedPosts.split(",");
		String modifiedUpvotedPosts = "";
		for (int i = 0; i < currentUpvotedPosts.length; i++)
		{
			if(!postID.equals(currentUpvotedPosts[i]))
			{
				if(modifiedUpvotedPosts != null)
				{
					modifiedUpvotedPosts += ",";
				}
				modifiedUpvotedPosts += currentUpvotedPosts[i];
			}
		}
		this.upvotedPosts = modifiedUpvotedPosts;
	}

	public void addDownvotedPosts(String postID){
		if(this.downvotedPosts != null)
		{
			this.downvotedPosts += ",";
		}
		this.downvotedPosts +=postID;
	}

	public void removeDownvotedPosts(String postID)
	{
		String [] currentDownvotedPosts =  this.downvotedPosts.split(",");
		String modifiedDownvotedPosts = "";
		for (int i = 0; i < currentDownvotedPosts.length; i++)
		{
			if(!postID.equals(currentDownvotedPosts[i]))
			{
				if(modifiedDownvotedPosts != null)
				{
					modifiedDownvotedPosts += ",";
				}
				modifiedDownvotedPosts += currentDownvotedPosts[i];
			}
		}
		this.downvotedPosts = modifiedDownvotedPosts;
	}

	public void addBookmarkedPost(String postID){
		if(this.bookmarkedPosts != null)
		{
			this.bookmarkedPosts += ",";
		}
		this.bookmarkedPosts += postID;;
	}
}