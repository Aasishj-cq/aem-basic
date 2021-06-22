package com.sling.models.core.models.impl;

import com.sling.models.core.models.TestComponent;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = TestComponent.class)
public class TestComponentImpl implements TestComponent{

    public String getName(){
        return "Aasish";
    }
    
}
