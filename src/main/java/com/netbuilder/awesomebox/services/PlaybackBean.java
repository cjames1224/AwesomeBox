package com.netbuilder.awesomebox.services;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JApplet;

import com.netbuilder.awesomebox.entities.Song;
import com.netbuilder.awesomebox.entityservices.SongService;

@Named
@ApplicationScoped
public class PlaybackBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private SongService ss;
	//private AudioInputStream stream;
	private Song currentSong;
	private SourceDataLine line = null;
	private Clip audioClip;
	private boolean isPlaying = false;
	private String[] buttonToggle= new String[]{"resources/images/kobePlay.png", "resources/images/kobePause.png"};
	private String image = buttonToggle[0];
	private AudioListener audioListener= new AudioListener();

	
	public PlaybackBean(){
	}
	
	
	public void setImage(String image){
		this.image = image;
	}
	
	public String getImage(){
		return image;
	}
	
	public String initAndStartLine(String urls){
		try {
			URL url = new URL(urls);
			audioClip = AudioSystem.getClip();
			audioClip.addLineListener(audioListener);

			try(AudioInputStream stream = AudioSystem.getAudioInputStream(url)){
				audioClip.open(stream);
			}
			audioClip.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
		
	public String closePlayback(){
		System.out.println("CALLING CLOSE PLAYBACK");
		audioClip.stop();
		audioClip.close();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	            .getExternalContext().getSession(false);
	    session.invalidate();
	    return null;
	}
	
	public void pause() {
		audioClip.stop();
	}
	
	public void play() {
		audioClip.start();
	}
	
	public Song getSong(){
		return currentSong;
	}
	
	public void setSong(Song s){
		currentSong = s;
	}
	
	public String togglePlay(String url) {
		if (audioClip == null) {
			initAndStartLine(url);
			audioClip.start();
			isPlaying = true;
			image = buttonToggle[1];
		}else{

			if(isPlaying) {
				image = buttonToggle[0];
				audioClip.stop();
				isPlaying=false;
			} else {
				image = buttonToggle[1];
				audioClip.start();
				isPlaying = true;
			}
		}
		return null;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	

}
