/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;

/**
 *
 * @author development
 */
public class ImageModelDetails implements Serializable {
    
    private Integer subCategoryImageModelId;
    private Integer imageModelId;
    private String imageModelName;
    private String layoutFileName;
    private String modelFileName;
    private String imageFileName;

    private Integer subCategoryId;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
        
    public String getLayoutFileName() {
        return layoutFileName;
    }

    public void setLayoutFileName(String layoutFileName) {
        this.layoutFileName = layoutFileName;
    }

    public String getModelFileName() {
        return modelFileName;
    }

    public void setModelFileName(String modelFileName) {
        this.modelFileName = modelFileName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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
