package com.netbuilder.awesomebox;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Named
@SessionScoped
public class ArtistService {
	
	@Inject
	private EntityManager em;
	private List<Artist> artistList;
	
	
	public List<Artist> getArtistList() {
		return artistList;
	}

	public void setArtistList(List<Artist> artistList) {
		this.artistList = artistList;
	}

	public ArtistService(){
		
	}
	
	@PostConstruct
	public void updateArtistList() {
		List<Artist> list = em.createQuery("SELECT a FROM Artist a",
				Artist.class).getResultList();
		this.artistList = list;
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
	
	public List<Artist> listArtists(){
		List<Artist> list = em.createQuery("SELECT a FROM Artist a",
				Artist.class).getResultList();
		
		if (list == null || list.size() == 0) {
			System.out.println("No Results Were Found");
		}
		
		for(Artist a: list){
			System.out.println(a.toString());
		}
		
		return list;
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
