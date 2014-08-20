package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@SessionScoped
public class SongArtistService implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
private EntityManager em;
	private List<SongArtist> songArtistList;
	
	
	public List<SongArtist> getSongArtistList() {
		return songArtistList;
	}

	public void setSongArtistList(List<SongArtist> songArtistList) {
		this.songArtistList = songArtistList;
	}

	public SongArtistService(){
		
	}
	
	@PostConstruct
	public void updateSongArtistList() {
		List<SongArtist> list = em.createQuery("SELECT sa FROM SongArtist sa",
				SongArtist.class).getResultList();
		this.songArtistList = list;
	}
	
	public void persistSongArtistList(List<SongArtist> list){
		if(list == null){
			throw new ValidationException("Invalid SongArtist Update");
		}
		em.getTransaction().begin();
		
		for(SongArtist sa: list){
			em.persist(sa);
		}
		
		em.getTransaction().commit();
	}
	
	public List<SongArtist> listSongArtists(){
		List<SongArtist> list = em.createQuery("SELECT sa FROM SongArtist sa",
				SongArtist.class).getResultList();
		
		for(SongArtist s: list){
			System.out.println(s.toString());
		}
		
		return list;
		
	}
	
}
