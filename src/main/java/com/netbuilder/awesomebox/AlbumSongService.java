package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

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
}
