package com.ba.service;

import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.util.Location;

public interface BicisService {
	
	public void getPuestosBicis(ServiceExecutionListener listener,Location location);
	public void reservar(ServiceExecutionListener listener,String idUser, String idPuesto);

}
