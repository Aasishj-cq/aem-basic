package com.sling.models.core.models;

import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables =Resource.class)
public class TextImage {
	
	private static final Logger LOG = LoggerFactory.getLogger(TextImage.class);

	@Inject @Optional
	private String title;

	@Optional
	private String childTitle;

	@Self
	Resource resource;

	@SlingObject
	private ResourceResolver resourceResolver;

	//ResourceResolver resourceResolver=null;
	
	@PostConstruct
	protected void init() {
		title =this.getTitle();
		this.setTitle(title);

		PageManager pageManager=resource.getResourceResolver().adaptTo(PageManager.class);
		if(pageManager.getContainingPage(resource) !=null){

			Page pageReq=pageManager.getContainingPage(resource);
			LOG.info("The pageReq is : +"+pageReq.getPath());
			
			ValueMap properties = resource.adaptTo(ValueMap.class);
			LOG.info("The properties are : +"+properties.toString());
			ValueMap props= resource.getValueMap();
			LOG.info("The props are: +"+props.toString());


			}
			if(pageManager.getContainingPage(resource).listChildren() !=null){
				Iterator<Page> childPages = pageManager.getContainingPage(resource).listChildren();
				while(childPages.hasNext()){
					childTitle= childPages.next().getName();
					//LOG.info("The title of the pages are :"+childPages.next().getName());
				}
			}
		}
	
	public String getChildTitle() {
		return childTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
