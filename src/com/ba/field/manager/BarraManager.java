package com.ba.field.manager;

import com.ba.util.OnClickUtil;
import com.pegsoluciones.blackberry.common.ui.field.ExButtonField;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class BarraManager extends HorizontalFieldManager {

	private static final int ANCHO_BOTON = Display.getWidth() / 3;
	public static final int ALTO_BOTON   = 40;
	private static final int SIZE_FONT   = 15;
	
	
	public BarraManager() {
		add(new ExButtonField("NOTICIAS", Color.BLACK, Color.BLACK, Color.BLACK,
				Color.WHITE, Color.DARKGRAY, Color.DARKGRAY, ANCHO_BOTON, ALTO_BOTON, FOCUSABLE,
				SIZE_FONT){
			
			public boolean trackwheelClick(int time, int status){
				
				return true;
			}
			
		});
		
		add(new ExButtonField("BICIS", Color.BLACK, Color.BLACK, Color.BLACK,
				Color.WHITE, Color.DARKGRAY, Color.DARKGRAY, ANCHO_BOTON, ALTO_BOTON, FOCUSABLE,
				SIZE_FONT){
			
			public boolean trackwheelClick(int time, int status){
				OnClickUtil.onClickBicis();
				return true;
			}
			
		});
		
		
		add(new ExButtonField("SERVICIOS", Color.BLACK, Color.BLACK, Color.BLACK,
				Color.WHITE, Color.DARKGRAY, Color.DARKGRAY, ANCHO_BOTON, ALTO_BOTON, FOCUSABLE,
				SIZE_FONT){
			
			public boolean trackwheelClick(int time, int status){
				return true;
			}
			
		});
	}

}
