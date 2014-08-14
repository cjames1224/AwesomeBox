package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AlbumService {
	
	private EntityManager em;
	
	public AlbumService(EntityManager em){
		this.em = em;
	}
	
	public void persistAlbumList(List<Album> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
		em.getTransaction().begin();
		
		for(Album a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listAlbums(){
		List<Album> list = em.createQuery("SELECT a FROM Album a",
				Album.class).getResultList();
		
		for(Album a: list){
			System.out.println(a.toString());
		}
	}

	public void updateAlbum(Album album,String name,int rating,int year,String genre,String type) {
		if (album == null || name == null || type == null) {
			throw new ValidationException("Invalid Album Update");
		}
		
		em.getTransaction().begin();
		String query = "UPDATE Album SET name = \'" + name + "\', rating = " + rating + ", year = " + year + ", genre = \'" + genre + "\', type = " + album.getType() +" WHERE id = " + album.getID();
		em.createQuery(query);
		album.setName(name);
		album.setRating(rating);
		album.setYear(year);
		album.setGenre(genre);
		album.setType(type);
		em.getTransaction().commit();
	}
	
	public void deleteAlbum(Album album) {
		if (album == null) {
			throw new ValidationException("Invalid Album");
		}
		
		em.getTransaction().begin();
		String query = "DELETE FROM Album WHERE id = " + album.getID();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}
}
