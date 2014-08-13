package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PlaylistSongService {

private EntityManager em;
	
	
	public PlaylistSongService(EntityManager em){
		this.em = em;
	}
	
	public void persistPlaylistSongList(List<PlaylistSong> list){
		em.getTransaction().begin();
		
		for(PlaylistSong a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listPlaylistSongs(){
		List<PlaylistSong> list = em.createQuery("SELECT a FROM PlaylistSong a",
				PlaylistSong.class).getResultList();
		
		for(PlaylistSong a: list){
			System.out.println(a.toString());
		}
	}
	
	public void updatePlaylistSong(PlaylistSong playlistSong, Song newSong) {
		em.getTransaction().begin();
		String query = "UPDATE PlaylistSong SET song = \'" + newSong + " WHERE id = " + playlistSong.getId();
		em.createQuery(query);
		playlistSong.setSong(newSong);
		em.getTransaction().commit();
	}
	
	public void updatePlaylistSongTrackNumber(PlaylistSong playlistSong, int trackNumber) {
		em.getTransaction().begin();
		String query = "UPDATE PlaylistSong SET trackNumber = \'" + trackNumber + " WHERE id = " + playlistSong.getId();
		em.createQuery(query);
		playlistSong.setTrackNumber(trackNumber);
		em.getTransaction().commit();
	}
	
	
	public void deletePlaylistSong(PlaylistSong PlaylistSong) {
		em.getTransaction().begin();
		String query = "DELETE FROM PlaylistSong WHERE id = " + PlaylistSong.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}
}
