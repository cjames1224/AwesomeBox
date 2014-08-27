package com.netbuilder.awesomebox.entityservices;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import com.netbuilder.awesomebox.ValidationException;
import com.netbuilder.awesomebox.entities.Song;

@Named
@Stateless
public class SongService implements Serializable {
	
private static final long serialVersionUID = 544335111396868724L;

@Inject
private EntityManager em;
	
	public void removeSongList(List<Song> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		for(Song a : list){
			em.remove(em.merge(a));
		}
	}

	public void persistSongList(List<Song> list){
		if(list == null){
			throw new ValidationException("Invalid list");
		}

		for(Song a: list){
			em.persist(a);
		}
	}
	
	public List<Song> getSongList() {
		return em.createQuery("SELECT s FROM Song s",
				Song.class).getResultList();
	}
	
	public AudioInputStream getStreamFromSongById(int id){
		AudioInputStream ais = null;
		try{
			ais = AudioSystem.getAudioInputStream(new File(em.find(Song.class, id).getFileLocation()));
			AudioFormat format = ais.getFormat();
			if(format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED){
				format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
						format.getSampleRate(),
						format.getSampleSizeInBits() * 2,
						format.getChannels(),
						format.getFrameSize() * 2,
						format.getFrameRate(),
						true);
				ais = AudioSystem.getAudioInputStream(format, ais);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ais;
	}
	
	public void updateSong(Song song){
		em.persist(em.merge(song));
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
	
//	public List<Song> listSongsByID(int id){
//		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE s.id = \'"+id+"\'",
//				Song.class).getResultList();
//		
//		if(list == null || list.size()==0) {
//			System.out.println("No songs with such name = "+id);
//			return list;
//		}
//		
//		for(Song s: list){
//			System.out.println(s.toString());
//		}
//		
//		return list;
//	}
	
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
	
	public void deleteSong(Song song) {
		if(song == null){
			throw new ValidationException("Invalid song delete");
		}
		em.remove(em.merge(song));
	}

	public List<Song> listSongsByID(int id) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE s.id = \'"+id+"\'",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			System.out.println("No songs with such name = "+id);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
		}
		
		return list;
	}
}
