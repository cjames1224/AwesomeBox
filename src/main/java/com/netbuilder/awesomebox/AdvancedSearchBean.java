package com.netbuilder.awesomebox;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AdvancedSearchBean implements Serializable{

	private static final long serialVersionUID = 4380233612056317331L;
	private boolean songRatingCheck = true, songPlaysCheck, songGenreCheck, albumRatingCheck,
	artistRatingCheck, songYearCheck, songLengthCheck;
	
	public AdvancedSearchBean(){}

	public boolean isSongRatingCheck() {
		return songRatingCheck;
	}

	public void setSongRatingCheck(boolean songRatingCheck) {
		this.songRatingCheck = songRatingCheck;
	}
	
	public boolean toggleSongRatingCheck(){
		songRatingCheck = !songRatingCheck;
		return songRatingCheck;
	}

	public boolean isSongPlaysCheck() {
		return songPlaysCheck;
	}

	public void setSongPlaysCheck(boolean songPlaysCheck) {
		this.songPlaysCheck = songPlaysCheck;
	}

	public boolean isSongGenreCheck() {
		return songGenreCheck;
	}

	public void setSongGenreCheck(boolean songGenreCheck) {
		this.songGenreCheck = songGenreCheck;
	}

	public boolean isAlbumRatingCheck() {
		return albumRatingCheck;
	}

	public void setAlbumRatingCheck(boolean albumRatingCheck) {
		this.albumRatingCheck = albumRatingCheck;
	}

	public boolean isArtistRatingCheck() {
		return artistRatingCheck;
	}

	public void setArtistRatingCheck(boolean artistRatingCheck) {
		this.artistRatingCheck = artistRatingCheck;
	}

	public boolean isSongYearCheck() {
		return songYearCheck;
	}

	public void setSongYearCheck(boolean songYearCheck) {
		this.songYearCheck = songYearCheck;
	}

	public boolean isSongLengthCheck() {
		return songLengthCheck;
	}

	public void setSongLengthCheck(boolean songLengthCheck) {
		this.songLengthCheck = songLengthCheck;
	}
	
}
