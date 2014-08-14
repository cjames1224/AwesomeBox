package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AlbumArtistService {
	
	private EntityManager em;
	
	public AlbumArtistService(EntityManager em){
		this.em = em;
	}
	
	public void persistAlbumArtistList(List<AlbumArtist> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
		em.getTransaction().begin();
		
		for(AlbumArtist a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listAlbumArtists(){
		List<AlbumArtist> list = em.createQuery("SELECT a FROM AlbumArtist a",
				AlbumArtist.class).getResultList();
		
		for(AlbumArtist a: list){
			System.out.println(a.toString());
		}
	}
	
//	public void updateAlbumArtist(AlbumArtist albumArtist,Album album,Artist artist) {
//		em.getTransaction().begin();
//		String query = "UPDATE AlbumArtist SET album = \'" + album + "\', artist = " + artist + " WHERE id = " + albumArtist.getId();
//		em.createQuery(query);
//		albumArtist.setArtist(artist);
//		albumArtist.setAlbum(album);
//		em.getTransaction().commit();
//	}
	
//	public void deleteAlbumArtist(AlbumArtist albumArtist) {
//		em.getTransaction().begin();
//		String query = "DELETE FROM AlbumArtist WHERE id = " + albumArtist.getId();
//		Query q = em.createQuery(query);
//		q.executeUpdate();
//		em.getTransaction().commit();
//	}

}
