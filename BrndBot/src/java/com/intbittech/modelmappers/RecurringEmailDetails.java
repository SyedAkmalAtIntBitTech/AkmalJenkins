/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.Organization;
import com.intbittech.model.RecuringEmailTemplate;

/**
 *
 * @author sandeep-kumar
 */
public class RecurringEmailDetails
{
    
     private Integer recuringEmailTemplateId;
     private String htmlData;
     private String templateName;
     private Integer organizationRecuringEmailLookupId;
     private Integer OrganizationId;
    

    public Integer getRecuringEmailTemplateId() {
        return recuringEmailTemplateId;
    }

    public void setRecuringEmailTemplateId(Integer recuringEmailTemplateId) {
        this.recuringEmailTemplateId = recuringEmailTemplateId;
    }

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getOrganizationRecuringEmailLookupId() {
        return organizationRecuringEmailLookupId;
    }

    public void setOrganizationRecuringEmailLookupId(Integer organizationRecuringEmailLookupId) {
        this.organizationRecuringEmailLookupId = organizationRecuringEmailLookupId;
    }

    public Integer getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(Integer OrganizationId) {
        this.OrganizationId = OrganizationId;
    }

   
    
}
