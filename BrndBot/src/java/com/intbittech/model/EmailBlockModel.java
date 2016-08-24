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
@Table(name = "email_block_model")
public class EmailBlockModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_block_model_id")
    private Integer emailBlockModelId;
    @Column(name = "email_block_model_name")
    private String emailBlockModelName;
    @Column(name = "image_file_name")
    private String imageFileName;
    @Column(name = "html_data")
    private String htmlData;
    @Column(name ="is_recurring")
    private boolean isRecurring;

    public EmailBlockModel() {
    }

    public EmailBlockModel(Integer emailBlockModelId) {
        this.emailBlockModelId = emailBlockModelId;
    }

    public Integer getEmailBlockModelId() {
        return emailBlockModelId;
    }

    public void setEmailBlockModelId(Integer emailBlockModelId) {
        this.emailBlockModelId = emailBlockModelId;
    }

    public String getEmailBlockModelName() {
        return emailBlockModelName;
    }

    public void setEmailBlockModelName(String emailBlockModelName) {
        this.emailBlockModelName = emailBlockModelName;
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

    public boolean isIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }
    
    
}
