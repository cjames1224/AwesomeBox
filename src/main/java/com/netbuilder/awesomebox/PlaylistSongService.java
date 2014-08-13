package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

public class PlaylistSongService {

private EntityManager em;
	
	
	public PlaylistSongService(EntityManager em){
		this.em = em;
	}
	
	public void persistPlaylistSongList(List<PlaylistSong> list){
		em.getTransaction().begin();
		
		for(PlaylistSong a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listPlaylistSongs(){
		List<PlaylistSong> list = em.createQuery("SELECT a FROM PlaylistSong a",
				PlaylistSong.class).getResultList();
		
		for(PlaylistSong a: list){
			System.out.println(a.toString());
		}
	}
}
