package com.ba.domain;

import java.util.Timer;
import java.util.TimerTask;

public class TimerReservaBici {
	
	private static final int MINUTOS = 15;
	private static TimerReservaBici instance;
	private TimerTask timerTask;
	
	private TimerReservaBici() {
		timerTask = new TimerTask() {
			
			public void run() {
								
			}
		};
	}
	
	public static TimerReservaBici getInstance(){
		if(instance != null)
			instance = new TimerReservaBici();
		return instance;
	}
	
	private void init() {
		new Timer().schedule(timerTask,60000 * MINUTOS);		
		
	}

}
