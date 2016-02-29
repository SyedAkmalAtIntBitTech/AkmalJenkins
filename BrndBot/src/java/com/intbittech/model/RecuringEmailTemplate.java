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
@Table(name = "recuring_email_template")
public class RecuringEmailTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recuring_email_template_id")
    private Integer recuringEmailTemplateId;
    @Column(name = "html_data")
    private String htmlData;
    @Column(name = "template_name")
    private String templateName;

    public RecuringEmailTemplate() {
    }

    public RecuringEmailTemplate(Integer recuringEmailTemplateId) {
        this.recuringEmailTemplateId = recuringEmailTemplateId;
    }

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
}
