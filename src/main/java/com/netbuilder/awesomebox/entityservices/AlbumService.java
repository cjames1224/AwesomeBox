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
import com.netbuilder.awesomebox.entities.Album;

@Named
@Stateless
public class AlbumService implements Serializable {
	
	@Inject
	private EntityManager em;
	
	public List<Album> getAlbumList() {
		return em.createQuery("SELECT a FROM Album a",
				Album.class).getResultList();
	}

	public void persistAlbumList(List<Album> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
		for(Album a: list){
			em.persist(a);
		}
	}
	
	public void updateAlbum(Album album){
		em.persist(em.merge(album));
	}
	
	public void removeAlbumList(List<Album> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		for(Album a : list){
			em.remove(em.merge(a));
		}
	}
	
	public void deleteAlbum(Album album) {
		if (album == null) {
			throw new ValidationException("Invalid Album");
		}
		em.remove(em.merge(album));

	}
}
