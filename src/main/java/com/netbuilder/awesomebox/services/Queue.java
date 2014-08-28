package com.netbuilder.awesomebox.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.netbuilder.awesomebox.ValidationException;
import com.netbuilder.awesomebox.entities.Song;
import com.netbuilder.awesomebox.entities.User;

@Named
@SessionScoped
public class Queue implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Song> queue;
	private boolean isShuffle;
	private Song currentSong;
	@Inject
	private PlaybackBean playback;
	private String[] shuffleToggle = new String[] {
			"resources/images/kobe_Shuffle_Off.png",
			"resources/images/kobe_Shuffle_On.png" };
	private String shuffleImage = shuffleToggle[0];

	public Queue() {
		queue = new ArrayList<Song>();
		isShuffle = false;
	}

	public void setShuffleImage(String shuffleImage) {
		this.shuffleImage = shuffleImage;
	}

	public String getShuffleImage() {
		return shuffleImage;
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

	public void addPlaylist(List<Song> playlist) {
		queue.addAll(playlist);
	}

	public Song getCurrentSong() {
		return currentSong;
	}

	public void removeSong(Song song) {
		if (song == null) {
			throw new ValidationException("Invalid Song");
		}
		queue.remove(song);
	}

	public ArrayList<Song> getQueue() {
		return queue;
	}

	public boolean isPlaying() {
		return Playback.getInstance().isPlaying();
	}

	public void clear() {
		queue.clear();
	}

	public void toggleShuffle(boolean isAdmin) {
		if (isAdmin) {
			isShuffle = !isShuffle;
			if (shuffleImage.equals("resources/images/kobe_Shuffle_Off.png")) {
				shuffleImage = shuffleToggle[1];
			} else {
				shuffleImage = shuffleToggle[0];
			}
		} else {
			throw new ValidationException(
					"User is not an Admin and cannot toggle shuffle");
		}
	}

	public void next() {
		currentSong = null;
		togglePlay();
	}

	public void togglePlay() {
		if (queue.size() == 0) {
			return;
		}
		if (currentSong == null) {
			if (isShuffle) {
				currentSong = queue.remove(new Random().nextInt(queue.size()));
			} else {
				currentSong = queue.remove(0);
			}
			playback.changeSong(currentSong.getFileLocation());
		}else{
			playback.togglePlay(null);
		}
	}
}