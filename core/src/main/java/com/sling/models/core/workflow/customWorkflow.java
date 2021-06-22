package com.sling.models.core.workflow;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WorkflowProcess.class, property = {"process.label = Custom Workflow"})
public class customWorkflow implements WorkflowProcess{

    private static final Logger LOG = LoggerFactory.getLogger(customWorkflow.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException{
        LOG.info("Executing Workflow");

        try{
            String textValue= metaDataMap.get("textValue", "empty");
            String dateValue= metaDataMap.get("dateValue", "empty");

            LOG.info("Text : {}", textValue);
            LOG.info("Date : {}", dateValue);

        }
        catch(Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
    
}
