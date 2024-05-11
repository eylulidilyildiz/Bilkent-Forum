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

	@Column(name = "friendsID") private String friendsID;

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
	public String getFriendsID() { return friendsID; }


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
		if (this.upvotedPosts != null) {
			this.upvotedPosts += "," + postID; 
		} else {
			this.upvotedPosts = postID; 
		}
	}

	public void removeUpvotedPosts(String postID)
	{
		String[] currentUpvotedPosts = this.upvotedPosts.split(",");
		StringBuilder modifiedUpvotedPosts = new StringBuilder();

		for (String upvotedPost : currentUpvotedPosts) {
			if (!postID.equals(upvotedPost)) {
				if (modifiedUpvotedPosts.length() > 0) {
					modifiedUpvotedPosts.append(",");
				}
				modifiedUpvotedPosts.append(upvotedPost);
			}
		}
		this.upvotedPosts = modifiedUpvotedPosts.toString();
	}

	public void addDownvotedPosts(String postID){
		if (this.downvotedPosts != null) {
			this.downvotedPosts += "," + postID; 
		} else {
			this.downvotedPosts = postID; 
		}
	}

	public void removeDownvotedPosts(String postID)
	{
		String[] currentDownvotedPosts = this.downvotedPosts.split(",");
		StringBuilder modifiedDownvotedPosts = new StringBuilder();

		for (String downvotedPost : currentDownvotedPosts) {
			if (!postID.equals(downvotedPost)) {
				if (modifiedDownvotedPosts.length() > 0) {
					modifiedDownvotedPosts.append(",");
				}
				modifiedDownvotedPosts.append(downvotedPost);
			}
		}
		this.downvotedPosts = modifiedDownvotedPosts.toString();
	}

	public void addBookmarkedPost(String postID){
		if (this.bookmarkedPosts != null) {
			this.bookmarkedPosts += "," + postID; 
		} else {
			this.bookmarkedPosts = postID; 
		}
	}

	public void removeBookmarkedPosts(String postID)
	{
		String[] currentBookmarkedPosts = this.bookmarkedPosts.split(",");
		StringBuilder modifiedBookmarkedPosts = new StringBuilder();

		for (String bookmarkedPost : currentBookmarkedPosts) {
			if (!postID.equals(bookmarkedPost)) {
				if (modifiedBookmarkedPosts.length() > 0) {
					modifiedBookmarkedPosts.append(",");
				}
				modifiedBookmarkedPosts.append(bookmarkedPost);
			}
		}
		this.bookmarkedPosts = modifiedBookmarkedPosts.toString();
	}

	public void addFriendsID(String newID){
		if (this.friendsID != null) {
			this.friendsID += "," + newID; 
		} else {
			this.friendsID = newID; 
		}
	}

	public void removeFriendsID(String postID)
	{
		String[] currentFriendsID = this.friendsID.split(",");
		StringBuilder modifiedFriendsID = new StringBuilder();

		for (String friendsID : currentFriendsID) {
			if (!postID.equals(friendsID)) {
				if (modifiedFriendsID.length() > 0) {
					modifiedFriendsID.append(",");
				}
				modifiedFriendsID.append(friendsID);
			}
		}
		this.friendsID = modifiedFriendsID.toString();
	}
}