package com.netbuilder.awesomebox;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBPopulator {

	private EntityManager em;
	private ArrayList<User> users;
	private ArrayList<Artist> artists;
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlists;
	private ArrayList<Song> songs;

	public static void main(String[] args) {
		new DBPopulator().populateDB();
	}

	public void populateDB() {
		em = Persistence.createEntityManagerFactory("awesomebox")
				.createEntityManager();

		//to clear the db, uncomment below
		clearDB();

		initAlbums();
		initArtists();
		initSongs();
		initUsers();
		initPlaylists();
		initAlbumArtists();
		initAlbumSongs();
		initPlaylistSongs();
		initSongArtists();
		
	}

	public void clearDB() {
		em.getTransaction().begin();
		
		String[] queries = new String[]{
				"SET FOREIGN_KEY_CHECKS=0", "TRUNCATE TABLE Album_Song",   "TRUNCATE TABLE Playlist_Song","TRUNCATE TABLE Album_Artist", "TRUNCATE TABLE Song_Artist", 
				"TRUNCATE TABLE Album","TRUNCATE TABLE Artist","TRUNCATE TABLE Song","TRUNCATE TABLE Playlist",  "TRUNCATE TABLE User", "SET FOREIGN_KEY_CHECKS=0"
		};
		for(String s: queries)
			em.createNativeQuery(s).executeUpdate();
	
		em.getTransaction().commit();
	}
	
	private void initArtists() {
		artists = new ArrayList<Artist>();
		artists.add(new Artist("Britney Spears", 5));
		artists.add(new Artist("Bon Jovi", 5));
		artists.add(new Artist("Dragonforce", 4));
		artists.add(new Artist("Kobe Bryant", 5));
		artists.add(new Artist("Green Day", 3));
		artists.add(new Artist("Christophe Beck", 2));
		artists.add(new Artist("Demi Lovato", 1));
		artists.add(new Artist("Idina Menzel", 4));
		artists.add(new Artist("Kristin Bell", 3));
		artists.add(new Artist("Damien Carter", 5));

		artists.add(new Artist("Lil Wayne", 1));
		new ArtistService(em).persistArtistList(artists);
	}
	
	private void initSongArtists() {
		List<SongArtist> sa = new ArrayList<SongArtist>();
		sa.add(new SongArtist(songs.get(0), artists.get(0)));
		sa.add(new SongArtist(songs.get(1), artists.get(0)));
		sa.add(new SongArtist(songs.get(2), artists.get(0)));
		sa.add(new SongArtist(songs.get(3), artists.get(2)));
		sa.add(new SongArtist(songs.get(4), artists.get(2)));
		sa.add(new SongArtist(songs.get(5), artists.get(2)));
		sa.add(new SongArtist(songs.get(6), artists.get(4)));
		sa.add(new SongArtist(songs.get(7), artists.get(4)));
		sa.add(new SongArtist(songs.get(8), artists.get(4)));
		sa.add(new SongArtist(songs.get(9), artists.get(4)));
		sa.add(new SongArtist(songs.get(10), artists.get(3)));
		sa.add(new SongArtist(songs.get(11), artists.get(10)));
		sa.add(new SongArtist(songs.get(12), artists.get(8)));
		sa.add(new SongArtist(songs.get(13), artists.get(7)));
		sa.add(new SongArtist(songs.get(14), artists.get(6)));
		sa.add(new SongArtist(songs.get(15), artists.get(5)));
		sa.add(new SongArtist(songs.get(16), artists.get(1)));
		sa.add(new SongArtist(songs.get(17), artists.get(1)));
		sa.add(new SongArtist(songs.get(18), artists.get(9)));
		new SongArtistService(em).persistSongArtistList(sa);

	}

	private void initSongs() {
		songs = new ArrayList<Song>();
		songs.add(new Song("I'm a Slave 4 U", 204,
				"file/location/I'maASlave4U.wav", "Pop", 2));
		songs.add(new Song("Oops!... I Did it Again", 211,
				"file/location/OppsDidItAgain.wav", "Pop", 5));
		songs.add(new Song("Don't Let Me Be the Last to Know", 230,
				"file/locaiton/DontLetMeBeTheLast.wav", "Pop", 3));
		songs.add(new Song("Through the Fire and Flames", 441,
				"file/location/Throughthefireandflames.wav", "Power Metal", 5));
		songs.add(new Song("The Flame of Youth", 401,
				"file/location/Theflameofyouth.wav", "Power Metal", 1));
		songs.add(new Song("Ring of Fire", 195, "file/location/ringoffire.wav",
				"Power Metal", 4));
		songs.add(new Song("21st Century Breakdown", 309,
				"file/location/21stcenture.wav", "Punk Rock", 3));
		songs.add(new Song("American Idiot", 174,
				"file/location/americanidiot.wav", "Punk Rock", 5));
		songs.add(new Song("Wake Me Up When September Ends", 285,
				"file/location/wakemeup.wav", "Punk Rock", 4));
		songs.add(new Song("Boulevard of Broken Dreams", 265,
				"file/location/boulevardofbrokendreams.wav", "Punk Rock", 5));
		songs.add(new Song("K.O.B.E.", 239, "file/location/kobe.wav",
				"Hip-hop", 5));
		songs.add(new Song("Kobe Bryant", 305, "file/location/kobebryant.wav",
				"Rap", 1));
		songs.add(new Song("Do You Want To Build A Snowman", 207,
				"file/loc/doyouwanta.wav", "Musical", 2));
		songs.add(new Song("Let It Go", 224, "file/loc/letitgo.wav", "Musical",
				5));
		songs.add(new Song("Let It Go", 226, "file/location/letitgov2.wav",
				"Musical", 3));
		songs.add(new Song("The Trolls", 102, "file/loc/thetrolls.wav",
				"Musical", 1));
		songs.add(new Song("It's My Life", 224, "file/loc/itsmylife.wav",
				"Hard Rock", 5));
		songs.add(new Song("One Wild Night", 283, "file/loc/oneWildnight.wav",
				"Hard Rock", 5));
		songs.add(new Song("Just Hanging Out (With My Family)", 345,
				"file/loc/birdemic.wav", "Movie", 5));
		new SongService(em).persistSongList(songs);
	}

	private void initAlbums() {
		albums = new ArrayList<Album>();
		albums.add(new Album("Britney", 2001, "Regular", 4, "Pop"));
		albums.add(new Album("Oops! ... I Did it Again", 2000, "Regular", 5,
				"Pop"));
		albums.add(new Album("Inhuman Rampage", 2006, "Regular", 5,
				"Power Metal"));
		albums.add(new Album("Maximum Overload", 2014, "Regular", 2,
				"Power Metal"));
		albums.add(new Album("American Idiot", 2004, "Regular", 4, "Punk Rock"));
		albums.add(new Album("21st Century Breakdown ", 2009, "Regular", 1,
				"Punk Rock"));
		albums.add(new Album("K.O.B.E.", 2000, "Single", 5, "Hip-Hop"));
		albums.add(new Album("Kobe Bryant", 2009, "Single", 1, "Rap"));
		albums.add(new Album("Frozen Original Soundtrack", 2013, "Compilation",
				3, "Musical"));
		albums.add(new Album("Crush", 2000, "Regular", 5, "Hard Rock"));
		albums.add(new Album("Birdemic The SoundTrack", 2000, "Compilation", 3,
				"Movie"));
		new AlbumService(em).persistAlbumList(albums);

	}
	
	private void initUsers() {
		users = new ArrayList<User>();
		users.add(new User("Christian", "P4ssword"));
		users.add(new User("Charleigh", "P4ssword"));
		users.add(new User("David", "P4ssword"));
		users.add(new User("Daniel", "P4ssword"));
		new UserService(em).persistUserList(users);
	}
	
	private void initPlaylistSongs() {
		List<PlaylistSong> ps = new ArrayList<PlaylistSong>();
		ps.add(new PlaylistSong(playlists.get(1), songs.get(0),1));
		ps.add(new PlaylistSong(playlists.get(1), songs.get(1),2));
		ps.add(new PlaylistSong(playlists.get(1), songs.get(13),3));

		ps.add(new PlaylistSong(playlists.get(0), songs.get(18),1));
		ps.add(new PlaylistSong(playlists.get(0), songs.get(16),2));
		ps.add(new PlaylistSong(playlists.get(0), songs.get(8),3));

		ps.add(new PlaylistSong(playlists.get(2), songs.get(10),1));
		ps.add(new PlaylistSong(playlists.get(2), songs.get(18),2));
		ps.add(new PlaylistSong(playlists.get(2), songs.get(7),3));

		ps.add(new PlaylistSong(playlists.get(3), songs.get(8),1));
		ps.add(new PlaylistSong(playlists.get(3), songs.get(9),2));
		ps.add(new PlaylistSong(playlists.get(3), songs.get(3),3));
		new PlaylistSongService(em).persistPlaylistSongList(ps);

	}


	private void initPlaylists() {
		playlists = new ArrayList<Playlist>();
		playlists.add(new Playlist("Charleigh's Playlist", users.get(0)));
		playlists.add(new Playlist("Christian's Playlist", users.get(1)));
		playlists.add(new Playlist("David's Playlist", users.get(2)));
		playlists.add(new Playlist("Daniel's Playlist", users.get(3)));
		new PlaylistService(em).persistPlaylistList(playlists);
	}

	private void initAlbumArtists() {
		List<AlbumArtist> aa = new ArrayList<AlbumArtist>();
		aa.add(new AlbumArtist(albums.get(0), artists.get(0)));
		aa.add(new AlbumArtist(albums.get(1), artists.get(0)));
		aa.add(new AlbumArtist(albums.get(2), artists.get(2)));
		aa.add(new AlbumArtist(albums.get(3), artists.get(2)));
		aa.add(new AlbumArtist(albums.get(4), artists.get(4)));
		aa.add(new AlbumArtist(albums.get(5), artists.get(4)));
		aa.add(new AlbumArtist(albums.get(6), artists.get(3)));
		aa.add(new AlbumArtist(albums.get(7), artists.get(10)));
		aa.add(new AlbumArtist(albums.get(8), artists.get(5)));
		aa.add(new AlbumArtist(albums.get(8), artists.get(6)));
		aa.add(new AlbumArtist(albums.get(8), artists.get(7)));
		aa.add(new AlbumArtist(albums.get(8), artists.get(8)));
		aa.add(new AlbumArtist(albums.get(9), artists.get(1)));
		aa.add(new AlbumArtist(albums.get(10), artists.get(9)));
		new AlbumArtistService(em).persistAlbumArtistList(aa);
	}

	private void initAlbumSongs() {
		List<AlbumSong> as = new ArrayList<AlbumSong>();
		as.add(new AlbumSong(albums.get(0), songs.get(0), 1));
		as.add(new AlbumSong(albums.get(1), songs.get(1), 1));
		as.add(new AlbumSong(albums.get(1), songs.get(2), 2));
		as.add(new AlbumSong(albums.get(2), songs.get(3), 3));
		as.add(new AlbumSong(albums.get(2), songs.get(4), 3));
		as.add(new AlbumSong(albums.get(3), songs.get(5), 1));
		as.add(new AlbumSong(albums.get(5), songs.get(6), 1));
		as.add(new AlbumSong(albums.get(4), songs.get(7), 1));
		as.add(new AlbumSong(albums.get(4), songs.get(8), 1));
		as.add(new AlbumSong(albums.get(4), songs.get(9), 1));
		as.add(new AlbumSong(albums.get(6), songs.get(10), 1));
		as.add(new AlbumSong(albums.get(7), songs.get(11), 1));
		as.add(new AlbumSong(albums.get(8), songs.get(12), 1));
		as.add(new AlbumSong(albums.get(8), songs.get(13), 1));
		as.add(new AlbumSong(albums.get(8), songs.get(14), 1));
		as.add(new AlbumSong(albums.get(8), songs.get(15), 1));
		as.add(new AlbumSong(albums.get(9), songs.get(16), 1));
		as.add(new AlbumSong(albums.get(9), songs.get(17), 1));
		as.add(new AlbumSong(albums.get(10), songs.get(18), 1));
		new AlbumSongService(em).persistAlbumSongList(as);
	}


	public EntityManager getEntityManager() {
		return em;
	}

}