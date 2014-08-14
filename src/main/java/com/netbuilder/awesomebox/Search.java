package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

public class Search {
	
	EntityManager em;
	
//	public List<Song> searchAll(String searchTerm) {
//		
//	}
	public Search(EntityManager em) {
		this.em = em;
	}
	
	public List<Song> searchSongName(SongService ss, String songName) {
		return ss.listSongsByName(songName);			
	}
	
	public List<Song> searchSongGenre(SongService ss, String songGenre) {
		return ss.listSongsByGenre(songGenre);		
	}
	
	public List<Song> searchSongRating(SongService ss, int rating) {
		return ss.listSongsByRating(rating);		
	}
	
	public List<Song> searchSongByArtist(String artist) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Artist a, SongArtist sa WHERE s.id = sa.song AND a.id = sa.artist AND a.name = '"+artist+"'",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			//System.out.println("No songs with such plays = "+plays);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
			//System.out.print(b);
		}
		
		return list;
	}
	
	public List<Song> searchSongByAlbum(String album) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.name = '"+album+"'",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			//System.out.println("No songs with such plays = "+plays);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
			//System.out.print(b);
		}
		
		return list;
	}
	
	public List<Song> searchSongByPlaylist(String playlist) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Playlist p, PlaylistSong ps WHERE s.id = ps.song AND p.id = ps.playlist AND p.name = '"+playlist+"'",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			//System.out.println("No songs with such plays = "+plays);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
			//System.out.print(b);
		}
		
		return list;
	}
	
	public List<Song> searchSongByPlaylist(Playlist playlist, Artist artist) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Playlist p, PlaylistSong ps  WHERE s.id = ps.song AND p.id = ps.playlist AND p.id = " + playlist.getId() +
				"AND s IN (SELECT s FROM Song s, Artist a, SongArtist sa WHERE s.id = sa.song AND a.id = sa.artist AND a.id = " +artist.getId()+")",
				Song.class).getResultList();
		
		if(list == null || list.size()==0) {
			//System.out.println("No songs with such plays = "+plays);
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
			//System.out.print(b);
		}
		
		return list;
	}
	
}
