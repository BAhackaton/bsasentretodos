package com.ba.field.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import net.rim.device.api.system.Alert;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;

import com.ba.domain.PuestoBicis;
import com.ba.field.field.ApplicationEvent;
import com.ba.field.field.PuestoBiciField;
import com.ba.util.Const;
import com.ba.util.MyTimerUtil;
import com.ba.util.Strings;
import com.ba.util.TimerListener;
import com.pegsoluciones.blackberry.common.event.EventManager;
import com.pegsoluciones.blackberry.common.event.EventObserver;
import com.pegsoluciones.blackberry.common.ui.field.Linea;
import com.pegsoluciones.blackberry.common.util.Location;

public class PuestosBicisManager extends BAManager implements TimerListener, EventObserver {
	
	private EditField countDown;
	private boolean reservado;
	private MyTimerUtil timer;
	InputStream is;
	byte[] tuneBytes;
	int i = 0;
	
	public PuestosBicisManager(Vector puestos, Location location) {		
		is = PuestoBiciField.class.getResourceAsStream("/alarma.mid");		
		try {
			tuneBytes = new byte[is.available()];
			is.read(tuneBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Alert.startMIDI(tuneBytes, true);
		reservado = false;
		countDown = new EditField("","Reserva no realizada");
		countDown.setMargin(2, 0, 2, 10);
		timer = new MyTimerUtil(PuestosBicisManager.this);		
		add(countDown);
		if(location != null){
			add(new EditField("","No se ha podido localizarlo, los puesto estan ordenados alfabeticamente"));
		}
		
		int size = puestos.size();
		for(int i = 0; i < size ; i++){
			PuestoBicis p = (PuestoBicis) puestos.elementAt(i);
			add(new PuestoBiciField(p));
			
			if(i < size -1 ){
				add(new Linea(Color.DARKGRAY,Color.WHITESMOKE,Display.getWidth(),10,NON_FOCUSABLE));
			}
		}
		
		EventManager.getInstance().addObserver(this, ApplicationEvent.RESERVAR);
	}
	
	public void timeChanged(long totalSeconds) {
		long remaindTime = Const.TIEMPO_RESERVA - totalSeconds;
		long seconds = remaindTime % 60;
		String secondsString = seconds < 10 ? ("0" + seconds) : "" + seconds;
		long minutos = remaindTime / 60;
		countDown.setText( minutos + ":" + secondsString);
		countDown.setLabel("Tiempo restante:");
		
		if(remaindTime == Const.TIEMPO_AVISO){
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				
				public void run() {
					Dialog.alert(Strings.POCO_TIEMPO);					
				}
			});
			Alert.startMIDI(tuneBytes, true);
			Alert.startVibrate(5000);
		}
		
		if(remaindTime == 0){
			reservado = false;
			timer.stop();			
			UiApplication.getUiApplication().invokeLater(new Runnable() {						
				public void run() {
					countDown.setText(Strings.RESERVA_FINALIZADA);
					countDown.setLabel("");
				}
			});
		}
	}

	public void onEvent(String topic, Object parameter) {	
		if(reservado)
			Dialog.alert(Strings.YA_RESERVO);				
		else{
			timer.startTimer();
			reservado = true;
		}
	}
	
}
