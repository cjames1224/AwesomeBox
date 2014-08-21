package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;


@Named
@SessionScoped
public class SongController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6538703322718372302L;


	@Inject
	private SongService songService;
	

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
	
	public void getSongAlbum() {
		//List<Song> songList = songService.listSongsByName(songName);
		//song = songList.get(0);
		
		
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
