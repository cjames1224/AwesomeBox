package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class SearchController implements Serializable {

	// attributes
	private String searchTerm;

	@Inject
	private SearchV2 searchService;
	
	private List<Song> results;

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
	
	public String search() {
		
		return "results";
	}
	
	public String generalSearch() {
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