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
}