package com.netbuilder.awesomebox.entityservices;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.netbuilder.awesomebox.ValidationException;
import com.netbuilder.awesomebox.entities.SongArtist;

@Named
@Stateless
public class SongArtistService implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;
	
	
	public void removeSongArtistList(List<SongArtist> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		for(SongArtist a : list){
			em.remove(em.merge(a));
		}
	}
	
	public List<SongArtist> getSongArtistList() {
		return em.createQuery("SELECT a FROM SongArtist a",
				SongArtist.class).getResultList();
	}

	public void persistSongArtistList(List<SongArtist> list){
		if(list == null){
			throw new ValidationException("Invalid SongArtist Update");
		}
		
		for(SongArtist sa: list){
			em.persist(sa);
		}
		
	}
}
