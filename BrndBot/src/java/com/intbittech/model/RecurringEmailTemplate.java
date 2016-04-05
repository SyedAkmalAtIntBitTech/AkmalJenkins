/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "recurring_email_template")
public class RecurringEmailTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recurring_email_template_id")
    private Integer recurringEmailTemplateId;
    @Column(name = "html_data")
    private String htmlData;
    @Column(name = "template_name")
    private String templateName;

    public RecurringEmailTemplate() {
    }

    public RecurringEmailTemplate(Integer recurringEmailTemplateId) {
        this.recurringEmailTemplateId = recurringEmailTemplateId;
    }

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
