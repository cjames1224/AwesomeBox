package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Queue implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Song> queue;
	private boolean isShuffle;
	
	public Queue() {
		queue = new ArrayList<Song>();
		isShuffle = false;
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
	
	public boolean isPlaying(){
		return Playback.getInstance().isPlaying();
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
		if (queue.size() == 0) {
			throw new ValidationException("Cannot play, Queue is empty");
		}
		
		if (isShuffle) {
			Playback.getInstance().createLineFromPath(queue.remove(new Random().nextInt(queue.size())).getFileLocation());
		} else {
			Playback.getInstance().createLineFromPath(queue.remove(0).getFileLocation());
		}
	}
}
