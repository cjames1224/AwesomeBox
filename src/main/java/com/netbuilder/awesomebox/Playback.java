package com.netbuilder.awesomebox;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Playback {

	private AudioInputStream stream;
	private String fileLocation;
	private SourceDataLine line;
	private int numRead = 0, offset = 0;
	private byte[] buffer;
	private Runnable playing;

	private static Playback instance = null;

	private Playback(){

	}

	public static Playback getInstance(){
		if(instance == null){
			instance = new Playback();
		}return instance;
	}

	public boolean isPlaying(){
		if(line != null)
			return line.isRunning();
		return false;
	}

	public void clearValues(){
		stream = null;
		line = null;
		numRead = 0;
		offset = 0;
		byte[] buffer = null;
	}

	public void togglePlay(){
		if(line.isRunning()){
			//System.out.println("Paused. Type pause to play");
			line.stop();
			line.drain();
		}else{
			//System.out.println("Playing. Type pause to pause");
			line.start();
			//pb.line.drain();
		}
	}

	public void createLineFromPath(String fileLocation) {
		try {
			clearValues();

			this.fileLocation = fileLocation;
			File f = new File(fileLocation);
			stream = AudioSystem.getAudioInputStream(new File(fileLocation));			

			AudioFormat format = stream.getFormat();
			if(format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED){
				format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
						format.getSampleRate(),
						format.getSampleSizeInBits() * 2,
						format.getChannels(),
						format.getFrameSize() * 2,
						format.getFrameRate(),
						true);
				stream = AudioSystem.getAudioInputStream(format, stream);
			}

			SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, 
					stream.getFormat(),
					((int) stream.getFrameLength() * format.getFrameSize()));

			initStream(info);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initStream(Info info) throws Exception{
		line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(stream.getFormat());
		line.start();

		buffer = new byte[32];
		String[] fileSplit = fileLocation.split("/");
		//System.out.println("Begun streaming " + fileSplit[fileSplit.length - 1]);
		line.stop();
		playing = new Runnable(){
			public void run() {
				int totals = 0;
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
				//System.out.println("Song over. Thanks for listening. Line closed");
				//System.exit(0);
			}
		};

		new Thread(playing).start();

	}

	//move 5 buffer frames ahead
	public void fastForward(){
		stream.mark(32);
		try {
			stream.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//move 5 buffer frames back
	public void rewind(){

	}

	public void stop(){
		line.close();
		//System.out.println("Thanks for listening. Line closed");
		//System.exit(0);
	}


//	public static void main(String[] args){
//		Playback pb = new Playback();
//
//		try {
//			pb.createLineFromPath("C:/Users/Training12/Desktop/bonjovi-itsmylife.wav");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		pb.togglePlay();
//
//		String control = "";
//		Scanner scan = new Scanner(System.in);
//		for(;;){
//			System.out.print("\"stop\" - stops song\n\"pause\" - toggles pause\n\"ff\" - fast-foward\n\"rw\" - rewinds\n>> ");
//			control = scan.nextLine();
//			switch(control){
//			case "pause":
//				pb.togglePlay();
//				break;
//			case "ff":
//				pb.fastForward();
//				break;
//			case "rw":
//				pb.rewind();
//				break;
//
//			case "stop":
//				pb.stop();
//				break;
//				//break;
//			default:
//				break;
//			}
//		}
//
//
//	}


}
