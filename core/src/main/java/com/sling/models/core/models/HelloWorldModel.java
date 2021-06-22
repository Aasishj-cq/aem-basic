
package com.sling.models.core.models;

import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;


@Model(adaptables=Resource.class)
public class HelloWorldModel {
	private static final Logger LOG = LoggerFactory.getLogger(HelloWorldModel.class);

	
    @Inject @Optional
    private String message;
    
    @Inject @Optional
    private String headline;
   
    @Inject @Optional
    private String linkUrl;
    
    @Inject @Optional
    private String linkText;

	@Inject @Optional
    private String fileReference;
	
	@Inject
	private SlingSettingsService slingSettings;
	
	private String runModes;
	private String[] vanity;
	
	@Self
	Resource resource;



	@PostConstruct
    protected void init() {
 
        message=this.getMessage();
        headline=this.getHeadline();
        linkUrl=this.getLinkUrl();
        linkText=this.getLinkText();
        fileReference=this.getFileReference();
        
        this.setMessage(message);
        this.setHeadline(headline);
        this.setLinkUrl(linkUrl);
        this.setLinkText(linkText);
        this.setFileReference(fileReference);
        
        runModes= slingSettings.getRunModes().toString();
        
        PageManager pm=resource.getResourceResolver().adaptTo(PageManager.class);
        Page containingPage=pm.getContainingPage(resource);
        ValueMap pageProps =containingPage.getProperties();
        vanity = (String[]) pageProps.get("sling:vanityPath");
        
        
//        ValueMap properties = res.adaptTo(ValueMap.class);
//        LOG.info("The properties is $$$$$$$ "+ properties );
//        String vanity=(String) properties.get("sling:vanityPath");
//        LOG.info("The vanity is $$$$$$$ "+ vanity );
        
        //List<HelloWorldModel> childs=new ArrayList<>();
        if(containingPage!=null) {
        Iterator<Page> children= containingPage.listChildren();     
        while(children.hasNext()) {
        	Page childPages=children.next();
        	LOG.info("The childPages are %%%%" +childPages.getPath());
        	//LOG.info("The child Pages are %%%%" +children.next().getPath());
        	
        }
        
        }
        
	}
    
    

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
		this.message = message;
	}



	public void setHeadline(String headline) {
		this.headline = headline;
	}

    public void setLinkText(String linkText) {
		this.linkText = linkText;
	}


	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getFileReference() {
		return fileReference;
	}

	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}


	public String getHeadline() {
    	return headline;
    }
    public String getLinkUrl() {
    	return linkUrl;
    }

    public String getLinkText() {
		return linkText;
	}



	public String getRunModes() {
		return runModes;
	}



	public String[] getVanity() {
		return vanity;
	}

    
}