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
        
        List<Artist> artistList = populateCategoryList();
        
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
        
        if(em != null){
        	System.out.println("Entity manager created successfully");
        }        
        
        em.close();
        
        System.out.println("Finished");
    }
    
    private static List<Artist> populateCategoryList(){
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
}
