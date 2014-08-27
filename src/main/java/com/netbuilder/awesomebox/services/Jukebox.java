package com.netbuilder.awesomebox.services;


public class Jukebox {

	private Queue queue;
	
	
	public Jukebox(){}
	
	public boolean playNext(){
		
		queue.play();
		
		return queue.isPlaying();
		
	}
	
	public boolean autoPlayNext(){
		
		if(!queue.isPlaying())
			queue.play();
		
		return queue.isPlaying();
		
	}
	
	
}
