/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public class RecurringEmailTemplateModel extends UserCompanyIds{
    private Integer recurringEmailTemplateId;
    private String htmlData;
    private String templateName;

    public Integer getRecurringEmailTemplateId() {
        return recurringEmailTemplateId;
    }

    public void setRecurringEmailTemplateId(Integer recurringEmailTemplateId) {
        this.recurringEmailTemplateId = recurringEmailTemplateId;
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
    
    
}
