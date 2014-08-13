package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

public class AlbumArtistService {
	
	private EntityManager em;
	
	public AlbumArtistService(EntityManager em){
		this.em = em;
	}
	
	public void persistAlbumArtistList(List<AlbumArtist> list){
		em.getTransaction().begin();
		
		for(AlbumArtist a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listAlbumArtists(){
		List<AlbumArtist> list = em.createQuery("SELECT a FROM Album a",
				AlbumArtist.class).getResultList();
		
		for(AlbumArtist a: list){
			System.out.println(a.toString());
		}
	}

}
