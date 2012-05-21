package com.ba.service.impl;

import net.rim.device.api.ui.component.Dialog;

import com.ba.dao.NoticiasDao;
import com.ba.service.NoticiasService;
import com.pegsoluciones.blackberry.common.service.AbstractService;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;

public class NoticiasServiceImpl extends AbstractService implements
		NoticiasService {
	
	
	NoticiasDao noticias;

	public void setNoticias(NoticiasDao noticias) {
		this.noticias = noticias;
	}

	public void getNoticias(final ServiceExecutionListener listener) {
		run(new Runnable() {
			
			public void run() {				
				try {
					Thread.sleep(2000);
					complete(listener, noticias.getNoticias());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		});
	}

}
