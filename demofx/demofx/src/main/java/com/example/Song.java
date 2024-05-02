package com.example;

// Java Program to Illustrate Creation of Simple POJO Class

// Importing required classes
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "song")

// POJO class
public class Song {

	@Id @Column(name = "songId") private int id;

	@Column(name = "songName") private String songName;

	@Column(name = "singer") private String artist;

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getSongName() { return songName; }

	public void setSongName(String songName)
	{
		this.songName = songName;
	}

	public String getArtist() { return artist; }

	public void setArtist(String artist)
	{
		this.artist = artist;
	}
}
