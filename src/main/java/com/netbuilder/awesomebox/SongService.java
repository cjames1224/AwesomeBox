package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SongService {
private EntityManager em;
	
	
	public SongService(EntityManager em){
		this.em = em;
	}
	
	public void persistSongList(List<Song> list){
		em.getTransaction().begin();
		
		for(Song a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listSongs(){
		List<Song> list = em.createQuery("SELECT s FROM Song s",
				Song.class).getResultList();
		
		for(Song s: list){
			System.out.println(s.toString());
		}
	}
	
	public void updateSong(Song Song,String name,int length, String fileLocation, String genre, int rating) {
		em.getTransaction().begin();
		String query = "UPDATE Song SET name = \'" + name + "\', length = " + length + ", fileLocation = \'" + fileLocation + "\', genre = \'" + genre + 
				"\', rating = " + rating + " WHERE id = " + Song.getId();
		em.createQuery(query);
		Song.setName(name);
		Song.setLength(length);
		Song.setFileLocation(fileLocation);
		Song.setGenre(genre);
		Song.setRating(rating);
		em.getTransaction().commit();
	}
	
	public void deleteSong(Song Song) {
		em.getTransaction().begin();
		String query = "DELETE FROM Song WHERE id = " + Song.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}
}
