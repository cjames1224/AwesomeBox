package com.netbuilder.awesomebox.entities;

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

import com.netbuilder.awesomebox.AlbumType;

@Entity
@Table(name="Album")
public class Album {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
	private AlbumType type;
	
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
		type = AlbumType.stringToAlbumType(t);
		this.rating = rating;
		this.genre = genre;
		
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long iD) {
		id = iD;
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
		return AlbumType.albumTypeToString(type);
	}

	public void setType(String type) {
		this.type = AlbumType.stringToAlbumType(type);
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
		.append("\", id=\"" + id)
		.append("\", year=\"" + year)
		.append("\", type=\"" + type)
		.append("\", genre=\"" + genre)
		.append("\", rating=\"" + rating + "\" }")
		.toString();
		
	}

}
