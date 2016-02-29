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
@Table(name = "email_model")
public class EmailModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_model_id")
    private Integer emailModelId;
    @Column(name = "email_model_name")
    private String emailModelName;
    @Column(name = "image_file_name")
    private String imageFileName;
    @Column(name = "html_data")
    private String htmlData;

    public EmailModel() {
    }

    public EmailModel(Integer emailModelId) {
        this.emailModelId = emailModelId;
    }

    public Integer getEmailModelId() {
        return emailModelId;
    }

    public void setEmailModelId(Integer emailModelId) {
        this.emailModelId = emailModelId;
    }

    public String getEmailModelName() {
        return emailModelName;
    }

    public void setEmailModelName(String emailModelName) {
        this.emailModelName = emailModelName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }

}
