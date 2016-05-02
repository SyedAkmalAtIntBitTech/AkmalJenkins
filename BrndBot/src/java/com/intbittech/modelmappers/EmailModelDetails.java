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
    
    private Integer modelId;
    private Integer emailModelId;
    private String emailModelName;
    private String imageFileName;
    private String htmlData;  
    private String imageFileData;
    private Integer subCategoryId;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
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

    
    
    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
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

    public String getImageFileData() {
        return imageFileData;
    }

    public void setImageFileData(String imageFileData) {
        this.imageFileData = imageFileData;
    }
     
     
    
}
