package com.ba.field.screen;

import java.util.Vector;

import com.ba.field.field.HeaderField;
import com.ba.field.manager.BarraManager;
import com.ba.field.manager.NoticiasManager;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.ui.screen.HomeScreenBase;

public class HomeScreen extends HomeScreenBase {

	public HomeScreen(Vector noticias) {
		add(new HeaderField());
		add(new BarraManager());
		
		NoticiasManager f;
		
		SimulaScreens.setScreen(this);		
		SimulaScreens.setInitialField(f = new NoticiasManager(noticias));
		add(f);
	}



	public String getMensajeSalida() {
		return null;
	}
	
}
