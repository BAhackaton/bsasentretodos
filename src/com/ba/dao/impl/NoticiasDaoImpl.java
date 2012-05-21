package com.ba.dao.impl;

import java.util.Vector;

import com.ba.dao.NoticiasDao;
import com.ba.domain.Noticia;
import com.pegsoluciones.blackberry.common.dao.AbstractDao;

public class NoticiasDaoImpl extends AbstractDao implements NoticiasDao {

	public Vector getNoticias() {
		Noticia n = new Noticia();
		Vector v = new Vector();
		n.setDetalle("El evento del año, en el CMD, estan todos invitados");
		n.setTitulo("BAhackacton");
		n.setUbicacion("CMO BSAS");
		n.setImagen("http://profile.ak.fbcdn.net/hprofile-ak-snc4/373033_113686738612_663943118_q.jpg");
		
		
		for(int i = 0 ; i < 10 ; i++){
			v.addElement(n);
		}
		
		return v;
	}

}
