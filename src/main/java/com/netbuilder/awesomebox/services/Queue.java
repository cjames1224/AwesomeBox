package com.netbuilder.awesomebox.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.netbuilder.awesomebox.ValidationException;
import com.netbuilder.awesomebox.entities.Song;
import com.netbuilder.awesomebox.entities.User;

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
	
	public boolean isEmpty() {
		if (queue == null) {
			return true;
		} else {
			return queue.size() < 1;
		}
	}
	public boolean isNotEmpty() {
		return !this.isEmpty();
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
	
	public ArrayList<Song> getQueue(){
		return queue;
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
