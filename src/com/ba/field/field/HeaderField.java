package com.ba.field.field;

import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class HeaderField extends Field {

	public static final int ALTO = 30;

	protected void layout(int width, int height) {
		setExtent(Display.getWidth(), ALTO);
	}

	protected void paint(Graphics g) {
		g.setBackgroundColor(Color.YELLOW);
		g.clear();
		
		g.setColor(Color.WHITE);
		
		g.drawText("GCBA", 10, PosicionesUtil.centrada(g.getFont().getHeight(), ALTO));
	}

}
