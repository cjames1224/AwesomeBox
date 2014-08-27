package com.netbuilder.awesomebox.dbaccess;

import java.util.List;

import javax.persistence.EntityManager;

import com.netbuilder.awesomebox.entities.Album;
import com.netbuilder.awesomebox.entities.Artist;
import com.netbuilder.awesomebox.entities.Playlist;
import com.netbuilder.awesomebox.entities.Song;
import com.netbuilder.awesomebox.entityservices.SongService;

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
	
	public List<Song> searchSongByAlbum(Album album, Artist artist) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Album al, AlbumSong als  WHERE s.id = als.song AND al.id = als.album AND al.id = " + album.getId() +
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
	
	public List<Song> searchSongByPlaylist(Playlist playlist, Album album) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Playlist p, PlaylistSong ps  WHERE s.id = ps.song AND p.id = ps.playlist AND p.id = " + playlist.getId() +
				"AND s IN (SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.id = " +album.getId()+")",
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
	
	public List<Song> searchSongGreaterRating(int rating) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE rating >= " + rating +")",
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
	
	public List<Song> searchSongLesserRating(int rating) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE rating <= " + rating +")",
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
	
	public List<Song> searchSongPlays(int plays) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE plays = " + plays +")",
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
	
	public List<Song> searchSongGreaterPlays(int plays) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE plays >= " + plays +")",
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
	
	public List<Song> searchSongLesserPlays(int plays) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE plays <= " + plays +")",
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
	
	public List<Song> searchSongYear(int year) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.year = " + year +")",
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
	
	public List<Song> searchSongGreaterYear(int year) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.year >= " + year +")",
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
	
	public List<Song> searchSongLesserYear(int year) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.year <= " + year +")",
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
	
	public List<Song> searchSongAlbumRating(int rating) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.rating = " + rating +")",
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
	
	public List<Song> searchSongAlbumGreaterRating(int rating) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.rating >= " + rating +")",
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
	
	public List<Song> searchSongAlbumLesserRating(int rating) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.rating <= " + rating +")",
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
	
	public List<Song> searchSongArtistRating(int rating) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s.id = sar.song AND ar.id = sar.artist AND ar.rating = " + rating +")",
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
	
	public List<Song> searchSongArtistGreaterRating(int rating) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s.id = sar.song AND ar.id = sar.artist AND ar.rating >= " + rating +")",
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
	
	public List<Song> searchSongArtistLesserRating(int rating) {
		List<Song> list = em.createQuery("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s.id = sar.song AND ar.id = sar.artist AND ar.rating <= " + rating +")",
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
	
	public List<Song> searchSongLength(int length) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE length = " + length +")",
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
	
	public List<Song> searchSongGreaterLength(int length) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE length >= " + length +")",
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
	
	public List<Song> searchSongLesserLength(int length) {
		List<Song> list = em.createQuery("SELECT s FROM Song s WHERE length <= " + length +")",
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
