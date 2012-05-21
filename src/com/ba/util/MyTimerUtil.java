package com.ba.util;

import java.util.Timer;
import java.util.TimerTask;


public class MyTimerUtil {
    TimerListener listener = null;
    long seconds;
    Timer timer;
    
    
    public MyTimerUtil(TimerListener l) {
        this.listener = l;        
    }

    public void startTimer() {
        int interval = 1000;
        seconds = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            	seconds++;
                if (listener != null) {
                    listener.timeChanged(seconds);
                }
            }
        }, 0, interval);
    }
    
    public void stop(){
    	timer.cancel();
    }
}