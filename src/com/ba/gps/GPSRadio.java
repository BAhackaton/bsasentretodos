package com.ba.gps;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.system.GPRSInfo;

import com.pegsoluciones.blackberry.common.device.StringConexionUtil;




public class GPSRadio {
        
    public static GPSRadioLocation getGoogleMapsPos() {
        
        //String sRes = "Lat:None,Long:None";
        GPSRadioLocation ret = null;
        
        HttpConnection http = null;
        OutputStream os = null;
        InputStream is = null;
        
        try {

            GPRSInfo.GPRSCellInfo ct = GPRSInfo.getCellInfo();

            // TODO: Enviar tmb mcc y mnc

            if ( ct != null && ct.getCellId() != 0 ) {

                String googleURI = "http://www.google.com/glm/mmap";
                
                //System.out.println( "Connecting to " + googleURI );
                
                byte[] formData = null;
                
                // Create the connection
  
              //  ConnectionPreferences cPref = new ConnectionPreferences();
                String cnx = StringConexionUtil.urlConexion( googleURI );
                            
                // Conecta para descargar el archivo.
                http = (HttpConnection) Connector.open(  cnx, Connector.READ_WRITE, true );
                //http = (HttpConnection) Connector.open( googleURI );
        
                http.setRequestMethod( HttpConnection.POST );
                            
                os = http.openOutputStream();
                
                getFormPostData( ct, os );
                
                //This line sends the request
                int rc = http.getResponseCode();
                if (rc != HttpConnection.HTTP_OK) {
                    System.out.println( googleURI + " response code :" + String.valueOf(rc) );
                    return null;
                }            
                is = http.openInputStream();
                DataInputStream dis = new DataInputStream(is); 
                
                // Read some prior data 
                dis.readShort(); 
                dis.readByte(); 
                // Read the error-code 
                int errorCode = dis.readInt(); 
                
                double lat = 0.0;
                double lng = 0.0;
                
                if (errorCode == 0)
                { 
                    lat = (double) dis.readInt() / 1000000D; 
                    lng = (double) dis.readInt() / 1000000D; 
                }
                
                ret = new GPSRadioLocation();
                ret.lat = lat;
                ret.lng = lng;


            }

                        
            
        }
        catch(Exception ex) {

            System.out.println( "GPSRadio: "  + ex.toString() );

        }
        finally {
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
                if (http != null)
                    http.close();
            }
            catch(Exception ex) {
                
                //System.out.println( "GPSRadio: " +  ex.toString() );
                
            }
        }
        
        return ret;
        
    }  
    
    
    private static void getFormPostData( GPRSInfo.GPRSCellInfo ct, OutputStream outputStream ) throws IOException {
        
          DataOutputStream os = new DataOutputStream(outputStream); 
          os.writeShort(21); 
          os.writeLong(0); 
          os.writeUTF("fr"); 
          os.writeUTF("Sony_Ericsson-K750"); 
          os.writeUTF("1.3.1"); 
          os.writeUTF("Web"); 
          os.writeByte(27); 

          os.writeInt(0); os.writeInt(0); os.writeInt(3); 
          os.writeUTF(""); 
          os.writeInt( ct.getCellId() ); // CELL-ID 
          os.writeInt( ct.getLAC() ); // LAC 
          os.writeInt(0); os.writeInt(0); 
          os.writeInt(0); os.writeInt(0); 
          //os.flush();        
    }
    
} 


