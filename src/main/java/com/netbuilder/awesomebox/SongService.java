package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Named
@SessionScoped
public class SongService implements Serializable {
	
private static final long serialVersionUID = 5443351151396868724L;

@Inject
private EntityManager em;
private List<Song> songList;
	
	public SongService(){
		
	}
	
	@PostConstruct
	public void updateSongList() {
		List<Song> list = em.createQuery("SELECT s FROM Song s",
				Song.class).getResultList();
		songList = list;
	}
	
	public void persistSongList(List<Song> list){
		if(list == null){
			throw new ValidationException("Invalid list");
		}
		
		
		em.getTransaction().begin();
		
		for(Song a: list){
			em.persist(a);
		}
		
		em.getTransaction().commit();
		songList = list;
	}
	
	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}


	public List<Song> listSongs(){
		List<Song> list = em.createQuery("SELECT s FROM Song s",
				Song.class).getResultList();
		
		for(Song s: list){
			System.out.println(s.toString());
		}
		this.songList = list;
		return list;
	}
	
	public List<Song> listSongsByName(String name){
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE name = \'"+name+"\'",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			System.out.println("No songs with such name = "+name);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
		}
		
		return list;
	}
	
	
	public List<Song> listSongsByGenre(String genre){
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE genre = \'"+genre+"\'",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			System.out.println("No songs with such genre = "+genre);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
		}
		
		return list;
	}
	
	public List<Song> listSongsByRating(int rating){
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE rating = \'"+rating+"\'",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			System.out.println("No songs with such rating = "+rating);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
		}
		
		return list;
	}
	
	public List<Song> listSongsByPlays(int plays){
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE plays = \'"+plays+"\'",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			System.out.println("No songs with such plays = "+plays);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
		}
		
		return list;
	}
	
	
	public void updateSong(Song song,String name,int length, String fileLocation, String genre, int rating) {
		if(song == null || name == null || fileLocation == null || genre == null){
			throw new ValidationException("Invalid song update");
		}
		
		em.getTransaction().begin();
		String query = "UPDATE Song SET name = \'" + name + "\', length = " + length + ", fileLocation = \'" + fileLocation + "\', genre = \'" + genre + 
				"\', rating = " + rating + " WHERE id = " + song.getId();
		em.createQuery(query);
		song.setName(name);
		song.setLength(length);
		song.setFileLocation(fileLocation);
		song.setGenre(genre);
		song.setRating(rating);
		em.getTransaction().commit();
	}
	
	public void updateSongName(Song song, String name){
		updateSong(song, name, song.getLength(), song.getFileLocation(), song.getGenre(), song.getRating());
	}
	
	public void updateSongLength(Song song, int length){
		updateSong(song, song.getName(), length, song.getFileLocation(), song.getGenre(), song.getRating());
	}
	
	public void updateSongFileLocation(Song song, String fileLocation){
		updateSong(song, song.getName(), song.getLength(), fileLocation, song.getGenre(), song.getRating());
	}
	
	public void updateSongGenre(Song song, String genre){
		updateSong(song, song.getName(), song.getLength(), song.getFileLocation(), genre, song.getRating());
	}
	
	public void updateSongRating(Song song, int rating){
		updateSong(song, song.getName(), song.getLength(), song.getFileLocation(), song.getGenre(), rating);
	}
	
	public void deleteSong(Song song) {
		if(song == null){
			throw new ValidationException("Invalid song delete");
		}
		em.getTransaction().begin();
		String query = "DELETE FROM Song WHERE id = " + song.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}
}
