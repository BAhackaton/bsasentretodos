package com.ba.application;

import com.ba.dao.BicisDao;
import com.ba.dao.NoticiasDao;
import com.ba.dao.impl.BicisDaoImpl;
import com.ba.dao.impl.NoticiasDaoImpl;
import com.ba.service.BicisService;
import com.ba.service.NoticiasService;
import com.ba.service.impl.BiciServicesImpl;
import com.ba.service.impl.NoticiasServiceImpl;
import com.pegsoluciones.blackberry.common.cache.InMemoryCache;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionNotifier;
import com.pegsoluciones.blackberry.common.web.provider.WebserviceProvider;
import com.pegsoluciones.blackberry.common.xml.parsers.DocumentBuilderAdapter;



public class ApplicationFactory {

	private static ApplicationFactory INSTANCE;
		
	public static ApplicationFactory getInstance() {
		
		if(INSTANCE == null) {
			INSTANCE = new ApplicationFactory();
		}
		return INSTANCE;
	}
	
	/** Web service provider used by DAO layer */
	private WebserviceProvider serviceProvider;
	
	/** Interface wich notifies from service to UI */
	private ServiceExecutionNotifier serviceNotifier;
	
	/** Dom document builder */
	private DocumentBuilderAdapter documentBuilder;
	
	/** Cache for services*/
	private InMemoryCache cache;
	
	/**
	 * Sets a web service provider implementation
	 * @param serviceProvider
	 */
	public void setWebserviceProvider(WebserviceProvider serviceProvider) {
		
		this.serviceProvider = serviceProvider;
	}
	
	/**
	 * Set the service notifier
	 * @param notifier 
	 */
	public void setServiceNotifier(ServiceExecutionNotifier notifier) {
		
		this.serviceNotifier = notifier;
	}
	
	

	/**
	 * @param documentBuilder the documentBuilder to set
	 */
	public void setDocumentBuilder(DocumentBuilderAdapter documentBuilder) {
		this.documentBuilder = documentBuilder;
	}


	/**
	 * @param cache the cache to set
	 */
	public void setCache(InMemoryCache cache) {
		this.cache = cache;
	}
	
	public NoticiasDao getNoticiasDao(){
		NoticiasDaoImpl dao = new NoticiasDaoImpl();
		dao.setServiceProvider(serviceProvider);
		dao.setDocumentBuilder(documentBuilder);
		dao.setCache(cache);
		dao.setUseCache(true);
		return dao;
	}
	
	public NoticiasService getNoticiasService() {
		NoticiasServiceImpl service = new NoticiasServiceImpl();
		service.setServiceNotifier(serviceNotifier);
		service.setNoticias(getNoticiasDao());
		return service;
	}
	
	public BicisDao getBicisDao(){
		BicisDaoImpl dao = new BicisDaoImpl();
		dao.setServiceProvider(serviceProvider);
		dao.setDocumentBuilder(documentBuilder);
		dao.setCache(cache);
		dao.setUseCache(true);
		return dao;
	}
	
	public BicisService getBicisService() {
		BiciServicesImpl service = new BiciServicesImpl();
		service.setServiceNotifier(serviceNotifier);
		service.setDao(getBicisDao());
		return service;
	}

}
