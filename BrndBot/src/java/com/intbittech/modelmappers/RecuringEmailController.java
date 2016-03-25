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
public class RecuringEmailController
{
     private Integer recuringEmailTemplateId;
     private String htmlData;
     private String templateName;
     private Integer organizationRecuringEmailLookupId;
     private Organization fkOrganizationId;
     private RecuringEmailTemplate fkRecuringEmailTemplateId;

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

    public Organization getFkOrganizationId() {
        return fkOrganizationId;
    }

    public void setFkOrganizationId(Organization fkOrganizationId) {
        this.fkOrganizationId = fkOrganizationId;
    }

    public RecuringEmailTemplate getFkRecuringEmailTemplateId() {
        return fkRecuringEmailTemplateId;
    }

    public void setFkRecuringEmailTemplateId(RecuringEmailTemplate fkRecuringEmailTemplateId) {
        this.fkRecuringEmailTemplateId = fkRecuringEmailTemplateId;
    }

   
     
    
}
