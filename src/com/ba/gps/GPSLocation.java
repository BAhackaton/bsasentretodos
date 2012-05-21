package com.ba.gps;
import javax.microedition.location.QualifiedCoordinates;

public class GPSLocation {
    
    // the coordinates of this location and their accuracy.
    public QualifiedCoordinates coordinates;

    // the terminal's current ground speed in meters per second (m/s) at the time of measurement.
    public float speed;
    
    // Returns the time stamp at which the data was collected.
    public long timeStamp;
    
    public long sysDate;
    
    public float getAccuracy() {

        // Devuelve solo la horizontal
        return (float) coordinates.getHorizontalAccuracy();

    }
    
} 

