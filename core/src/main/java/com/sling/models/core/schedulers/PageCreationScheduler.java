package com.sling.models.core.schedulers;

import com.day.cq.wcm.api.Page;
import com.sling.models.core.services.MyPageService;
import com.sling.models.core.services.PageCreationService;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = PageCreationScheduler.class)
@Designate(ocd=PageCreationService.class)
public class PageCreationScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(PageCreationScheduler.class);

    public String contentPath;
    public int id;
    boolean autoSave= true;

    @Reference
    private Scheduler scheduler;

    @Reference
    private MyPageService myPageService;
    
    Page prodPage= null;

    @Activate
    protected void activate(PageCreationService config){
        id=config.schedulerName().hashCode();
        contentPath = config.contentPath();
    }

    @Modified
    protected void modified(PageCreationService config){
        removeScheduler();
        id=config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    public void deactivate(PageCreationService config){
        removeScheduler();
    }

    private void removeScheduler(){
        LOG.info("Removing scheduler: {}", id);
        scheduler.unschedule(String.valueOf(id));
    }

    private void addScheduler(PageCreationService config){

        if(config.enable()){
            ScheduleOptions scheduleOptions= scheduler.EXPR(config.cronExp());

            scheduleOptions.name(config.schedulerName());
            scheduleOptions.canRunConcurrently(false);
            scheduler.schedule(this, scheduleOptions);
        }
        else{
            LOG.info("Didn't get into if condition");
        }
    }

    @Override
    public void run() {
        LOG.info("inside run method"+contentPath);
        myPageService.createSamplePage(contentPath, "myScheduler", "//apps/testProject/templates/page-content", "myScheduler");
        
    }
    
}
