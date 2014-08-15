package com.netbuilder.awesomebox;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author Kobe
 *
 */
public class PlaylistSongService {

	private EntityManager em;
	
	/**
	 * Class constructor given entityManager parameter
	 * 
	 * @param em represents shared entity manager
	 */
	public PlaylistSongService(EntityManager em){
		this.em = em;
	}
	
	/**
	 * populates DB with playlistSongs
	 * 
	 * @param list list of PlaylistSongs to add to DB
	 */
	public void persistPlaylistSongList(List<PlaylistSong> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
		em.getTransaction().begin();
		
		for(PlaylistSong a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
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
			throw new ValidationException("Song already exists in playlist");
		}
		
		// get count of current playlist and add new song with last track number
		List<Song> songList = em.createQuery("SELECT s FROM Song s, Playlist p, PlaylistSong ps WHERE s.id = ps.song AND p.id = ps.playlist AND p.id = "+playlist.getId(),
				Song.class).getResultList();
		int count = songList.size();
		
		// add new PlaylistSong
		em.createQuery("INSERT INTO PlaylistSong (song, playlist, trackNumber) Values ("+song.getId()+", "+playlist.getId()+", "+(count+1)+")", 
				PlaylistSong.class);
	
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
	 * get list of all playlistSongs
	 * 
	 * @return list of all playlistSong
	 */
	public List<PlaylistSong> listPlaylistSongs(){
		List<PlaylistSong> list = em.createQuery("SELECT a FROM PlaylistSong a",
				PlaylistSong.class).getResultList();
		
		for(PlaylistSong a: list){
			System.out.println(a.toString());
		}
		return list;
	}
	
	/**
	 * replaces song in playlist.. this is probably in the wrong class.
	 * 
	 * @param playlistSong  song to be replaced
	 * @param newSong song to be replaced with
	 */
	public void updatePlaylistSong(PlaylistSong playlistSong, Song newSong) {
		if (playlistSong == null || newSong == null) {
			throw new ValidationException("Invalid PlaylistSong Update");
		}
		
		em.getTransaction().begin();
		String query = "UPDATE PlaylistSong SET song = " + newSong.getId() + " WHERE id = " + playlistSong.getId();
		em.createQuery(query);
		playlistSong.setSong(newSong);
		em.getTransaction().commit();
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
		
		em.getTransaction().begin();
		String query = "UPDATE PlaylistSong SET trackNumber = \'" + trackNumber + " WHERE id = " + playlistSong.getId();
		em.createQuery(query);
		playlistSong.setTrackNumber(trackNumber);
		em.getTransaction().commit();
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
		
		em.getTransaction().begin();
		String query = "DELETE FROM PlaylistSong WHERE id = " + playlistSong.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}
}
