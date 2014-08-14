package com.netbuilder.awesomebox;

import java.util.ArrayList;

public class Queue {

	private ArrayList<Song> queue;
	
	public Queue() {
		queue = new ArrayList<Song>();
	}
	
	public void addSong(Song song) {
		if (song == null) {
			throw new ValidationException("Invalid Song");
		}
		
		queue.add(song);
	}
	
	public void removeSong(Song song) {
		if (song == null) {
			throw new ValidationException("Invalid Song");
		}
		
		queue.remove(song);
	}
	
	public void clear() {
		queue.clear();
	}
	
	public void play() {
		Playback.getInstance().createLineFromPath(queue.remove(0).getFileLocation());
	}
}
