package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class PlaylistController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6170237496115317228L;
	@Inject
	private SearchV2 search;
	private int playlistId;
	@Inject
	private PlaylistService playlistService;
	
	public int getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}
	
	public String getPlaylistDetails(int id) {
		this.playlistId = id;
		return "oneplaylist";
	}
	
	public Playlist getThisPlaylist() {
		return playlistService.getPlaylist(playlistId).get(0);
	}
	
	public List<Song> songsInPlaylist() {
		return search.searchPlaylist(playlistId);
	}

}
