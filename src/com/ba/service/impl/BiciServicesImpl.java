package com.ba.service.impl;

import com.ba.dao.BicisDao;
import com.ba.service.BicisService;
import com.pegsoluciones.blackberry.common.service.AbstractService;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.util.Location;

public class BiciServicesImpl extends AbstractService implements BicisService {

	private BicisDao dao;
	
	public void getPuestosBicis(final ServiceExecutionListener listener,Location location) {
		try {
			complete(listener, dao.getPuestosBicis(location));
		} catch (Exception e) {

		}
	}

	public void setDao(BicisDao dao) {
		this.dao = dao;
	}

	public void reservar(final ServiceExecutionListener listener,final String idUser,final String idPuesto) {
		try {
			complete(listener, dao.reservar(idUser,idPuesto));
		} catch (Exception e) {

		}	
	}

}
