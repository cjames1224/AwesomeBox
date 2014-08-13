package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

public class SongArtistService {

private EntityManager em;
	
	
	public SongArtistService(EntityManager em){
		this.em = em;
	}
	
	public void persistSongArtistList(List<SongArtist> list){
		em.getTransaction().begin();
		
		for(SongArtist sa: list){
			em.persist(sa);
		}
		
		em.getTransaction().commit();
	}
	
	public void listSongArtists(){
		List<SongArtist> list = em.createQuery("SELECT sa FROM SongArtist sa",
				SongArtist.class).getResultList();
		
		for(SongArtist s: list){
			System.out.println(s.toString());
		}
	}
	
}
