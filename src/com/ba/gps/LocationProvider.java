package com.ba.gps;




import com.pegsoluciones.blackberry.common.util.Location;

import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Status;





public class LocationProvider {

	
	public static int n = 0;

    public static Location getLocation() {
    
    	Status.show("Abriendo gps");
    	GPS.start();
    	
        Location ret = new Location();
        
        GPSLocation gpsLoc = GPS.getLocation( 5 );
        

        if (gpsLoc != null) { 
        // Bien, tenemos una posici�n buena.
            ret.lat = gpsLoc.coordinates.getLatitude();
            ret.lng = gpsLoc.coordinates.getLongitude();
        }else {
        	//Status.show("GPS is not avalible. Precision 700mts." );
            // No se pudo obtener la posicion por GPS.              
            // Intentamos por Radio.
            GPSRadioLocation radLoc = GPSRadio.getGoogleMapsPos();
            if ( radLoc != null ) {
               ret.lat = radLoc.lat;
               ret.lng = radLoc.lng;
            }
            else {
               return null;
            }    
         }
             
        return ret;
    }

   
    public static double getMiLat(){
        return getLocation().lat;
    }

    public static double getMiLon(){
        return getLocation().lng;
    }


} 
