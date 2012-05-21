package com.ba.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;
import com.pegsoluciones.blackberry.common.util.StringUtils;

/**
 * @author poza pablo
 * metodos de manejo de string
 *
 */
public abstract class BAStringUtils {

	private static final String PUNTITOS_SUSPENSIVOS = "...";

	public static String getStringFromStream(InputStream in, String encoding) throws IOException {
		
		InputStreamReader reader;
	    if (encoding == null) {
	        reader = new InputStreamReader(in);
	    } else {
	        reader = new InputStreamReader(in, encoding);            
	    }

	    StringBuffer sb = new StringBuffer();

	    final char[] buf = new char[1024];
	    int len;
	    while ((len = reader.read(buf)) > 0) {
	        sb.append(buf, 0, len);
	    }
	    return sb.toString();
	}
	
	
	/**
	 * @param s
	 * @param max
	 * @return s si el su largo es menor max, si no s con un largo de max con 
	 *  tres puntos 
	 */
	public static String trucateStringPuntitos(String s, int max){
		if(s.length() <= max)
			return s; 
		if(max<s.length())
			return s.substring(0, max).trim() + "..." ;
		else
			return s.substring(0,s.length()-1).trim() + "..." ;	
	}

	/**
	 * Retorna unaCadena separada por c en un vector
	 * 
	 * @param String: unaCadena
	 * @param char: c
	 * @return Vector
	 */
	public static Vector parse(String string,char c) {

		Vector v = new Vector();		
		StringBuffer buf = new StringBuffer();

		int largo = string.length(); 
		int i = 0;		
		while(largo != i){			
			char c1 = (char)string.getBytes()[i];		
			if(c1 == c){			
				v.addElement(new String(buf));
				buf = new StringBuffer();
			}
			else{
				buf.append(c1);
				if(largo == i + 1)
					v.addElement(new String(buf));
			}	
			i++;
		}		
		return v;
	}    	

	public static String formatPrice(String p){

		int chau = p.indexOf(',');

		if(chau != -1)
			return p;

		int coma = p.indexOf('.'); 
		String centavos;


		String pesos = null; 

		if(coma == -1 ){
			centavos = ".00";
			pesos =  p;
		}
		else{
			centavos = p.substring(coma,p.length());
			pesos = p.substring(0,coma);
		}

		System.out.println("Pesos: " + pesos);
		System.out.println("Centavos: " + centavos);

		String centenares,miles;

		if(p.length() >= 4)
			centenares= pesos.substring(pesos.length() - 3,pesos.length());

		else
			return p; // es menor que 1000

		miles = pesos.substring(0,pesos.length()-3);

		if(!miles.equals(""))
			miles = miles.concat(",");

		return miles + centenares  + centavos;
	}

	public static String replaceAll(String s, String before, String after) {
		if(s == null)	
			return null;
		int index = 0;
		while (true) {
			int next = s.indexOf(before, index);
			if (next < 0) {
				return s;
			}
			s = s.substring(0, next) + after + s.substring(next + before.length());
			index = next + after.length();
		}
	}

