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
		if (list == null) {
			throw new ValidationException("Invalid playlist persist");
		}
		
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
	
	public List<Playlist> getPlaylist(String name){
		if(name == null){
			throw new ValidationException("Invalid playlist name");
		}
		
		List<Playlist> list = em.createQuery("SELECT a FROM Playlist a where name =\'" + name + "\'",
				Playlist.class).getResultList();
		
		if(list == null || list.size() == 0){
			System.out.println("No such playlist with name = " + name);
		}
		
		for(Playlist a: list){
			System.out.println(a.toString());
		}
		
		return list;
	}
	
	public List<Playlist> getPlaylist(long id){
		if(id <= 0){
			throw new ValidationException("Invalid playlist id");
		}
		
		List<Playlist> list = em.createQuery("SELECT a FROM Playlist a where id ="+id,
				Playlist.class).getResultList();
		
		if(list == null || list.size() == 0){
			System.out.println("No such playlist with id = " + id);
		}
		
		for(Playlist a: list){
			System.out.println(a.toString());
		}
		
		return list;
	}
	
	public void updatePlaylist(Playlist playlist,String name) {
		if (playlist == null || name == null) {
			throw new ValidationException("Invalid playlist update");
		}
		em.getTransaction().begin();
		String query = "UPDATE Playlist SET name = \'" + name + "\' WHERE id = " + playlist.getId();
		em.createQuery(query);
		playlist.setName(name);
		em.getTransaction().commit();
	}
	
	public void deletePlaylist(Playlist playlist) {
		if (playlist == null) {
			throw new ValidationException("Invalid playlist delete");
		}
		em.getTransaction().begin();
		String query = "DELETE FROM Playlist WHERE id = " + playlist.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}
	

}
