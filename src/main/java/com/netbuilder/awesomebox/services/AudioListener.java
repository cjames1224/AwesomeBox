package com.netbuilder.awesomebox.services;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

@Named
@SessionScoped
public class AudioListener implements LineListener, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Queue queue;
	private boolean done = false;
	
	@Override
	public void update(LineEvent event) {
		if (event.getType() == LineEvent.Type.CLOSE) {
			System.out.println("THIS \n IS \n happenenging");
			queue.next();
		}
	}
	
	public synchronized void waitUntilDone() throws InterruptedException {
	      while (!done) { 
	    	  wait(); 
	      }
	}

}
