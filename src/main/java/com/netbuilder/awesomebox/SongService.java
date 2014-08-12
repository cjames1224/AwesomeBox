package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

public class SongService {
private EntityManager em;
	
	
	public SongService(EntityManager em){
		this.em = em;
	}
	
	public void persistArtistList(List<Song> list){
		em.getTransaction().begin();
		
		for(Song a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listArtists(){
		List<Song> list = em.createQuery("SELECT s FROM Song s",
				Song.class).getResultList();
		
		for(Song s: list){
			System.out.println(s.toString());
		}
	}
}
