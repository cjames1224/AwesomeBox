package com.netbuilder.awesomebox.services;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JApplet;

import com.netbuilder.awesomebox.entityservices.SongService;

@Named
@SessionScoped
public class PlaybackBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private SongService ss;
	private AudioInputStream stream;
	private SourceDataLine line = null;
	private Clip audioClip;
	private boolean isPlaying = false;
	private String[] buttonToggle= new String[]{"resources/images/kobePlay.png", "resources/images/kobePause.png"};
	private String image = buttonToggle[0];
	
	public PlaybackBean(){
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public String getImage(){
		return image;
	}
	
	public String initAndStartLine(){
		try {
			URL url = new URL("http://localhost:8080/awesomebox/songs/br.wav");
			audioClip = AudioSystem.getClip();

			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			audioClip.open(ais);
			audioClip.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
		
	public void pause() {
		audioClip.stop();
	}
	
	public void play() {
		audioClip.start();
	}
	
	public String togglePlay() {
		if (audioClip == null) {
			initAndStartLine();
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
	

}
