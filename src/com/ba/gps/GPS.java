package com.ba.gps;

import javax.microedition.location.Location;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;

public class GPS {
        
    private static int _interval  =  60;    
    private static int _timeout   =  10;    
    private static int _maxage    =  20;
    
    private static LocationProvider _locationProvider;
    private static GPSLocation      _loc;
    
    public static void setInterval( int seconds ) {
        _interval = seconds;
    }
    
    public static GPSLocation getLocation() {
        return _loc;
    }

    public static GPSLocation getLocation( int mTime ) {
        
        // Esta funci�n devuelve la �ltima location tomada,
        // si es que fue tomada dentro de los mTime milisegundos
        // si es m�s vieja que eso, devuelve null.
        
        if ( _loc == null ) {
            return null;
        }
        if ( _loc.sysDate - System.currentTimeMillis() < mTime ) {
            return _loc;
        }
        
        return null;
        
    }

    
    public static boolean start() {

        boolean retval = false;
        
        if ( _locationProvider != null ) {
            stop();
        }
        
        try {
            
            _locationProvider = LocationProvider.getInstance(null);
            
            if ( _locationProvider == null ) {
    
                // Not supported.

            } 
            else {
                
                // only a single listener can be associated with a provider, and unsetting it involves the same
                // call but with null, therefore, no need to cache the listener instance
                _locationProvider.setLocationListener( new LocationListener() {
                    public void locationUpdated(LocationProvider provider, Location location) {
                        
                        if ( location.isValid() ) {
                
                            _loc = new GPSLocation();
                            _loc.coordinates     = location.getQualifiedCoordinates();
                            _loc.timeStamp       = location.getTimestamp();
                            _loc.speed           = location.getSpeed();                        
                            _loc.sysDate         = System.currentTimeMillis();
                                
                        }
                        
                    }

                    public void providerStateChanged(LocationProvider provider, int newState) {
                        // no logr� que se dispare nunca este evento.
                    }
                    
                }, _interval, _timeout, _maxage);
                retval = true;
                
            }
            
        } 
        catch ( Exception ex ) {
            //throw new RuntimeException( "Iniciando GPS: " + ex.toString() );
            System.out.println( "Iniciando GPS:" + ex.getMessage() );
        }
        
        return retval;
    }
    
    public static void stop() {
        try {
            if ( _locationProvider != null ) {
                _locationProvider.reset();
                _locationProvider.setLocationListener(null, -1, -1, -1);
            }        
        }
        catch( Exception ex ) {
        } 
        _locationProvider = null;
    }

}
