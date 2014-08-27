package com.netbuilder.awesomebox.entityservices;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.netbuilder.awesomebox.ValidationException;
import com.netbuilder.awesomebox.entities.AlbumArtist;

@Named
@Stateless
public class AlbumArtistService implements Serializable {
	
	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;
	
	
	public List<AlbumArtist> getAlbumArtistList() {
		List<AlbumArtist> list = em.createQuery("SELECT a FROM AlbumArtist a",
				AlbumArtist.class).getResultList();
		
		return list;
	}
	

	public void persistAlbumArtistList(List<AlbumArtist> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
		for(AlbumArtist a: list){
			em.persist(a);
		}	
	}
	
	public void removeAlbumArtistList(List<AlbumArtist> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		for(AlbumArtist a : list){
			em.remove(em.merge(a));
		}
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
