/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

/**
 *
 * @author development
 */
public class ImageModelDetails {
    
    private Integer subCategoryImageModelId;
    private Integer imageModelId;
    private String imageModelName;

    private Integer subCategoryId;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
    
    public Integer getSubCategoryImageModelId() {
        return subCategoryImageModelId;
    }

    public void setSubCategoryImageModelId(Integer subCategoryImageModelId) {
        this.subCategoryImageModelId = subCategoryImageModelId;
    }

    public Integer getImageModelId() {
        return imageModelId;
    }

    public void setImageModelId(Integer imageModelId) {
        this.imageModelId = imageModelId;
    }

    public String getImageModelName() {
        return imageModelName;
    }

    public void setImageModelName(String imageModelName) {
        this.imageModelName = imageModelName;
    }
    
    
    
    
    
    
}
