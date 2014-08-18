package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SearchV2 {
	
	EntityManager em;
	StringBuilder searchBuilder;

	public SearchV2(EntityManager em) {
		this.em = em;
		this.searchBuilder = null;
	}
	
	public static void main(String[] args){
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("awesomebox");
        
		EntityManager em = emf.createEntityManager();
		 
		SearchV2 s = new SearchV2(em);
		
		s.
		searchSongName("Final Countdown").
		searchSongLength(300).
		search();

	}
	
	public List<Song> search(){
		List<Song> list = em.createQuery(searchBuilder.toString(), Song.class).getResultList();
		if(list == null || list.size() == 0){
			return list;
		}
		
		for(Song s: list){
			System.out.println(s.toString());
		}
		
		searchBuilder = null;
		
		return list;
		
	}
	
	private void initAndConnCheck(){
		if(searchBuilder == null){
			searchBuilder = new StringBuilder();
		}else{
			searchBuilder.append(" intersect ");
		}
	}
	
	public SearchV2 searchSongName(String songName) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE name = \'"+songName+"\'");
		return this;		
	}
	
	public SearchV2 searchSongGenre(String songGenre) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE genre = \'"+songGenre+"\'");
		return this;		
	}
	
	public SearchV2 searchSongRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE rating = \'"+rating+"\'");
		return this;	
	}
	
	
	
	public SearchV2 searchSongByArtist(String artist) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Artist a, SongArtist sa WHERE s.id = sa.song AND a.id = sa.artist AND a.name = '"+artist+"'");
		return this;
	}
	
	public SearchV2 searchSongByAlbum(String album) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.name = '"+album+"'");
		return this;
	}
	
	public SearchV2 searchSongByPlaylist(String playlist) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Playlist p, PlaylistSong ps WHERE s.id = ps.song AND p.id = ps.playlist AND p.name = '"+playlist+"'");
		return this;
	}
	
	public SearchV2 searchSongByPlaylist(Playlist playlist, Artist artist) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Playlist p, PlaylistSong ps  WHERE s.id = ps.song AND p.id = ps.playlist AND p.id = " + playlist.getId() +
				"AND s IN (SELECT s FROM Song s, Artist a, SongArtist sa WHERE s.id = sa.song AND a.id = sa.artist AND a.id = " +artist.getId()+")");
		return this;
	}
	
	public SearchV2 searchSongByAlbum(Album album, Artist artist) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als  WHERE s.id = als.song AND al.id = als.album AND al.id = " + album.getId() +
				"AND s IN (SELECT s FROM Song s, Artist a, SongArtist sa WHERE s.id = sa.song AND a.id = sa.artist AND a.id = " +artist.getId()+")");
		return this;
	}
	
	public SearchV2 searchSongByPlaylist(Playlist playlist, Album album) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Playlist p, PlaylistSong ps  WHERE s.id = ps.song AND p.id = ps.playlist AND p.id = " + playlist.getId() +
				"AND s IN (SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.id = " +album.getId()+")");
		return this;
	}
	
	public SearchV2 searchSongGreaterRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE rating >= " + rating +")");
		return this;
	}
	
	public SearchV2 searchSongLesserRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE rating <= " + rating +")");
		return this;
	}
	
	public SearchV2 searchSongPlays(int plays) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE plays = " + plays +")");
		return this;
	}
	
	public SearchV2 searchSongGreaterPlays(int plays) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE plays >= " + plays +")");
		return this;
	}
	
	public SearchV2 searchSongLesserPlays(int plays) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE plays <= " + plays +")");
		return this;
	}
	
	public SearchV2 searchSongYear(int year) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.year = " + year +")");
		return this;
	}
	
	public SearchV2 searchSongGreaterYear(int year) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.year >= " + year +")");
		return this;
	}
	
	public SearchV2 searchSongLesserYear(int year) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.year <= " + year +")");
		return this;
	}
	
	public SearchV2 searchSongAlbumRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.rating = " + rating +")");
		return this;
	}
	
	public SearchV2 searchSongAlbumGreaterRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.rating >= " + rating +")");
		return this;
	}
	
	public SearchV2 searchSongAlbumLesserRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s.id = als.song AND al.id = als.album AND al.rating <= " + rating +")");
		return this;
	}
	
	public SearchV2 searchSongArtistRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s.id = sar.song AND ar.id = sar.artist AND ar.rating = " + rating +")");
		return this;
	}
	
	public SearchV2 searchSongArtistGreaterRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s.id = sar.song AND ar.id = sar.artist AND ar.rating >= " + rating +")");
		return this;
	}
	
	public SearchV2 searchSongArtistLesserRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s.id = sar.song AND ar.id = sar.artist AND ar.rating <= " + rating +")");
		return this;
	}
	
	public SearchV2 searchSongLength(int length) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE length = " + length +")");
		return this;
	}
	
	public SearchV2 searchSongGreaterLength(int length) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE length >= " + length +")");
		return this;
	}
	
	public SearchV2 searchSongLesserLength(int length) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE length <= " + length +")");
		return this;
	}
}
