package com.netbuilder.awesomebox;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Awesome Box!" );
        
        System.out.println("Creating an entity manager.");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("awesomebox");
        
        EntityManager em = emf.createEntityManager();
        
        ArtistService as = new ArtistService(em);
        
        List<Artist> artistList = populateArtistList();
        
        as.persistArtistList(artistList);
        
        as.listArtists();
        

        
        AlbumService als = new AlbumService(em);
        
        List<Album> albumList = populateAlbumList();
        
        als.persistAlbumList(albumList);
        
        //System.out.println("listing songs?");
        als.listAlbums();
        
        SongService ss = new SongService(em);
        
        List<Song> songList = populateSongList();
        
        ss.persistSongList(songList);
        
        //System.out.println("listing songs?");
        ss.listSongs();
        
        UserService us = new UserService(em);
        
        List<User> userList = populateUserList();
        
        us.persistUserList(userList);
        
        us.listUsers();
        
        PlaylistService ps = new PlaylistService(em);
        
        List<Playlist> playList = populatePlaylistList(userList);
        
        ps.persistPlaylistList(playList);
        
        ps.listPlaylists();
        
        SongArtistService sas = new SongArtistService(em);
        
        List<SongArtist> songArtistList = populateSongArtistList(songList, artistList);
        
        sas.persistSongArtistList(songArtistList);
        
        sas.listSongArtists();
        
        // albumartisttest
        AlbumArtistService aas = new AlbumArtistService(em);
        
        List<AlbumArtist> albumArtistList = populateAlbumArtistList(albumList, artistList);
        
        aas.persistAlbumArtistList(albumArtistList);
        
        aas.listAlbumArtists();
        
        // albumsongtest
        AlbumSongService ass = new AlbumSongService(em);
        
        List<AlbumSong> albumSongList = populateAlbumSongList(albumList, songList);
        
        ass.persistAlbumSongList(albumSongList);
        
        ass.listAlbumSongs();
        
     // albumsongtest
        PlaylistSongService pss = new PlaylistSongService(em);
        
        List<PlaylistSong> playlistSongList = populatePlaylistSongList(playList, songList);
        
        pss.persistPlaylistSongList(playlistSongList);
        
        pss.listPlaylistSongs();
        
        //update artist
        
        as.updateArtist(artistList.get(0), "James", 1);
        
        as.listArtists();
        
        as.deleteArtist(artistList.remove(0));
        
        as.listArtists();
        
        pss.updatePlaylistSong(playlistSongList.get(0), songList.get(1));
        
        pss.listPlaylistSongs();
        
        System.out.println(artistList.get(0));
        
        System.out.println(ss.listSongsByName("Theme Song"));
        
        Search search = new Search(em);
        System.out.println("Testsearch artist");
        search.searchSongByArtist( "Lady Gaga");
        System.out.println("Test search album");
        search.searchSongByAlbum(albumList.get(0).getName());
        System.out.println("Test search playlist");
        search.searchSongByPlaylist(playList.get(2).getName());
        System.out.println("Test search playlist and artist");
        search.searchSongByPlaylist(playList.get(0), artistList.get(0));
        System.out.println("Test search album and artist");
        search.searchSongByAlbum(albumList.get(0), artistList.get(0));
        System.out.println("Test search playlist and album");
        search.searchSongByPlaylist(playList.get(0), albumList.get(0));
        System.out.println("Test search greater than rating");
        search.searchSongGreaterRating(2);
        System.out.println("Test search less than rating");
        search.searchSongLesserRating(2);
        System.out.println("Test search year");
        search.searchSongYear(2000);
        System.out.println("Test search Greater year");
        search.searchSongGreaterYear(2000);
        System.out.println("Test search Less than year");
        search.searchSongLesserYear(2000);
        System.out.println("Test search album rating");
        search.searchSongAlbumRating(3);
        System.out.println("Test search artist rating");
        search.searchSongArtistRating(5);
        System.out.println("Test search length");
        search.searchSongLength(600);
        
        pss.addSongToPlaylist(playList.get(0), songList.get(0));
        pss.addSongToPlaylist(playList.get(0), songList.get(1));
        pss.addSongToPlaylist(playList.get(0), songList.get(2));
        pss.addSongToPlaylist(playList.get(0), songList.get(3));
        
        System.out.println("TEST THINGS");
        pss.listPlaylistSongs();
        pss.reorderSong(playList.get(0), songList.get(1), 1);
        pss.listPlaylistSongs();
        
        System.out.println(ss.getSongList().get(0));
        
        //TEST MOVING TRACK NUMBER
        if(em != null){
        	System.out.println("Entity manager created successfully");
        }        
        
        em.close();
        
        System.out.println("Finished");
        
        
    }
    
    private static List<Artist> populateArtistList(){
    	List<Artist> list = new ArrayList<Artist>();
    	
    	list.add(new Artist( "Bon Jovi", 5 ));
    	list.add(new Artist( "MAGIC!", 4 ));
    	list.add(new Artist( "Dragonforce", 4 ));
    	list.add(new Artist( "Green Day", 2 ));
    	list.add(new Artist( "Lady Gaga", 2 ));
    	list.add(new Artist( "Britbrit", 5 ));
    	
    	return list;
    }
    
    private static List<User> populateUserList(){
    	List<User> list = new ArrayList<User>();
    	
    	list.add(new User( "Charleigh", "password", 2, true ));
    	list.add(new User( "Christian", "p", 0, false));
    	list.add(new User( "Dragonforce", "nerd", 0, false ));
    	list.add(new User( "Green Day", "boob", 2, false ));
    	list.add(new User( "Lady Gaga", "brit", 0, false ));
    	list.add(new User( "Britbrit", "gaga", 5, false ));
    	
    	return list;
    }
    
    private static List<Playlist> populatePlaylistList(List<User> userList){
    	List<Playlist> list = new ArrayList<Playlist>();
    	
    	list.add(new Playlist( "Charleigh Playlist",userList.get(0)));
    	list.add(new Playlist( "Christian Playlist",userList.get(1)));
    	list.add(new Playlist( "Dragonforce Playlist",userList.get(2)));
    	list.add(new Playlist( "Green Day Playlist",userList.get(3)));
    	list.add(new Playlist( "Lady Gaga Playlist",userList.get(4)));
    	list.add(new Playlist( "Britbrit Playlist",userList.get(5)));
    	
    	return list;
    }
    
    private static List<Song> populateSongList(){
    	List<Song> list = new ArrayList<Song>();
    	
    	list.add(new Song( "Sweet Caroline", 300, "filelocation", "madeup", 4 ));
    	list.add(new Song( "Poker Face", 300,  "filelocation", "rock", 3));
    	list.add(new Song( "Theme Song", 300, "filelocation", "classical", 2 ));
    	list.add(new Song( "Radar", 300, "filelocation", "pop", 1 ));
    	list.add(new Song( "Hit Me Baby One More Time", 300, "filelocation", "rock", 1 ));
    	list.add(new Song( "Wake Me Up", 300,  "filelocation", "pop", 5 ));
    	list.add(new Song( "Final Countdown", 600,  "filelocation", "classics", 5 ));
    	
    	return list;
    }
    
    private static List<Album> populateAlbumList(){
    	List<Album> list = new ArrayList<Album>();
    	
    	list.add(new Album( "Hit Me Baby One More Time", 2000, "Single", 4 , "madeup"));
    	list.add(new Album( "Moulin Rouge", 2005,  "Compilation", 3, "rock"));
    	list.add(new Album( "Stuff I'm Making Up", 2012, "Regular", 2 , "classical"));
    	list.add(new Album( "Abbey Road", 1800, "Regular", 1, "pop" ));
    	
    	return list;
    }
    
    private static List<SongArtist> populateSongArtistList(List<Song> songList,
    		List<Artist> artistList){
    	
    	List<SongArtist> list = new ArrayList<SongArtist>();
    	
    	list.add(new SongArtist(songList.get(0), artistList.get(0)));
    	list.add(new SongArtist(songList.get(1), artistList.get(0)));
    	list.add(new SongArtist(songList.get(2), artistList.get(1)));
    	list.add(new SongArtist(songList.get(3), artistList.get(2)));
    	list.add(new SongArtist(songList.get(4), artistList.get(3)));
    	list.add(new SongArtist(songList.get(5), artistList.get(4)));
    	list.add(new SongArtist(songList.get(6), artistList.get(5)));

    	
    	return list;
    }
    
    private static List<AlbumArtist> populateAlbumArtistList(List<Album> albumList,
    		List<Artist> artistList){
    	
    	List<AlbumArtist> list = new ArrayList<AlbumArtist>();
    	
    	list.add(new AlbumArtist(albumList.get(0), artistList.get(0)));
    	list.add(new AlbumArtist(albumList.get(1), artistList.get(1)));
    	list.add(new AlbumArtist(albumList.get(2), artistList.get(2)));
    	list.add(new AlbumArtist(albumList.get(3), artistList.get(3)));
    	//list.add(new AlbumArtist(albumList.get(4), artistList.get(4)));
    	
    	return list;
    }
    
    private static List<AlbumSong> populateAlbumSongList(List<Album> albumList,
    		List<Song> songList){
    	
    	List<AlbumSong> list = new ArrayList<AlbumSong>();
    	
    	list.add(new AlbumSong(albumList.get(0), songList.get(0),1));
    	list.add(new AlbumSong(albumList.get(1), songList.get(1),1));
    	list.add(new AlbumSong(albumList.get(2), songList.get(2),1));
    	list.add(new AlbumSong(albumList.get(3), songList.get(3),1));
    	
    	return list;
    }
    
    private static List<PlaylistSong> populatePlaylistSongList(List<Playlist> playList,
    		List<Song> songList){
    	
    	List<PlaylistSong> list = new ArrayList<PlaylistSong>();
    	
    	list.add(new PlaylistSong(playList.get(0), songList.get(0),1));
    	list.add(new PlaylistSong(playList.get(1), songList.get(1),1));
    	list.add(new PlaylistSong(playList.get(2), songList.get(2),1));
    	list.add(new PlaylistSong(playList.get(3), songList.get(3),1));
    	
    	return list;
    }
}
