package com.sling.models.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="aasish", description = "aasish")
public @interface SlingScheduler {

    @AttributeDefinition(name="Content path", type=AttributeType.STRING)
    public String contentPath() default "/content/Sling-Models";

    @AttributeDefinition(name="Cron Expression", type = AttributeType.STRING)
    public String cronExp();
    
}
