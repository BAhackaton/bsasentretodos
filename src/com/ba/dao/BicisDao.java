package com.ba.dao;

import java.util.Vector;

import com.pegsoluciones.blackberry.common.util.Location;

public interface BicisDao {

		public Vector getPuestosBicis(Location location);

		public Boolean reservar(String idUser, String idPuesto);
	
}
