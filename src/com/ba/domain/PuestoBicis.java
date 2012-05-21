package com.ba.domain;

import com.pegsoluciones.blackberry.common.util.Location;

public class PuestoBicis {

	private String nombre;
	private String direccion;
	private String bicisDisponibles;
	private String bicisDisponiblesReserva;
	private String tiempoEspera;
	private String distancia;
	private Location location;
	private String id;
	
	public PuestoBicis() {
		location = new Location();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getBicisDisponibles() {
		return bicisDisponibles;
	}
	public void setBicisDisponibles(String bicisDisponibles) {
		this.bicisDisponibles = bicisDisponibles;
	}
	public String getTiempoEspera() {
		return tiempoEspera;
	}
	public void setTiempoEspera(String tiempoEspera) {
		this.tiempoEspera = tiempoEspera;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
	public String getDistancia() {
		return distancia;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setLatitud(String lat) {
		location.lat = Double.parseDouble(lat);
	}

	public void setLongitud(String lng) {
		location.lng = Double.parseDouble(lng);
	}

	public void setBicisDisponiblesReserva(String bicisDisponiblesReserva) {
		this.bicisDisponiblesReserva = bicisDisponiblesReserva;
	}

	public String getBicisDisponiblesReserva() {
		return bicisDisponiblesReserva;
	}
	
}
