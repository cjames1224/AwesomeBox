package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@Stateless
public class SongArtistService implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;
	
	
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
