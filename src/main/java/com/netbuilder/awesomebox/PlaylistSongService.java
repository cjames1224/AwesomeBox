package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author Kobe
 *
 */
@Named
@Stateless
public class PlaylistSongService implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;

	public void removePlaylistSongList(List<PlaylistSong> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		for(PlaylistSong a : list){
			em.remove(em.merge(a));
		}
	}
	
	
	public List<PlaylistSong> getPlaylistSongList() {
		return em.createQuery("SELECT a FROM PlaylistSong a",
				PlaylistSong.class).getResultList();
	}


	public void persistPlaylistSongList(List<PlaylistSong> playlistSongList) {
		if (playlistSongList == null) {
			throw new ValidationException("Invalid List");
		}
		
		for(PlaylistSong ps : playlistSongList){
			em.persist(ps);
		}
	}

	/**
	 * Adds given song to given playlist by creating new playlistSong 
	 * 
	 * @param playlist playlist to be added to 
	 * @param song song to be added
	 */
	public void addSongToPlaylist(Playlist playlist, Song song) {
		// throws error if song already exists
		if(em.createQuery("SELECT ps from PlaylistSong ps WHERE ps.song = "+song.getId()).getResultList().size() > 0 ) {
			//throw new ValidationException("Song already exists in playlist");
		}
		
		List<PlaylistSong> list = em.createQuery("SELECT ps FROM PlaylistSong ps WHERE ps.playlist =" + playlist.getId(),
				PlaylistSong.class).getResultList();
		System.out.println(list.size());
		PlaylistSong  ps = new PlaylistSong(playlist, song, list.size()+1);
		List<PlaylistSong> list23 = new ArrayList<PlaylistSong>();
		list23.add(ps);
		
		this.persistPlaylistSongList(list23);
		//persistPlaylistSongList(list);
	}
	
	/**
	 * reorders playlist given a song and a position to move it to. 
	 * 
	 * @param playlist playlist to change
	 * @param song song selected to move
	 * @param trackNumber position the song is going to move to
	 */
	public void reorderSong(Playlist playlist, Song song, int trackNumber) {
		List<PlaylistSong> playlistSongs = em.createQuery("SELECT ps FROM PlaylistSong ps WHERE ps.song = "+song.getId(),
				PlaylistSong.class).getResultList();
		
		int totalTracks = playlistSongs.size();
		
		// validation on track number and playlist
		if (totalTracks == 0) {
			throw new ValidationException("No songs in this playlist...");
		} else if (totalTracks < trackNumber) {
			throw new ValidationException("Invalid new track number");
		}
		
		// adding 1 to all or removing 1 to all
		
		int currentTrackNumber = em.createQuery("SELECT ps FROM PlaylistSong ps WHERE ps.song = "+song.getId(),
				PlaylistSong.class).getResultList().get(0).getTrackNumber();
		if (currentTrackNumber == trackNumber) {
			// do nothing
			return;
		} 
		if (currentTrackNumber < trackNumber) {
			// move tracks up in list...
			for (int i = currentTrackNumber+1; i <= trackNumber; i++) {
				em.createQuery("Update PlaylistSong SET trackNumber = " + (i-1)+" WHERE trackNumber = "+i);
			}
			em.createQuery("Update PlaylistSong SET tracknumber = "+trackNumber+" WHERE song ="+song.getId());
		} else {
			// move tracks down in list...
			for (int i = trackNumber; i < currentTrackNumber; i++) {
				em.createQuery("Update  PlaylistSong SET trackNumber = " + (i+1)+" WHERE trackNumber = "+i);
			}
			em.createQuery("Update PlaylistSong SET tracknumber = "+trackNumber+" WHERE song ="+song.getId());
		}

		
		
	}

	/**
	 * replaces song in playlist.. this is probably in the wrong class.
	 * 
	 * @param playlistSong  song to be replaced
	 * @param newSong song to be replaced with
	 */
	public void updatePlaylistSong(PlaylistSong playlistSong) {
		if (playlistSong == null) {
			throw new ValidationException("Invalid PlaylistSong Update");
		}
		
		em.persist(em.merge(playlistSong));

	}
	
	/**
	 * this is a dumb function that takes a playlist song and updates the track number.
	 * 
	 * @param playlistSong
	 * @param trackNumber
	 */
	public void updatePlaylistSongTrackNumber(PlaylistSong playlistSong, int trackNumber) {
		if (playlistSong == null) {
			throw new ValidationException("Invalid PlaylistSong Update");
		}
		
		String query = "UPDATE PlaylistSong SET trackNumber = \'" + trackNumber + " WHERE id = " + playlistSong.getId();
		em.createQuery(query);
		playlistSong.setTrackNumber(trackNumber);
	}
	
	/**
	 * deletes song from playlist by deleting playlist song
	 * 
	 * @param playlistSong
	 */
	public void deletePlaylistSong(PlaylistSong playlistSong) {
		if (playlistSong == null) {
			throw new ValidationException("Invalid PlaylistSong Update");
		}
		
		em.remove(em.merge(playlistSong));
	}
}
