package com.netbuilder.awesomebox;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
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

@Named
@SessionScoped
public class PlaybackBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private SongService ss;
	private AudioInputStream stream;
	private SourceDataLine line = null;
	
	public PlaybackBean(){
	}
	
	public String initAndStartLine(int id){
/*		stream = ss.getStreamFromSongById(id);
		System.out.println(ss.listSongsByID(id));
		
		try {
			System.out.println("inside try block");
			line = (SourceDataLine) AudioSystem.getLine(
					new DataLine.Info(SourceDataLine.class, stream.getFormat(),
						((int) stream.getFrameLength() * stream.getFormat().getFrameSize())));
			line.open(stream.getFormat());
		} catch (LineUnavailableException e) {
			System.err.println("Could not initiate Line from songId and inputstream");
			e.printStackTrace();
		}
		startLine();*/
		
		//File audioFile = new File("http://localhost:8080/awesomebox/songs/br.wav");

		try {
			URL url = new URL("http://localhost:8080/awesomebox/songs/br.wav");
			//AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(url);
			AudioFormat format = audioStream1.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			//audioClip.addLineListener(this);
			audioClip.open(audioStream1);
			audioClip.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public void startLine(){
		line.start();
		
		byte[] buffer = new byte[32];
		Runnable playing = new Runnable(){
			public void run() {
				int totals = 0;
				int numRead, offset;
				try {
					while((numRead = stream.read(buffer, 0 , buffer.length)) >= 0){
						offset = 0;
						while(offset < numRead){
							offset += line.write(buffer, offset, numRead - offset);
						}

						stream.mark(totals);
						totals += 32;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				line.drain();
				line.stop();
				line.close();

			}
		};

		new Thread(playing).start();
		
	}
	

}
