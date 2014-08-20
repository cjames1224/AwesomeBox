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
public class AlbumSongService {

@Inject
private EntityManager em;
private List<AlbumSong> albumSongList;
	
	
	public List<AlbumSong> getAlbumSongList() {
	return albumSongList;
}

public void setAlbumSongList(List<AlbumSong> albumSongList) {
	this.albumSongList = albumSongList;
}

	public AlbumSongService(){
		
	}
	
	@PostConstruct
	public void updateAlbumSongList() {
		List<AlbumSong> list = em.createQuery("SELECT a FROM AlbumSong a",
				AlbumSong.class).getResultList();
		this.albumSongList = list;
	}
	
	public void persistAlbumSongList(List<AlbumSong> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		
		em.getTransaction().begin();
		
		for(AlbumSong a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
	}
	
	public List<AlbumSong> listAlbumSongs(){
		List<AlbumSong> list = em.createQuery("SELECT a FROM AlbumSong a",
				AlbumSong.class).getResultList();
		
		for(AlbumSong a: list){
			System.out.println(a.toString());
		}
		
		return list;
	}
	
//	public void updateAlbumSong(AlbumSong albumSong,Album album,Song song,int trackNumber) {
//		em.getTransaction().begin();
//		String query = "UPDATE AlbumSong SET album = \'" + album + "\', song = " + song + " WHERE id = " + albumSong.getId();
//		em.createQuery(query);
//		albumSong.setAlbum(album);
//		albumSong.setSong(song);
//		albumSong.setTrackNumber(trackNumber);
//		em.getTransaction().commit();
//	}
//	
//	public void deleteAlbumSong(AlbumSong albumSong) {
//		em.getTransaction().begin();
//		String query = "DELETE FROM AlbumSong WHERE id = " + albumSong.getId();
//		Query q = em.createQuery(query);
//		q.executeUpdate();
//		em.getTransaction().commit();
//	}
}
