package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

public class PlaylistService {
	
	private EntityManager em;
	
	public PlaylistService(EntityManager em){
		this.em = em;
	}
	
	public void persistPlaylistList(List<Playlist> list){
		em.getTransaction().begin();
		
		for(Playlist a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listPlaylists(){
		List<Playlist> list = em.createQuery("SELECT a FROM Playlist a",
				Playlist.class).getResultList();
		
		for(Playlist a: list){
			System.out.println(a.toString());
		}
	}

}
