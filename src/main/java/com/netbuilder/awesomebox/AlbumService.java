package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Named
@SessionScoped
public class AlbumService implements Serializable {
	
	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;
	private List<Album> albumList;
	
	public AlbumService(){
		
	}
	
	@PostConstruct
	public void updateAlbumList() {
		List<Album> list = em.createQuery("SELECT a FROM Album a",
				Album.class).getResultList();
		this.albumList = list;
	}
	
	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}

	public void persistAlbumList(List<Album> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
	
		
		for(Album a: list){
			em.persist(a);
		}
		
	
	}
	
	public void listAlbums(){
		List<Album> list = em.createQuery("SELECT a FROM Album a",
				Album.class).getResultList();
		
		for(Album a: list){
			System.out.println(a.toString());
		}
	}
	
	public void updateAlbumName(Album album,String name) {
		updateAlbum(album,name,album.getRating(),album.getYear(),album.getGenre(),album.getType());
	}
	
	public void updateAlbumRating(Album album,int rating) {
		updateAlbum(album,album.getName(),rating,album.getYear(),album.getGenre(),album.getType());
	}
	
	public void updateAlbumYear(Album album,int year) {
		updateAlbum(album,album.getName(),album.getRating(),year,album.getGenre(),album.getType());
	}
	
	public void updateAlbumGenre(Album album,String genre) {
		updateAlbum(album,album.getName(),album.getRating(),album.getYear(),genre,album.getType());
	}
	
	public void updateAlbumType(Album album,String type) {
		updateAlbum(album,album.getName(),album.getRating(),album.getYear(),album.getGenre(),type);
	}

	public void updateAlbum(Album album,String name,int rating,int year,String genre,String type) {
		if (album == null || name == null || type == null) {
			throw new ValidationException("Invalid Album Update");
		}
		

		String query = "UPDATE Album SET name = \'" + name + "\', rating = " + rating + ", year = " + year + ", genre = \'" + genre + "\', type = " + album.getType() +" WHERE id = " + album.getId();
		em.createQuery(query);
		album.setName(name);
		album.setRating(rating);
		album.setYear(year);
		album.setGenre(genre);
		album.setType(type);
	
	}
	
	public void deleteAlbum(Album album) {
		if (album == null) {
			throw new ValidationException("Invalid Album");
		}
		

		String query = "DELETE FROM Album WHERE id = " + album.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();

	}
}
