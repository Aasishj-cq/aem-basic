package com.sling.models.core;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.sling.models.core.models.HelloWorldModel;

public class FooterBean {
	
	private static String test;
	private static final Logger LOG = LoggerFactory.getLogger(HelloWorldModel.class);

	@Self
	Resource resource;

	protected void init() {
		LOG.info("***inside init method****");
		PageManager pm=resource.getResourceResolver().adaptTo(PageManager.class);
		LOG.info("The pm is *******"+pm);
        Page page=pm.getContainingPage(resource);
        LOG.info("The page is *******"+page);
		test= page.getPath();
		LOG.info("The test is *******"+test);
	}

	
	public String getTest() {
		return test;
	}
	
}
