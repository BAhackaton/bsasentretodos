package com.ba.util;

public class Urls {

	public static final String PUESTOS = "http://pegsoluciones.com/puestosBicis.php";

	public static String reservar(String idUser, String idPuesto) {		
		String s = "http://pegsoluciones.com/reservarBici.php?idUser=" + idUser
			+ "&idPuesto=" + idPuesto;
		return s;
	}

}
