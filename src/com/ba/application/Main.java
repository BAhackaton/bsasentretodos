package com.ba.application;

import com.pegsoluciones.blackberry.common.application.BlackberryApplication;
import com.pegsoluciones.blackberry.common.cache.InMemoryCache;
import com.pegsoluciones.blackberry.common.service.UIServiceExecutionNotifier;
import com.pegsoluciones.blackberry.common.ui.ScreenInfo;
import com.pegsoluciones.blackberry.common.web.provider.RimWebserviceProvider;
import com.pegsoluciones.blackberry.common.xml.parsers.RimDocumentBuilder;




public class Main extends BlackberryApplication {

	
    public static void main(String[] args){
    	
    	Main main = new Main();
    	ScreenInfo.setResDir("image/");
    	main.start(BAApplication.class);
    }
    

    protected void setUp() {
		
		// starts the application factory and set
		// dependencies as well as implementation
		// like web service providers.
		// Access to internet using RIM provided classes 
		ApplicationFactory.getInstance().setWebserviceProvider(new RimWebserviceProvider());
		
		// Service layer will notify UI with this solution
		// quite different from testing environment
		ApplicationFactory.getInstance().setServiceNotifier(new UIServiceExecutionNotifier());
		
		// Use RIM implementation for parsing 
		// xml data
		ApplicationFactory.getInstance().setDocumentBuilder(new RimDocumentBuilder());
		
		// Set cache for DAO
		ApplicationFactory.getInstance().setCache(new InMemoryCache());
		
	}

}
