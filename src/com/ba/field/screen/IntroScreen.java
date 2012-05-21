package com.ba.field.screen;

import java.util.Vector;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;

import com.ba.application.ApplicationFactory;
import com.ba.service.NoticiasService;
import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.util.BitmapUtil;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;


public final class IntroScreen extends MainScreen implements ServiceExecutionListener{

    private Bitmap logo;

	public IntroScreen(){        
    	NoticiasService noticiasService = ApplicationFactory.getInstance().getNoticiasService();
    	noticiasService.getNoticias(this);
    	logo = BitmapUtil.getImagen("intro.png");
    }
    
    public void paint(Graphics g){
    	g.setBackgroundColor(16765954);
    	g.clear();
    	GraphicUtil.drawBitmap(g, logo,PosicionesUtil.xCentrada(logo) , PosicionesUtil.yCentrada(logo));
    }

	public void onCallComplete(Object parameters) {
		ScreenUtil.pushScreen(new HomeScreen((Vector)parameters));
	}

	public void onError(ServiceError error) {
		Dialog.alert("Error");
	}
}
