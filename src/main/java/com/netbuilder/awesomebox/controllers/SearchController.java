package com.netbuilder.awesomebox.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.netbuilder.awesomebox.dbaccess.SearchV2;
import com.netbuilder.awesomebox.entities.Song;


@Named
@SessionScoped
public class SearchController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 746095038289392329L;

	// attributes
	private String searchTerm;

	@Inject
	private SearchV2 searchService;
	
	private List<Song> results;
	
	private boolean songRatingCheck, 
			songPlaysCheck,
			songGenreCheck, 
			albumRatingCheck,
			artistRatingCheck, 
			songYearCheck,
			songLengthCheck;
	private int starEquality = 0, starValue = 3, 
			playsEquality = 0, playsValue = 10, 
			albumStarEquality = 0, albumStarValue = 3, 
			artistStarEquality = 0, artistStarValue = 3, 
			songYearEquality = 0, songYearValue = 1999,
			songLengthEquality = 0, songLengthValue = 175;
	private String genreValue = "";

	@PostConstruct
	public void setup() {
		this.songRatingCheck = false;
		this.songPlaysCheck = false;
		this.songGenreCheck = false;
		this.albumRatingCheck = false;
		this.artistRatingCheck = false;
		this.songYearCheck = false;
		this.songLengthCheck = false;
	}
	// getters and setters
	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public SearchV2 getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchV2 searchService) {
		this.searchService = searchService;
	}
	
	public String search(){
		boolean isJustKeyword = true;
		searchService.clear();
		boolean[] checks = new boolean[]{songRatingCheck, songPlaysCheck, songGenreCheck,
				 albumRatingCheck, artistRatingCheck, songYearCheck, songLengthCheck};
		for(int i = 0; i < checks.length; i++){
			//System.out.println("goes thru checks");
			if(checks[i]){
				//System.out.println("does stuff");
				isJustKeyword = false;
				caseSearch(i);
			} 
		}
		if(isJustKeyword) {
			generalSearch();
		} else {
			search2();
		}
		this.setup();
		return "results";
	}
	
	public void caseSearch(int i){
		switch(i){
		case 0:
			searchRating(starEquality, starValue);
			break;
		case 1:
			searchPlays(playsEquality, playsValue);
			break;
		case 2:
			searchGenre(genreValue);
			break;
		case 3:
			searchAlbumRating(albumStarEquality, albumStarValue);
			break;
		case 4:
			searchArtistRating(artistStarEquality, artistStarValue);
			break;
		case 5:
			searchYear(songYearEquality, songYearValue);
			break;
		case 6:
			searchLength(songLengthEquality, songLengthValue);
			break;
		default:
			break;
		}
	}


//	public String search() {
//		results = searchService.generalSearch(searchTerm);
//		return "results";
//	}

	public List<Song> getResults() {
		return results;
	}

	public void setResults(List<Song> results) {
		this.results = results;
	}
	
	public void searchRating(int equality,int rating) {
		if (equality == 0) {
			this.searchService.searchSongLesserRating(rating);
		} else if (equality == 1) {
			this.searchService.searchSongGreaterRating(rating);
		} else {
			this.searchService.searchSongRating(rating);
		}
	}
	
	public void searchLength(int equality,int length) {
		if (equality == 0) {
			this.searchService.searchSongLesserLength(length);
		} else if (equality == 1) {
			this.searchService.searchSongGreaterLength(length);
		} else {
			this.searchService.searchSongLength(length);
		}
	}
	
	public void searchPlays(int equality,int plays) {
		if (equality == 0) {
			this.searchService.searchSongLesserPlays(plays);
		} else if (equality == 1) {
			this.searchService.searchSongGreaterPlays(plays);
		} else {
			this.searchService.searchSongPlays(plays);
		}
	}
	
	public void searchYear(int equality,int year) {
		if (equality == 0) {
			this.searchService.searchSongLesserYear(year);
		} else if (equality == 1) {
			this.searchService.searchSongGreaterYear(year);
		} else {
			this.searchService.searchSongYear(year);
		}
	}
	
	public void searchAlbumRating(int equality,int rating) {
		if (equality == 0) {
			this.searchService.searchSongAlbumLesserRating(rating);
		} else if (equality == 1) {
			this.searchService.searchSongAlbumGreaterRating(rating);
		} else {
			this.searchService.searchSongAlbumRating(rating);
		}
	}
	
	public void searchArtistRating(int equality,int rating) {
		if (equality == 0) {
			this.searchService.searchSongArtistLesserRating(rating);
		} else if (equality == 1) {
			this.searchService.searchSongArtistGreaterRating(rating);
		} else {
			this.searchService.searchSongArtistRating(rating);
		}
	}
	
	public void searchGenre(String term) {
		this.searchService.searchSongGenre(term);
	}
	
	
//	ongRatingCheck = false, 
//			songPlaysCheck = false,
//			songGenreCheck = false, 
//			albumRatingCheck = false,
//			artistRatingCheck = false, 
//			songYearCheck = false,
//			songLengthCheck = false;
	public void toggleSongPlaysCheck() {
		songPlaysCheck = !songPlaysCheck;
		//return songPlaysCheck;
	}
	
	public void toggleSongGenreCheck() {
		songGenreCheck = !songGenreCheck;
	//	return songGenreCheck;
	}
	
	public void toggleAlbumRatingCheck() {
		albumRatingCheck = !albumRatingCheck;
		//return albumRatingCheck;
	}
	
	public void toggleArtistRatingCheck() {
		artistRatingCheck = !artistRatingCheck;
		//return artistRatingCheck;
	}
	
	public void toggleSongYearCheck() {
		songYearCheck = !songYearCheck;
		//return songYearCheck;
	}
	public void toggleSongLengthCheck() {
		songLengthCheck = !songLengthCheck;
		//return songLengthCheck;
	}
	public void toggleSongRatingCheck() {
		songRatingCheck = !songRatingCheck;
		//return songRatingCheck;
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
	
	public List<Song> search2() {
		List<Song> list = this.searchService.search();
		if (list == null) {
			setResults(list);
			return list;
		}
		List<Song> list2 = searchService.searchSongByAlbum(searchTerm).search();
		list2.addAll(searchService.searchSongByArtist(searchTerm).search());
		list2.addAll(searchService.searchSongName(searchTerm).search());
		List<Song> list3 = new ArrayList<Song>();
		for (int i = 0;i < list.size();i++) {
			for (int j = 0;j < list2.size();j++) {
				if (list.get(i).getId() == list2.get(j).getId()) {
					list3.add(list.get(i));
				}
			}
		}
		for (int i = 0;i < list3.size();i++) {
			for (int j = i+1;j < list3.size();j++) {
				if (list3.get(i).getId() == list3.get(j).getId()) {
					list3.remove(j);
				}
			}
		}
		setResults(list3);
		return list3;
	}
	
	public String generalSearch() {
		searchService.clear();
		List<Song> list = searchService.searchSongByAlbum(searchTerm).search();
		list.addAll(searchService.searchSongByArtist(searchTerm).search());
		list.addAll(searchService.searchSongName(searchTerm).search());
		for (int i = 0;i < list.size();i++) {
			for (int j = i+1;j < list.size();j++) {
				if (list.get(i).getId() == list.get(j).getId()) {
					list.remove(j);
				}
			}
		}
		setResults(list);
		return "results";
	}
	
	

}