package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Named
@Stateless
public class AlbumSongService implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;
@Inject
private EntityManager em;
	

	
	public List<AlbumSong> getAlbumSongList() {
		List<AlbumSong> list = em.createQuery("SELECT a FROM AlbumSong a",
				AlbumSong.class).getResultList();
		return list;
	}
	
	public void persistAlbumSongList(List<AlbumSong> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
		for(AlbumSong a: list){
			em.persist(a);
		}
	}
	
	public void removeAlbumSongList(List<AlbumSong> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		for(AlbumSong a : list){
			em.remove(em.merge(a));
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
