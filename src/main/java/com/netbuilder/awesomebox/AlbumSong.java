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
@Table(name="Album_Song")
public class AlbumSong {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Album_ID",referencedColumnName = "ID")
	@NotNull
	private Album album;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Song_ID",referencedColumnName = "ID")
	@NotNull
	private Song song;
	@Column(name="Track_Number",nullable = false)
	@NotNull
	private int trackNumber;
	
	public AlbumSong() {
		
	}
	
	public AlbumSong(Album album,Song song,int trackNumber) {
		this.album = album;
		this.song = song;
		this.trackNumber = trackNumber;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
		.append("AlbumSong { Song=\"" + song)
		.append("\", album=\"" + album)
		.append("\", track number=\"" + trackNumber + "\" }")
		.append("\", id=\"" + id)
		.toString();
	}
}
