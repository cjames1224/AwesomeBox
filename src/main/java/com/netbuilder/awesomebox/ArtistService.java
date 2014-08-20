package com.netbuilder.awesomebox;

import java.io.Serializable;
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
public class ArtistService implements Serializable {
	
	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;
	


	

	public List<Artist> getArtistList() {
		List<Artist> list = em.createQuery("SELECT a FROM Artist a",
				Artist.class).getResultList();
		return list;
	}
	
	public void persistArtistList(List<Artist> list){
		if (list == null) {
			throw new ValidationException("Invalid Artist persist");
		}

		
		for(Artist a: list){
			em.persist(a);
		}
		

	}
	
	
	
	public List<Artist> listArtistById(int id) {
		if (id <= 0) {
			throw new ValidationException("Invalid ID");
		}
		
		List<Artist> list = em.createQuery("SELECT a FROM Artist a WHERE id = " + id,
				Artist.class).getResultList();
		
		if (list == null || list.size() == 0) {
			System.out.println("No Results Were Found");
		}
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
		
		return list;
	}
	
	public List<Artist> listArtistByName(String name) {
		if (name == null) {
			throw new ValidationException("Invalid name");
		}
		
		List<Artist> list = em.createQuery("SELECT a FROM Artist a WHERE name = \'" + name + "\'",
				Artist.class).getResultList();
		
		if (list == null || list.size() == 0) {
			System.out.println("No Results Were Found");
		}
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
		
		return list;
	}
	
	public List<Artist> listArtistByRating(int rating) {
		if (rating < 0) {
			throw new ValidationException("Invalid Name");
		}
		
		List<Artist> list = em.createQuery("SELECT a FROM Artist a WHERE rating = " + rating,
				Artist.class).getResultList();
		
		if (list == null || list.size() == 0) {
			System.out.println("No Results Were Found");
		}
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
		
		return list;
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
		
	
		String query = "UPDATE Artist SET name = \'" + name + "\', rating = " + rating + " WHERE id = " + artist.getId();
		em.createQuery(query);
		artist.setName(name);
		artist.setRating(rating);
	
	}
	
	public void deleteArtist(Artist artist) {
		if (artist == null ) {
			throw new ValidationException("Invalid artist delete");
		}
	
		String query = "DELETE FROM Artist WHERE id = " + artist.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
	
	}

}
