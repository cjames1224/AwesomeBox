package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

public class ArtistService {
	
	private EntityManager em;
	
	
	public ArtistService(EntityManager em){
		this.em = em;
	}
	
	public void persistArtistList(List<Artist> list){
		em.getTransaction().begin();
		
		for(Artist a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listArtists(){
		List<Artist> list = em.createQuery("SELECT a FROM Artist a",
				Artist.class).getResultList();
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
	}

}
