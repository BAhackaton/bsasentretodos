package com.ba.dao.impl;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ba.dao.BicisDao;
import com.ba.domain.PuestoBicis;
import com.ba.util.Urls;
import com.pegsoluciones.blackberry.common.dao.AbstractDao;
import com.pegsoluciones.blackberry.common.util.Location;

public class BicisDaoImpl extends AbstractDao implements BicisDao {

	private static final String PUESTO = "puesto";
	private static final String ID = "id";
	private static final String NOMBRE = "nombre";
	private static final String DIRECCION = "direccion";
	private static final String DISPONIBLES = "disponible";
	private static final String LATITUD = "latitud";
	private static final String LONGITUD = "longitud";

	public Vector getPuestosBicis(Location location) {
		Vector v = new Vector();
		Document document = this.loadResource(Urls.PUESTOS);			
		
		Node root = document.getFirstChild();
		NodeList items = root.getChildNodes();

		for (int i=0;i<items.getLength();i++){
			Node item = items.item(i);				
			String name  = item.getNodeName();

			if(name.equals(PUESTO)){
				NodeList caracteristicas = item.getChildNodes();
				PuestoBicis p = new PuestoBicis();
				for(int k = 0 ; k < caracteristicas.getLength(); k++){
					Node caracteristica = caracteristicas.item(k);
					name = caracteristica.getNodeName();
					if(name.equals("#text"))
						continue;
					
					String value = caracteristica.getFirstChild().getNodeValue();
					
					if(name.equals(ID)){
						p.setId(value);
					}else if(name.equals(NOMBRE)){
						p.setNombre(value);
					}else if(name.equals(DIRECCION)){
						p.setDireccion(value);
					}else if(name.equals(DISPONIBLES)){
						p.setBicisDisponibles(value);
					}else if(name.equals(LATITUD)){
						p.setLatitud(value);
					}else if(name.equals(LONGITUD)){
						p.setLongitud(value);
					}
				}
				v.addElement(p);
			}			
			
		}

		return v;
	}

	public Boolean reservar(String idUser, String idPuesto) {		
		Document document = this.loadResource(Urls.reservar(idUser,idPuesto));			
		
		Node root = document.getFirstChild();				
		String rta = root.getFirstChild().getNodeValue();
		
		return new Boolean(rta.equals("ok"));
	}

}
