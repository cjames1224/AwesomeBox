package com.netbuilder.awesomebox.dbaccess;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.netbuilder.awesomebox.entities.Album;
import com.netbuilder.awesomebox.entities.AlbumArtist;
import com.netbuilder.awesomebox.entities.AlbumSong;
import com.netbuilder.awesomebox.entities.Artist;
import com.netbuilder.awesomebox.entities.Playlist;
import com.netbuilder.awesomebox.entities.PlaylistSong;
import com.netbuilder.awesomebox.entities.Song;
import com.netbuilder.awesomebox.entities.SongArtist;
import com.netbuilder.awesomebox.entities.User;
import com.netbuilder.awesomebox.entityservices.AlbumArtistService;
import com.netbuilder.awesomebox.entityservices.AlbumService;
import com.netbuilder.awesomebox.entityservices.AlbumSongService;
import com.netbuilder.awesomebox.entityservices.ArtistService;
import com.netbuilder.awesomebox.entityservices.PlaylistService;
import com.netbuilder.awesomebox.entityservices.PlaylistSongService;
import com.netbuilder.awesomebox.entityservices.SongArtistService;
import com.netbuilder.awesomebox.entityservices.SongService;
import com.netbuilder.awesomebox.entityservices.UserService;

@LocalBean
@Singleton
@Startup
@DataSourceDefinition(

	name = "JDNIPool",
	className = "com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource" ,
    user = "root",
    password = "P4ssword"
		
)
public class DBPopulator {

	
	private ArrayList<User> users;
	private ArrayList<Artist> artists;
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlists;
	private ArrayList<Song> songs;

	@Inject private EntityManager em;
	@Inject private SongService ss;
	@Inject private ArtistService ars;
	@Inject private AlbumService as;
	@Inject private UserService us;
	@Inject private PlaylistService ps;
	@Inject private SongArtistService sas;
	@Inject private PlaylistSongService pss;
	@Inject private AlbumSongService ass;
	@Inject private AlbumArtistService aas;

	@PostConstruct
	public void populateDB() {

		//clearDB();
		
		if(em.createNativeQuery("SELECT * FROM Song").getResultList().size() == 0){
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
		ars.persistArtistList(artists);
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
		sas.persistSongArtistList(sa);

	}

	private void initSongs() {
		songs = new ArrayList<Song>();
		songs.add(new Song("I'm a Slave 4 U", 204,
				"/songs/br.mp3", "Pop", 2));
		songs.add(new Song("Oops!... I Did it Again", 211,
				"/songs/br.mp3", "Pop", 5));
		songs.add(new Song("Don't Let Me Be the Last to Know", 230,
				"/songs/br.mp3", "Pop", 3));
		songs.add(new Song("Through the Fire and Flames", 441,
				"/songs/br.mp3", "Power Metal", 5));
		songs.add(new Song("The Flame of Youth", 401,
				"/songs/br.mp3", "Power Metal", 1));
		songs.add(new Song("Ring of Fire", 195, "/songs/br.mp3",
				"Power Metal", 4));
		songs.add(new Song("21st Century Breakdown", 309,
				"/songs/br.mp3", "Punk Rock", 3));
		songs.add(new Song("American Idiot", 174,
				"/songs/br.mp3", "Punk Rock", 5));
		songs.add(new Song("Wake Me Up When September Ends", 285,
				"/songs/br.mp3", "Punk Rock", 4));
		songs.add(new Song("Boulevard of Broken Dreams", 265,
				"/songs/br.mp3", "Punk Rock", 5));
		songs.add(new Song("K.O.B.E.", 239, "/songs/br.mp3",
				"Hip-hop", 5));
		songs.add(new Song("Kobe Bryant", 305, "/songs/br.mp3",
				"Rap", 1));
		songs.add(new Song("Do You Want To Build A Snowman", 207,
				"/songs/br.mp3", "Musical", 2));
		songs.add(new Song("Let It Go", 224, "/songs/br.mp3", "Musical",
				5));
		songs.add(new Song("Let It Go", 226, "/songs/br.mp3",
				"Musical", 3));
		songs.add(new Song("The Trolls", 102, "/songs/br.mp3",
				"Musical", 1));
		songs.add(new Song("It's My Life", 224, "/songs/br.mp3",
				"Hard Rock", 5));
		songs.add(new Song("One Wild Night", 283, "/songs/br.mp3",
				"Hard Rock", 5));
		songs.add(new Song("Just Hanging Out (With My Family)", 345,
				"/songs/br.mp3", "Movie", 5));
		ss.persistSongList(songs);
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
		as.persistAlbumList(albums);

	}
	
	private void initUsers() {
		users = new ArrayList<User>();
		users.add(new User("Christian", "P4ssword",0,true));
		users.add(new User("Charleigh", "P4ssword"));
		users.add(new User("David", "P4ssword"));
		users.add(new User("Daniel", "P4ssword"));
		us.persistUserList(users);
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
		pss.persistPlaylistSongList(ps);

	}


	private void initPlaylists() {
		playlists = new ArrayList<Playlist>();
		playlists.add(new Playlist("Charleigh's Playlist", users.get(0)));
		playlists.add(new Playlist("Christian's Playlist", users.get(1)));
		playlists.add(new Playlist("David's Playlist", users.get(2)));
		playlists.add(new Playlist("Daniel's Playlist", users.get(3)));
		ps.persistPlaylistList(playlists);
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
		aas.persistAlbumArtistList(aa);
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
		ass.persistAlbumSongList(as);
	}


	public EntityManager getEntityManager() {
		return em;
	}

}
