package com.sling.models.core.services.impl;

import com.day.cq.wcm.api.PageManager;
import com.sling.models.core.services.MyPageService;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = MyPageService.class)
public class MyPageServiceImpl implements MyPageService {

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    @SlingObject
	private ResourceResolver resourceResolver;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void createSamplePage(String contentPath, String title, String template, String name){

        try {
            log.info("inside try");
            resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
            PageManager createPage= resourceResolver.adaptTo(PageManager.class);
        
            createPage.create(contentPath, title, template, name);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        finally{
            resourceResolver.close();
        }
    }
    
}