	public static String elementosSeparadosPor(Vector vectorString,String separador) {
		String s = new String();

		for(int i = 0; i < vectorString.size();i++)
			s += (String)vectorString.elementAt(i) + (i == vectorString.size() -1 ? "" : separador);

		return s;
	}

	
	public static int drawTituloPixel(Graphics g,int xT, int yT
			,String cadena, int maxPixelAncho,int renglonesMax){
		String r1 = null;
		String aux = cadena;

		try {
			r1 = new String("".getBytes(),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Vector v = BAStringUtils.parse(cadena,' ') ;

		if(v.size()>0)
			r1= r1.concat( ((String)v.elementAt(0)).concat(" "));
		else
			return 1;
		
		int i = 1; 
		while( v.size() > i && (GraphicUtil.getAdvance(g,(String)v.elementAt(i) + r1)<= maxPixelAncho))
			r1 =r1.concat( ((String)v.elementAt(i++)).concat(" "));

		if(renglonesMax == 1){
			g.drawText(trucatePixeles(cadena,g, maxPixelAncho), xT,yT);
			return 1;
		}else
			g.drawText(cadena.substring(0,r1.length() - 1),xT,yT);

		if(v.size() <= i )
			return 1;

		int pos = posicionDelCaracter(aux,' ',i);

		String substring = cadena.substring(pos,aux.length());				
		return 1 + drawTituloPixel(g,xT,yT + g.getFont().getHeight(),
				"" + substring,maxPixelAncho,renglonesMax - 1);
	}	
	
	public static int drawTituloPixelCentrada(Graphics g,int xT, int yT
			,String cadena, int maxPixelAncho,int renglonesMax){
		String r1 = null;
		String aux = cadena;

		try {
			r1 = new String("".getBytes(),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Vector v = StringUtils.parse(cadena,' ') ;

		if(v.size()>0)
			r1= r1.concat( ((String)v.elementAt(0)).concat(" "));
		else
			return 1;
		
		int i = 1; 
		while( v.size() > i && (GraphicUtil.getAdvance(g,(String)v.elementAt(i) + r1)<= maxPixelAncho))
			r1 =r1.concat( ((String)v.elementAt(i++)).concat(" "));

		if(renglonesMax == 1){
			g.drawText(trucatePixeles(cadena,g, maxPixelAncho), xT
					+ PosicionesUtil.centrada(g.getFont().getAdvance(trucatePixeles(cadena,g, maxPixelAncho)),
						maxPixelAncho),yT);
			return 1;
		}else
			g.drawText(cadena.substring(0,r1.length() - 1),xT + 
					PosicionesUtil.centrada(g.getFont().getAdvance(cadena.substring(0,r1.length() - 1)),
							maxPixelAncho),yT);

		if(v.size() <= i )
			return 1;

		int pos = posicionDelCaracter(aux,' ',i);

		return 1 + drawTituloPixelCentrada(g,xT,yT + g.getFont().getHeight()+1,
				aux.substring(pos,aux.length()),maxPixelAncho,renglonesMax - 1);
	}			
	
	
	public static String trucatePixeles(String s,Graphics g, int pixelesMax){
		
		//StringBuffer truncada = new StringBuffer();		
		int i = 0;
		int largo = 0;
		
		while(i < s.length() && largo < pixelesMax){
			i++;
			largo =  GraphicUtil.getAdvance(g,s.substring(0,i));
		}
		
		String sT = s.substring(0,i);
		int length = sT.length();
		String fin = length == s.length() ? "" : PUNTITOS_SUSPENSIVOS;
		if(fin.equals(PUNTITOS_SUSPENSIVOS))
			sT = sT.substring(0,length - 3);
		return sT.trim().concat(fin);
	}

	/**
	 * Dibuja a cadena en g en la posicions (xt,yt) 
	 * 
	 * @param g
	 * @param xT
	 * @param yT
	 * @param cadena
	 * @param largoMax por renglon
	 * @param renglonesMax
	 * @return
	 */
	public static int drawTitulo(Graphics g,int xT, int yT
			,String cadena, int largoMax,int renglonesMax){
		String r1 = null;
		String aux = cadena;

		try {
			r1 = new String("".getBytes(),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Vector v = parse(cadena,' ') ;

		if(v.size()>0)
			r1= r1.concat( ((String)v.elementAt(0)).concat(" "));
		else
			return 1;

		int i = 1; 
		while( v.size() > i && (r1 + (String)v.elementAt(i)).length() <= largoMax)
			r1 =r1.concat( ((String)v.elementAt(i++)).concat(" "));

		if(renglonesMax == 1){
			g.drawText(trucateStringPuntitos(cadena, largoMax), xT,yT);
			return 1;
		}else
			g.drawText(cadena.substring(0,r1.length() - 1),xT,yT);

		if(v.size() <= i )
			return 1;

		int pos = posicionDelCaracter(aux,' ',i);

		return 1 + drawTitulo(g,xT,yT + g.getFont().getHeight()+1,aux.substring(pos,aux.length()),largoMax,renglonesMax - 1);
	}	
	/**
	 * Dibuja a cadena en g en la posicions (xt,yt) 
	 * 
	 * @param g
	 * @param xT
	 * @param yT
	 * @param cadena
	 * @param largoMax por renglon
	 * @param renglonesMax
	 * @return
	 */
	/**
	 * Dibuja a cadena en g en la posicions (xt,yt) 
	 * 
	 * @param xT
	 * @param yT
	 * @param cadena
	 * @param largoMax por renglon
	 * @param renglonesMax
	 * @return Cantidad de renglones que ocupa "cadena"
	 */
	public static int LineCant(Font font, int xT, int yT
			,String cadena, int largoMax,int renglonesMax){
		String r1 = null;
		String aux = cadena;
		
		try {
			r1 = new String("".getBytes(),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Vector v = parse(cadena,' ') ;
		
		if(v.size()>0)
			r1= r1.concat( ((String)v.elementAt(0)).concat(" "));
		else
			return 1;
		
		int i = 1; 
		while( v.size() > i && (r1 + (String)v.elementAt(i)).length() <= largoMax)
			r1 =r1.concat( ((String)v.elementAt(i++)).concat(" "));
		
		if(renglonesMax == 1){
			return 1;
		}else
		if(v.size() <= i )
			return 1;
		
		int pos = posicionDelCaracter(aux,' ',i);
		
		return 1 + LineCant(font, xT,yT + font.getHeight()+1,aux.substring(pos,aux.length()),largoMax,renglonesMax - 1);
	}	

	public static String decoder(String s){
		s=replaceAll(s,"&reg;", "ï¿½");
		s= replaceAll(s,"#160;", " ");
		s= replaceAll(s,"&quot;","");
		s= replaceAll(s,"&apos;","'");
		s= replaceAll(s,"&lt;", "<");
		s= replaceAll(s,"&gt;", ">");
		s= replaceAll(s,"middot;", "ï¿½");
		s= replaceAll(s, "&deg", "ï¿½");
		s= replaceAll(s, "&aacute;", "ï¿½");
		s= replaceAll(s, "&amp", "&");
		s= replaceAll(s, "&ocirc", "ï¿½");
		s= replaceAll(s,"&Aacute;","ï¿½");
		s= replaceAll(s,"&Eacute;", "ï¿½");
		s= replaceAll(s,"&Iacute;", "ï¿½");
		s= replaceAll(s,"&Oacute;", "ï¿½");
		s= replaceAll(s,"&Uacute;", "ï¿½");
		s= replaceAll(s,"&aacute;","ï¿½");
		s= replaceAll(s,"&eacute;", "ï¿½");
		s= replaceAll(s,"&iacute;", "ï¿½");
		s= replaceAll(s,"&oacute;", "ï¿½");
		s= replaceAll(s,"&uacute;", "ï¿½");
		s= replaceAll(s,"&amp;", "&");
		s= replaceAll(s,"&ntilde;", "ï¿½");
		s= replaceAll(s,"&Ntilde;", "N");
		s= replaceAll(s,"&;", "");
		s= replaceAll(s,"Aacute;","ï¿½");
		s= replaceAll(s,"Eacute;", "ï¿½");
		s= replaceAll(s,"Iacute;", "ï¿½");
		s= replaceAll(s,"Oacute;", "ï¿½");
		s= replaceAll(s,"Uacute;", "ï¿½");
		s= replaceAll(s,"aacute;","ï¿½");
		s= replaceAll(s,"eacute;", "ï¿½");
		s= replaceAll(s,"iacute;", "ï¿½");
		s= replaceAll(s,"oacute;", "ï¿½");
		s= replaceAll(s,"uacute;", "ï¿½");
		s= replaceAll(s,"&amp;", "&");
		s= replaceAll(s,"ntilde;", "ï¿½");
		s= replaceAll(s,"Ntilde;", "N");
		s= replaceAll(s,"<p>", "");
		s= replaceAll(s,"</p>", "");
		s= replaceAll(s,"<i>", "");
		s= replaceAll(s,"</i>", "");
		s= replaceAll(s,"<b>", "");
		s= replaceAll(s,"</b>", "");

		return s;


	}	


	/**
	 * 
	 * devuelve la posicion del caracter c en su aparcion numero nro
	 * 
	 * 
	 * @param string
	 * @param c
	 * @param numero
	 * @return
	 */
	private static int posicionDelCaracter(String string, char c, int nro) {

		int largo = string.length(); 
		int i = 0;
		int nro1 = 0;

		while(largo != i &&  nro1 != nro){
			char c1 = (char)string.getBytes()[i++];
			if(c == c1)
				nro1++;
		}


		if(nro1 == nro)
			return i;
		return 0;
	}		
	
	/**
	 * 
	 * 
	 * decodifica un string en formato hexa a un string de texto usando 
	 * el encoding especificado
	 * 
	 * 
	 * @param hex - la cadena en hexa a ser transformada en string
	 * @param encoding - en encoding que se va a utilizar
	 * @return el string decodificado
	 * @throws UnsupportedEncodingException 
	 */
	public static final String hexStringToEncodedString(final String hex, 
			final String encoding) throws UnsupportedEncodingException {
        byte [] bytes = new byte[(hex.length() / 2)];
        int j = 0;
        for ( int i=0; i<bytes.length; i++ ) {
            j = i * 2;
            String hex_pair = hex.substring(j,j+2);
            byte b = (byte) (Integer.parseInt(hex_pair, 16) & 0xFF);
            bytes [i] = b;
        }
        String returnString = null;
        returnString = new String(bytes, encoding);
        
        return returnString;
    }
}
