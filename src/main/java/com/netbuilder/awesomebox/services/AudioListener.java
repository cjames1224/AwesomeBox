package com.netbuilder.awesomebox.services;

import javax.inject.Inject;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioListener implements LineListener{
	
	@Inject
	private Queue queue;

	@Override
	public void update(LineEvent event) {
		if (event.getType() == event.getType().CLOSE) {
			queue.next();
		}
	}

}
