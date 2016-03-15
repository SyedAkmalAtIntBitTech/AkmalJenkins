/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;
/**
 *
 * @author Akmal
 */
public class EmailModelDetails implements Serializable{
    
    private Integer subCategoryEmailModelId;
    private Integer emailModelId;
    private String emailModelName;

    public Integer getSubCategoryEmailModelId() {
        return subCategoryEmailModelId;
    }

    public void setSubCategoryEmailModelId(Integer subCategoryEmailModelId) {
        this.subCategoryEmailModelId = subCategoryEmailModelId;
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
     
     
    
}
