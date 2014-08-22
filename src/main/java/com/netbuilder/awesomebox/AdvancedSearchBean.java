package com.netbuilder.awesomebox;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class AdvancedSearchBean implements Serializable {

	@Inject
	private SearchController sc;
	private static final long serialVersionUID = 4380233612056317331L;
	private boolean songRatingCheck = false, 
			songPlaysCheck = false,
			songGenreCheck = false, 
			albumRatingCheck = false,
			artistRatingCheck = false, 
			songYearCheck = false,
			songLengthCheck = false;
	private int starEquality = 0, starValue = 3, 
			playsEquality = 0, playsValue = 10, 
			albumStarEquality = 0, albumStarValue = 3, 
			artistStarEquality = 0, artistStarValue = 3, 
			songYearEquality = 0, songYearValue = 1999,
			songLengthEquality = 0, songLengthValue = 175;
	private String genreValue = "";

	public AdvancedSearchBean() {
	}
	
	public String search(){
		boolean[] checks = new boolean[]{songRatingCheck, songPlaysCheck, songGenreCheck,
				 albumRatingCheck, artistRatingCheck, songYearCheck, songLengthCheck};
		for(int i = 0; i < checks.length; i++){
			if(checks[i]){
				//caseSearch(i);
			}
		}
		sc.search();
		return "results";
	}
	
	
	public boolean toggleSongRatingCheck() {
		songRatingCheck = !songRatingCheck;
		return songRatingCheck;
	}

	public boolean isSongRatingCheck() {
		return songRatingCheck;
	}

	public void setSongRatingCheck(boolean songRatingCheck) {
		this.songRatingCheck = songRatingCheck;
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

	public int getStarEquality() {
		return starEquality;
	}

	public void setStarEquality(int starEquality) {
		this.starEquality = starEquality;
	}

	public int getStarValue() {
		return starValue;
	}

	public void setStarValue(int starValue) {
		this.starValue = starValue;
	}

	public int getPlaysEquality() {
		return playsEquality;
	}

	public void setPlaysEquality(int playsEquality) {
		this.playsEquality = playsEquality;
	}

	public int getPlaysValue() {
		return playsValue;
	}

	public void setPlaysValue(int playsValue) {
		this.playsValue = playsValue;
	}

	public String getGenreValue() {
		return genreValue;
	}

	public void setGenreValue(String genreValue) {
		this.genreValue = genreValue;
	}

	public int getAlbumStarEquality() {
		return albumStarEquality;
	}

	public void setAlbumStarEquality(int albumStarEquality) {
		this.albumStarEquality = albumStarEquality;
	}

	public int getAlbumStarValue() {
		return albumStarValue;
	}

	public void setAlbumStarValue(int albumStarValue) {
		this.albumStarValue = albumStarValue;
	}

	public int getArtistStarEquality() {
		return artistStarEquality;
	}

	public void setArtistStarEquality(int artistStarEquality) {
		this.artistStarEquality = artistStarEquality;
	}

	public int getArtistStarValue() {
		return artistStarValue;
	}

	public void setArtistStarValue(int artistStarValue) {
		this.artistStarValue = artistStarValue;
	}

	public int getSongYearEquality() {
		return songYearEquality;
	}

	public void setSongYearEquality(int songYearEquality) {
		this.songYearEquality = songYearEquality;
	}

	public int getSongYearValue() {
		return songYearValue;
	}

	public void setSongYearValue(int songYearValue) {
		this.songYearValue = songYearValue;
	}

	public int getSongLengthEquality() {
		return songLengthEquality;
	}

	public void setSongLengthEquality(int songLengthEquality) {
		this.songLengthEquality = songLengthEquality;
	}

	public int getSongLengthValue() {
		return songLengthValue;
	}

	public void setSongLengthValue(int songLengthValue) {
		this.songLengthValue = songLengthValue;
	}
}

