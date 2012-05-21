package com.ba.field.field;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.Dialog;

import com.ba.application.ApplicationFactory;
import com.ba.domain.PuestoBicis;
import com.ba.field.manager.BarraManager;
import com.ba.service.BicisService;
import com.ba.util.Strings;
import com.pegsoluciones.blackberry.common.device.DeviceUtil;
import com.pegsoluciones.blackberry.common.event.EventManager;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.field.ExBitmapField;
import com.pegsoluciones.blackberry.common.util.GoogleMapsUtil;

public class PuestoBiciField extends Field implements ServiceExecutionListener{

	private static final int ALTO = 70;
	private static final int MARGEN_Y = 10;
	private static final int MARGEN_X = 10;
	private PuestoBicis p;
	private int colorFondo = Color.WHITE;
	

	public PuestoBiciField(PuestoBicis p) {
		super(FOCUSABLE);
		this.p = p;		
	}

	protected void layout(int width, int height) {
		setExtent(Display.getWidth(), ALTO);
	}
	
	

	protected void paint(Graphics g) {		
		g.setBackgroundColor(colorFondo);
		g.clear();
		
		g.setFont(Font.getDefault().derive(Font.PLAIN, 15));
		
		g.drawText(p.getNombre() + " - " + p.getDireccion(), MARGEN_X, MARGEN_Y);
		int y = MARGEN_Y + g.getFont().getHeight();
		if(p.getBicisDisponibles().equals("0")){
			g.drawText("Tiempo de espera: " + p.getTiempoEspera(), MARGEN_X, 
					y);
		}else{
			g.drawText("Bicis disponibles: " + p.getBicisDisponibles(), MARGEN_X, 
					y);
		}
		
		
		y +=  g.getFont().getHeight();
		int bicis = Integer.parseInt(p.getBicisDisponibles());
		g.drawText("Bicis disponibles reserva: " + (int)( bicis * 0.3), MARGEN_X,y);
		
		if(p.getDistancia() != null)
			g.drawText(p.getDistancia(),Display.getWidth() - MARGEN_X - 
					g.getFont().getAdvance(p.getDistancia()), y);
	}
	
	protected boolean trackwheelClick(int status, int time) {
		if(p.getBicisDisponibles().equals("0")){
			Dialog.alert("No hay bicis disponible, intente hacer la reserva mas tarde");
		}else{
			int rta = Dialog.ask(Strings.DESEA_RESERVAR,Strings.SI_NO,0);
			if(rta == 0){
				BicisService bicisService = ApplicationFactory.getInstance().getBicisService();
				bicisService.reservar(this,  "2009", p.getId() + "");				
			}else if(rta == 2){
				int altoMapa = Display.getHeight() - HeaderField.ALTO - BarraManager.ALTO_BOTON;
				String url = GoogleMapsUtil.getUrlMapaConPin(p.getLocation().lat + "",
						p.getLocation().lng + "", 14, Display.getWidth(), altoMapa, 'B');
				SimulaScreens.pushManager(new ExBitmapField(url));
			}					
		}
		
		return true;
	}

	
	protected void onFocus(int direction) {		
		colorFondo = Color.LIGHTGRAY;
		super.onFocus(direction);
	}

	protected void onUnfocus() {
		colorFondo = Color.WHITE;
		super.onUnfocus();
		invalidate();
	}

	public void onCallComplete(Object parameters) {
		Boolean b = (Boolean) parameters;
		if(b.booleanValue())
			EventManager.getInstance().notify(ApplicationEvent.RESERVAR, null);
		else
			Dialog.alert(Strings.RESERVAR_ERROR);
	}

	public void onError(ServiceError error) {
		Dialog.alert(Strings.RESERVAR_ERROR);
	}

}
