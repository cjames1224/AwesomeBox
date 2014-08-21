package com.netbuilder.awesomebox;

import java.io.Serializable;
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
	
	public String generalSearch() {
		List<Song> list = searchService.searchSongByAlbum(searchTerm).search();
		list.addAll(searchService.searchSongByArtist(searchTerm).search());
		list.addAll(searchService.searchSongName(searchTerm).search());
		setResults(list);
		return "results";
	}
	
	

}