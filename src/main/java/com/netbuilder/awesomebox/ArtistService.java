package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ArtistService {
	
	private EntityManager em;
	
	
	public ArtistService(EntityManager em){
		this.em = em;
	}
	
	public void persistArtistList(List<Artist> list){
		if (list == null) {
			throw new ValidationException("Invalid Artist persist");
		}
		em.getTransaction().begin();
		
		for(Artist a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public void listArtists(){
		List<Artist> list = em.createQuery("SELECT a FROM Artist a",
				Artist.class).getResultList();
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
	}
	
	public void listArtistById(int id) {
		List<Artist> list = em.createQuery("SELECT a FROM Artist a WHERE id = " + id,
				Artist.class).getResultList();
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
	}
	
	public void listArtistByName(String name) {
		List<Artist> list = em.createQuery("SELECT a FROM Artist a WHERE name = " + name,
				Artist.class).getResultList();
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
	}
	
	public void listArtistByRating(int rating) {
		List<Artist> list = em.createQuery("SELECT a FROM Artist a WHERE rating = " + rating,
				Artist.class).getResultList();
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
	}
	
	public void updateArtistName(Artist artist,String name) {
		updateArtist(artist,name,artist.getRating());
	}
	
	public void updateArtistRating(Artist artist,int rating) {
		updateArtist(artist,artist.getName(),rating);
	}
	
	public void updateArtist(Artist artist,String name,int rating) {
		if (artist == null || name == null) {
			throw new ValidationException("Invalid artist update");
		}
		
		em.getTransaction().begin();
		String query = "UPDATE Artist SET name = \'" + name + "\', rating = " + rating + " WHERE id = " + artist.getId();
		em.createQuery(query);
		artist.setName(name);
		artist.setRating(rating);
		em.getTransaction().commit();
	}
	
	public void deleteArtist(Artist artist) {
		if (artist == null ) {
			throw new ValidationException("Invalid artist delete");
		}
		em.getTransaction().begin();
		String query = "DELETE FROM Artist WHERE id = " + artist.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}

}
