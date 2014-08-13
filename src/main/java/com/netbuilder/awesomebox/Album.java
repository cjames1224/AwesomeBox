package com.netbuilder.awesomebox;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.usertype.UserType;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="album")
public class Album {
	
	private enum albumType {Single,Compilation,Regular};
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	@Column(name="Name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;
	
	@Column(name="Year", nullable = false)
	@NotNull
	@Range(min = 1, max = 2015)
	private int year;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Type")
	private albumType type;
	
	@Column(name="Rating")
	@Range(min = 1, max = 5)
	private int rating;
	
	@Column(name="Genre", length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String genre;
	
	
	public Album(){
		
	}
	
	public Album(String name, int year, String t, int rating, String genre ){
		this.name = name;
		this.year = year;
		type = this.stringToAlbumType(t);
		this.rating = rating;
		this.genre = genre;
		
	}
	
	private albumType stringToAlbumType (String s) {
		switch (s) {
		case "Regular": return albumType.Regular;
		case "Compilation": return albumType.Compilation;
		default: return albumType.Single;
		}
	}
	
	private String albumTypeToString (albumType t) {
		switch (t) {
		case Regular: return "Regular";
		case Compilation: return "Compilation";
		default: return "Single";
		}
	}
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getType() {
		return this.albumTypeToString(type);
	}

	public void setType(String type) {
		this.type = this.stringToAlbumType(type);
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString(){
		return new StringBuilder()
		.append("Album { name=\"" + name)
		.append("\", id=\"" + ID)
		.append("\", year=\"" + year)
		.append("\", type=\"" + type)
		.append("\", genre=\"" + genre)
		.append("\", rating=\"" + rating + "\" }")
		.toString();
		
	}

}