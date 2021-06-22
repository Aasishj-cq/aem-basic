package com.sling.models.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.sling.models.core.models.CustomMulti;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables=Resource.class, adapters = CustomMulti.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomMultiImpl implements CustomMulti{

    private static final Logger log= LoggerFactory.getLogger(CustomMultiImpl.class);
   
    @Inject
    Resource componentResource;

    @ValueMapValue
    private List<String> products;

    @Override
    public List<String> getProducts(){
        log.info("inside getProducts method");
        if(products != null){
            log.info("inside if");
            return new ArrayList<String>(products);
        }else{
            log.info("inside else");
            return Collections.emptyList();
        }

    }
}
