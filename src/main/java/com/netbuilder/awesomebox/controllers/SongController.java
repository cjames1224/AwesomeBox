package com.netbuilder.awesomebox.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.netbuilder.awesomebox.dbaccess.SearchV2;
import com.netbuilder.awesomebox.entities.Album;
import com.netbuilder.awesomebox.entities.Artist;
import com.netbuilder.awesomebox.entities.Song;
import com.netbuilder.awesomebox.entityservices.SongService;


@Named
@SessionScoped
public class SongController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6538703322718372302L;


	@Inject
	private SongService songService;
	
	@Inject
	private SearchV2 search;
	

//	@Inject
//	private AlbumService albumService;
	
	private int songId;


	public SongService getSongService() {
		return songService;
	}

	public void setSongService(SongService songService) {
		this.songService = songService;
	}

	public String getSongName() {
		List<Song> songs = songService.listSongsByID(songId);
		return songs.get(0).getName();
	}

	public void setSongId(int id) {
		this.songId = id;
	}
	
	public int getSongId() {
		return songId;
	}
	
	public Album getSongAlbum() {
		return search.searchAlbumName(songId).get(0);	
	}
	
	public Album getSongAlbum(int songId) {
		return search.searchAlbumName(songId).get(0);	
	}
	
	public Artist getSongArtist() {
		return search.searchArtistName(songId).get(0);
	}
	public Artist getSongArtist(int songId) {
		return search.searchArtistName(songId).get(0);
	}
	
	public Song getSong() {
		return songService.listSongsByID(songId).get(0);
	}
	
	public Song getSong(int id) {
		return songService.listSongsByID(id).get(0);
	}
	
	public String getSongDetails(int id) {
		this.songId = id;
		return "song";
	}


	
	
}
