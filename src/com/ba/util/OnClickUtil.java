package com.ba.util;

import java.util.Vector;

import com.ba.application.ApplicationFactory;
import com.ba.domain.PuestoBicis;
import com.ba.field.manager.PuestosBicisManager;
import com.ba.gps.LocationProvider;
import com.ba.service.BicisService;
import com.pegsoluciones.blackberry.common.screenEnManager.SimulaScreens;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.util.Location;

public class OnClickUtil {

	public static void onClickBicis() {
		
		final Location location = LocationProvider.getLocation();
		
		BicisService bicisService = ApplicationFactory.getInstance().getBicisService();
		bicisService.getPuestosBicis(new ServiceExecutionListener() {
			
			public void onError(ServiceError error) {
				
			}
			
			public void onCallComplete(Object p) {			
				SimulaScreens.pushManager(new PuestosBicisManager((Vector)p,location));
			}
		}, location);
	}

}
