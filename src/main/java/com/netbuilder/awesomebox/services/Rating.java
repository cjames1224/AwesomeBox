package com.netbuilder.awesomebox.services;

import javax.persistence.EntityManager;

import com.netbuilder.awesomebox.ValidationException;
import com.netbuilder.awesomebox.entities.Album;
import com.netbuilder.awesomebox.entities.Artist;
import com.netbuilder.awesomebox.entities.Song;


/**
 * let's you rate stuff
 * @author chris
 *
 */
public class Rating {
	
	private EntityManager em;
	
	public Rating(EntityManager em) {
		this.em = em;
	}
	
	public void rate(Song song, int rating){
		if(song == null || rating < 1 || rating > 5){
			throw new ValidationException("Invalid rating");
		}
		em.getTransaction().begin();
		
		int newRating = (int)( ( (song.getRating() * 3) + rating ) / 4) ;
		
		song.setRating(newRating);
		
		String query = "UPDATE Song SET rating = " + newRating + " WHERE id = " + song.getId();
		em.createQuery(query);
		
		em.getTransaction().commit();
	}
	
	public void rate(Artist artist, int rating){
		if(artist == null || rating < 1 || rating > 5){
			throw new ValidationException("Invalid rating");
		}
		em.getTransaction().begin();
		
		int newRating = (int)( ( (artist.getRating() * 3) + rating ) / 4) ;
		
		artist.setRating(newRating);
		
		String query = "UPDATE Artist SET rating = " + newRating + " WHERE id = " + artist.getId();
		em.createQuery(query);
		
		em.getTransaction().commit();
		
	}
	
	public void rate(Album album, int rating){
		if(album == null || rating < 1 || rating > 5){
			throw new ValidationException("Invalid rating");
		}
		em.getTransaction().begin();

		int newRating = (int)( ( (album.getRating() * 3) + rating ) / 4) ;
		
		album.setRating(newRating);
		
		String query = "UPDATE Album SET rating = " + newRating + " WHERE id = " + album.getId();
		em.createQuery(query);
		
		em.getTransaction().commit();
		
	}

}
