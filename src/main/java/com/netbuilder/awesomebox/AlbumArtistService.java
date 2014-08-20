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
public class AlbumArtistService implements Serializable {
	
	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;
	private List<AlbumArtist> albumArtistList;
	
	public AlbumArtistService(){
		
	}
	
	@PostConstruct
	public void updateAlbumArtistList() {
		List<AlbumArtist> list = em.createQuery("SELECT a FROM AlbumArtist a",
				AlbumArtist.class).getResultList();
		this.albumArtistList = list;
	}
	
	public List<AlbumArtist> getAlbumArtistList() {
		return albumArtistList;
	}

	public void setAlbumArtistList(List<AlbumArtist> albumArtistList) {
		this.albumArtistList = albumArtistList;
	}

	public void persistAlbumArtistList(List<AlbumArtist> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
		em.getTransaction().begin();
		
		for(AlbumArtist a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public List<AlbumArtist> listAlbumArtists(){
		List<AlbumArtist> list = em.createQuery("SELECT a FROM AlbumArtist a",
				AlbumArtist.class).getResultList();
		
		for(AlbumArtist a: list){
			System.out.println(a.toString());
		}
		
		return list;
	}
	
//	public void updateAlbumArtist(AlbumArtist albumArtist,Album album,Artist artist) {
//		em.getTransaction().begin();
//		String query = "UPDATE AlbumArtist SET album = \'" + album + "\', artist = " + artist + " WHERE id = " + albumArtist.getId();
//		em.createQuery(query);
//		albumArtist.setArtist(artist);
//		albumArtist.setAlbum(album);
//		em.getTransaction().commit();
//	}
	
//	public void deleteAlbumArtist(AlbumArtist albumArtist) {
//		em.getTransaction().begin();
//		String query = "DELETE FROM AlbumArtist WHERE id = " + albumArtist.getId();
//		Query q = em.createQuery(query);
//		q.executeUpdate();
//		em.getTransaction().commit();
//	}

}
