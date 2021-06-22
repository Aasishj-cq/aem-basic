package com.sling.models.core.services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(immediate = true, service = CustomScheduler.class)
@Designate(ocd=SlingScheduler.class)
public class CustomScheduler {

    private String customPath;
    
    
}
