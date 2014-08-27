package com.netbuilder.awesomebox.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.netbuilder.awesomebox.dbaccess.SearchV2;
import com.netbuilder.awesomebox.entities.Playlist;
import com.netbuilder.awesomebox.entities.Song;
import com.netbuilder.awesomebox.entityservices.PlaylistService;

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
