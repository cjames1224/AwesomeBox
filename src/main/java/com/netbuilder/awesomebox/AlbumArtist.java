package com.netbuilder.awesomebox;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Album_Artist")
public class AlbumArtist {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="Album_ID", referencedColumnName="ID", nullable=false )
	@NotNull
	private Album album;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="Artist_ID", referencedColumnName="ID", nullable=false )
	@NotNull
	private Artist artist;

	// constructors
	public AlbumArtist() {

	}
	
	public AlbumArtist(Album album, Artist artist) {
		this.album = album;
		this.artist = artist;
	}

	// getters and setters
	public int getId() {
		return id;
	}


	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
		.append("AlbumArtist { id=\"" + id)
		.append("\", album=\"" + album)
		.append("\", artist=\"" + artist)
		.toString();
	}
	
	
}
