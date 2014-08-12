package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

public class AlbumService {
	
	private EntityManager em;
	
	public AlbumService(EntityManager em){
		this.em = em;
	}
	
	public void persistAlbumList(List<Album> list){
		em.getTransaction().begin();
		
		for(Album a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listArtists(){
		List<Album> list = em.createQuery("SELECT a FROM Album a",
				Album.class).getResultList();
		
		for(Album a: list){
			System.out.println(a.toString());
		}
	}

}
