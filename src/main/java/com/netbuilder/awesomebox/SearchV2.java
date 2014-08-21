package com.netbuilder.awesomebox;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
@Stateless
public class SearchV2 {
	
	@Inject
	private EntityManager em;
	private StringBuilder searchBuilder = null;
	private int endParens = 0;

	
//	public static void main(String[] args){
//		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("awesomebox");
//        
//		EntityManager em = emf.createEntityManager();
//		 
//		SearchV2 s = new SearchV2(em);
//		
//		s.searchSongLesserRating(3).searchSongGenre("rock").search();
//		
//	}
	
	public StringBuilder getStringBuilder(){
		return searchBuilder;
	}
	
	public List<Song> search(){
		for(int i = 0; i < endParens; i++)
			searchBuilder.append(" ) ");
		List<Song> list = em.createQuery(searchBuilder.toString(), Song.class).getResultList();
		if(list == null || list.size() == 0){
			searchBuilder = null;
			return list;
		}
		
		//System.out.println(searchBuilder.toString());
		
//		for(Song s: list){
//			System.out.println(s.toString());
//		}
//		
		searchBuilder = null;
		
		return list;
		
	}
	
	private void initAndConnCheck(){
		if(searchBuilder == null){
			searchBuilder = new StringBuilder();
		}else{
			searchBuilder.append(" And s in ( ");
			endParens ++;
		}
	}
	
//	
//	public List<Song> generalSearch(String searchTerm) {
//		List<Song> list = this.searchSongByAlbum(searchTerm).search();
//		list.addAll(this.searchSongByArtist(searchTerm).search());
//		list.addAll(this.searchSongName(searchTerm).search());
//		return list;
//	}
	
	public SearchV2 searchSongName(String songName) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.name = \'"+songName+"\' ");
		return this;		
	}
	
	public List<Album> searchAlbumName(int id) {
		
		List<Album> list = em.createQuery("SELECT al FROM Song s, AlbumSong als, Album al WHERE s = als.song AND al = als.album AND s.id = "+id+" ",Album.class).getResultList();
		return list;		
	}
	
	public List<Artist> searchArtistName(int id) {
	
		List<Artist> list = em.createQuery("SELECT a FROM Song s, SongArtist sa, Artist a WHERE s = sa.song AND a = sa.artist AND s.id = "+id+" ",Artist.class).getResultList();
		return list;		
	}
	
	public SearchV2 searchSongGenre(String songGenre) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.genre = \'"+songGenre+"\'");
		return this;		
	}
	
	public SearchV2 searchSongRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.rating = \'"+rating+"\'");
		return this;	
	}
	
	
	
	public SearchV2 searchSongByArtist(String artist) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Artist a, SongArtist sa WHERE s = sa.song AND a = sa.artist AND a.name = '"+artist+"'");
		return this;
	}
	
	public SearchV2 searchSongByAlbum(String album) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s = als.song AND al = als.album AND al.name = '"+album+"'");
		return this;
	}
	
	public SearchV2 searchSongByPlaylist(String playlist) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Playlist p, PlaylistSong ps WHERE s = ps.song AND p = ps.playlist AND p.name = '"+playlist+"'");
		return this;
	}
	
	public SearchV2 searchSongByPlaylist(Playlist playlist, Artist artist) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Playlist p, PlaylistSong ps  WHERE s = ps.song AND p = ps.playlist AND p.id = " + playlist.getId() +
				"AND s IN (SELECT s FROM Song s, Artist a, SongArtist sa WHERE s = sa.song AND a = sa.artist AND a.id = " +artist.getId()+"");
		return this;
	}
	
	public SearchV2 searchSongByAlbum(Album album, Artist artist) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als  WHERE s = als.song AND al = als.album AND al.id = " + album.getId() +
				"AND s IN (SELECT s FROM Song s, Artist a, SongArtist sa WHERE s = sa.song AND a = sa.artist AND a.id = " +artist.getId()+"");
		return this;
	}
	
	public SearchV2 searchSongByPlaylist(Playlist playlist, Album album) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Playlist p, PlaylistSong ps  WHERE s = ps.song AND p = ps.playlist AND p.id = " + playlist.getId() +
				"AND s IN (SELECT s FROM Song s, Album al, AlbumSong als WHERE s = als.song AND al = als.album AND al.id = " +album.getId()+"");
		return this;
	}
	
	public SearchV2 searchSongGreaterRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.rating >= " + rating );
		return this;
	}
	
	public SearchV2 searchSongLesserRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.rating <= " + rating);
		return this;
	}
	
	public SearchV2 searchSongPlays(int plays) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.plays = " + plays);
		return this;
	}
	
	public SearchV2 searchSongGreaterPlays(int plays) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.plays >= " + plays);
		return this;
	}
	
	public SearchV2 searchSongLesserPlays(int plays) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.plays <= " + plays);
		return this;
	}
	
	public SearchV2 searchSongYear(int year) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s = als.song AND al = als.album AND al.year = " + year);
		return this;
	}
	
	public SearchV2 searchSongGreaterYear(int year) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s = als.song AND al = als.album AND al.year >= " + year);
		return this;
	}
	
	public SearchV2 searchSongLesserYear(int year) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s = als.song AND al = als.album AND al.year <= " + year );
		return this;
	}
	
	public SearchV2 searchSongAlbumRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s = als.song AND al = als.album AND al.rating = " + rating );
		return this;
	}
	
	public SearchV2 searchSongAlbumGreaterRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s = als.song AND al = als.album AND al.rating >= " + rating );
		return this;
	}
	
	public SearchV2 searchSongAlbumLesserRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Album al, AlbumSong als WHERE s = als.song AND al = als.album AND al.rating <= " + rating);
		return this;
	}
	
	public SearchV2 searchSongArtistRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s = sar.song AND ar = sar.artist AND ar.rating = " + rating );
		return this;
	}
	
	public SearchV2 searchSongArtistGreaterRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s = sar.song AND ar = sar.artist AND ar.rating >= " + rating );
		return this;
	}
	
	public SearchV2 searchSongArtistLesserRating(int rating) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s, Artist ar, SongArtist sar WHERE s = sar.song AND ar = sar.artist AND ar.rating <= " + rating);
		return this;
	}
	
	public SearchV2 searchSongLength(int length) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.length = " + length);
		return this;
	}
	
	public SearchV2 searchSongGreaterLength(int length) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.length >= " + length );
		return this;
	}
	
	public SearchV2 searchSongLesserLength(int length) {
		initAndConnCheck();
		searchBuilder.append("SELECT s FROM Song s WHERE s.length <= " + length );
		return this;
	}
}
