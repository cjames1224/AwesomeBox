package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public void updatePlaylist(Playlist Playlist,String name) {
		em.getTransaction().begin();
		String query = "UPDATE Playlist SET name = \'" + name + "\' WHERE id = " + Playlist.getId();
		em.createQuery(query);
		Playlist.setName(name);
		em.getTransaction().commit();
	}
	
	public void deletePlaylist(Playlist Playlist) {
		em.getTransaction().begin();
		String query = "DELETE FROM Playlist WHERE id = " + Playlist.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}
	

}
