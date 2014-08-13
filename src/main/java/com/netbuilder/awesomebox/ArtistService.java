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
	
	public void updateArtist(Artist artist,String name,int rating) {
		em.getTransaction().begin();
		String query = "UPDATE Artist SET name = \'" + name + "\', rating = " + rating + " WHERE id = " + artist.getId();
		em.createQuery(query);
		artist.setName(name);
		artist.setRating(rating);
		em.getTransaction().commit();
	}
	
	public void deleteArtist(Artist artist) {
		em.getTransaction().begin();
		String query = "DELETE FROM Artist WHERE id = " + artist.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}

}
