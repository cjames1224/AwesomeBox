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
		if(list == null){
			throw new ValidationException("Invalid list");
		}
		
		
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
	
	public void updateSong(Song song,String name,int length, String fileLocation, String genre, int rating) {
		if(song == null || name == null || fileLocation == null || genre == null){
			throw new ValidationException("Invalid song update");
		}
		
		em.getTransaction().begin();
		String query = "UPDATE Song SET name = \'" + name + "\', length = " + length + ", fileLocation = \'" + fileLocation + "\', genre = \'" + genre + 
				"\', rating = " + rating + " WHERE id = " + song.getId();
		em.createQuery(query);
		song.setName(name);
		song.setLength(length);
		song.setFileLocation(fileLocation);
		song.setGenre(genre);
		song.setRating(rating);
		em.getTransaction().commit();
	}
	
	public void deleteSong(Song song) {
		if(song == null){
			throw new ValidationException("Invalid song delete");
		}
		em.getTransaction().begin();
		String query = "DELETE FROM Song WHERE id = " + song.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}
}
