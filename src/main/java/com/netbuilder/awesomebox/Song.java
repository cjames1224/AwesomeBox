package com.netbuilder.awesomebox;

import javax.enterprise.context.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Song")
@XmlRootElement
public class Song {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;
	@Column(name="Length", nullable = false)
	@NotNull
	private int length;
	@Column(name="Plays", nullable = false)
	@NotNull
	private int plays;
	@Column(name="File_Location",nullable = false)
	@NotNull
	private String fileLocation;
	@Column(name="Genre")
	private String genre;
	@Column(name="Rating")
	private int rating;
	
	public Song() {
		
	}
	
	public Song(String name,int length,String fileLocation,String genre,int rating) {
		this.name = name;
		this.length = length;
		this.fileLocation = fileLocation;
		this.genre = genre;
		this.rating = rating;
		this.plays = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPlays() {
		return plays;
	}

	public void setPlays(int plays) {
		this.plays = plays;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
		.append("Song { name=\"" + name)
		.append("\", id=\"" + id)
		.append("\", length=\"" + length)
		.append("\", plays=\"" + plays)
		.append("\", file location=\"" + fileLocation)
		.append("\", genre=\"" + genre)
		.append("\", rating=\"" + rating + "\" }")
		.toString();
	}
}
