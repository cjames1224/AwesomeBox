package com.netbuilder.awesomebox;

import java.util.ArrayList;
import java.util.Random;

public class Queue {

	private ArrayList<Song> queue;
	private boolean isShuffle;
	
	public Queue() {
		queue = new ArrayList<Song>();
	}
	
	public void addSong(Song song) {
		if (song == null) {
			throw new ValidationException("Invalid Song");
		}
		
		if (queue.contains(song)) {
			throw new ValidationException("Song already in Queue");
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
	
	public void toggleShuffle(User user) {
		if (user.isAdmin()) {
			isShuffle = !isShuffle;
		} else {
			throw new ValidationException("User is not an Admin and cannot toggle shuffle");
		}
	}
	
	public void play() {
		if (isShuffle) {
			Playback.getInstance().createLineFromPath(queue.remove(new Random().nextInt(queue.size())).getFileLocation());
		} else {
			Playback.getInstance().createLineFromPath(queue.remove(0).getFileLocation());
		}
	}
}
