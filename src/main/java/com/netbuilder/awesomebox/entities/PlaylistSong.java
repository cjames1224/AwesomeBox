package com.netbuilder.awesomebox.entities;

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
@Table(name="Playlist_Song")
public class PlaylistSong {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Playlist_ID",referencedColumnName = "ID")
	@NotNull
	private Playlist playlist;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Song_ID",referencedColumnName = "ID")
	@NotNull
	private Song song;
	@Column(name="Track_Number",nullable = false)
	@NotNull
	private int trackNumber;
	
	public PlaylistSong() {
		
	}
	
	public PlaylistSong(Playlist playlist,Song song,int trackNumber) {
		this.playlist = playlist;
		this.song = song;
		this.trackNumber = trackNumber;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
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
		.append("PlaylistSong { Song=\"" + song)
		.append("\", playlist=\"" + playlist)
		.append("\", track number=\"" + trackNumber + "\" }")
		.append("\", id=\"" + id)
		.toString();
	}
}
