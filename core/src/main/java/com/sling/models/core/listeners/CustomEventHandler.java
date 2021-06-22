package com.sling.models.core.listeners;

import org.osgi.service.event.EventHandler;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component(service= EventHandler.class, immediate=true, property= {
		Constants.SERVICE_DESCRIPTION + "=This event handler listens the events on page activation",
				EventConstants.EVENT_TOPIC+ "=org/apache/sling/api/resource/Resource/ADDED",
				EventConstants.EVENT_TOPIC+ "=org/apache/sling/api/resource/Resource/CHANGED",
				EventConstants.EVENT_FILTER+ "(&" + "(path=/content/Sling-Models/*/jcr:content) (|("
						+ SlingConstants.PROPERTY_CHANGED_ATTRIBUTES + "=*jcr:title) " + "(" + ResourceChangeListener.CHANGES
						+ "=*jcr:title)))"
})

public class CustomEventHandler implements EventHandler{
	
	private static final Logger log = LoggerFactory.getLogger(CustomEventHandler.class);
	
	
	
	public void handleEvent(Event event) {
		log.info("event is : {}", event.getTopic());
		
	}

}
