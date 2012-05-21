package com.ba.field.manager;

import java.util.Vector;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;

import com.ba.domain.Noticia;
import com.ba.field.field.NoticiaField;
import com.pegsoluciones.blackberry.common.ui.field.Linea;

public class NoticiasManager extends BAManager {

	public NoticiasManager(Vector noticias) {
		
		int size = noticias.size();
		for(int i = 0 ; i < size ; i++){
			Noticia n = (Noticia) noticias.elementAt(i);
			add(new NoticiaField(n));
			if(i < size -1 ){
				add(new Linea(Color.DARKGRAY,Color.WHITESMOKE,Display.getWidth(),10,NON_FOCUSABLE));
			}
		}		
	}

}
