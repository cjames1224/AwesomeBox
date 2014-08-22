package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Named
@Stateless
public class PlaylistService implements Serializable{
	
	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;
	

	public void removePlaylistList(List<Playlist> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		for(Playlist a : list){
			em.remove(em.merge(a));
		}
	}
	
	
	public List<Playlist> getPlayList() {
		List<Playlist> list = em.createQuery("SELECT a FROM Playlist a",
				Playlist.class).getResultList();
		return list;
	}
	

	public void createPlaylist(User user, String name) {
		Playlist playlist = new Playlist(name, user);
		List<Playlist> playlistList = new ArrayList<Playlist>();
		playlistList.add(playlist);
		this.persistPlaylistList(playlistList);
		
	}
	
	
	public void persistPlaylistList(List<Playlist> list){
		if (list == null) {
			throw new ValidationException("Invalid playlist persist");
		}

		for(Playlist a: list){
			em.persist(a);
		}
		

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
	
	public void updatePlaylist(Playlist playlist) {
		if (playlist == null) {
			throw new ValidationException("Invalid playlist update");
		}
		em.persist(em.merge(playlist));
	
	}
	
	public void deletePlaylist(Playlist playlist) {
		if (playlist == null) {
			throw new ValidationException("Invalid playlist delete");
		}
		em.remove(em.merge(playlist));
	}


	
	

}
