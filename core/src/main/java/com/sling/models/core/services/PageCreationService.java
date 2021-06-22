package com.sling.models.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name ="PageCreationScheduler", description = "Configuration for creating a page")
public @interface PageCreationService {

    @AttributeDefinition(name ="Scheduler name", type = AttributeType.STRING)
    public String schedulerName() default "My Scheduler";

    @AttributeDefinition(name ="Content path", type = AttributeType.STRING)
    public String contentPath() default "/content/Sling-Models/en";

    @AttributeDefinition(name ="Cron Expression", type= AttributeType.STRING)
    public String cronExp() default "0 * * * * ?";

    @AttributeDefinition(name ="Enable", type= AttributeType.BOOLEAN)
    public boolean enable() default false;

}
