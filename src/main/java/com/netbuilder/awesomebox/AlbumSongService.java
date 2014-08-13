package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AlbumSongService {

private EntityManager em;
	
	
	public AlbumSongService(EntityManager em){
		this.em = em;
	}
	
	public void persistAlbumSongList(List<AlbumSong> list){
		em.getTransaction().begin();
		
		for(AlbumSong a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listAlbumSongs(){
		List<AlbumSong> list = em.createQuery("SELECT a FROM AlbumSong a",
				AlbumSong.class).getResultList();
		
		for(AlbumSong a: list){
			System.out.println(a.toString());
		}
	}
	
//	public void updateAlbumSong(AlbumSong albumSong,Album album,Song song,int trackNumber) {
//		em.getTransaction().begin();
//		String query = "UPDATE AlbumSong SET album = \'" + album + "\', song = " + song + " WHERE id = " + albumSong.getId();
//		em.createQuery(query);
//		albumSong.setAlbum(album);
//		albumSong.setSong(song);
//		albumSong.setTrackNumber(trackNumber);
//		em.getTransaction().commit();
//	}
//	
//	public void deleteAlbumSong(AlbumSong albumSong) {
//		em.getTransaction().begin();
//		String query = "DELETE FROM AlbumSong WHERE id = " + albumSong.getId();
//		Query q = em.createQuery(query);
//		q.executeUpdate();
//		em.getTransaction().commit();
//	}
}
