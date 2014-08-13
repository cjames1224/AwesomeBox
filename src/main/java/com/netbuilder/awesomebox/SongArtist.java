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

@Entity
@Table(name="Song_Artist")
public class SongArtist {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
	
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "Song_ID", referencedColumnName = "ID")
	@NotNull
	private Song song;
	
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "Artist_ID", referencedColumnName = "ID")
	@NotNull
	private Artist artist;
	

	public SongArtist(){}

	public SongArtist(Song s, Artist a){
		song = s;
		artist = a;
	}
	

	public int getId() {
		return id;
	}

	public Song getSong() {
		return song;
	}

	public Artist getArtist() {
		return artist;
	}
	
	public void setSong(Song s) {
		song = s;
	}

	public void setArtist(Artist a) {
		artist = a;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
		.append("SongArtist { Song=\"" + song)
		.append("\", artist=\"" + artist)
		.append("\", id=\"" + id)
		.toString();
	}


}
