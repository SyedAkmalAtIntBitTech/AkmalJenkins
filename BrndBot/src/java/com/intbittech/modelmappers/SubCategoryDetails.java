/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;

/**
 *
 * @author ajit
 */
public class SubCategoryDetails implements Serializable{
    
    private Integer subCategoryId;
    private String subCategoryName;
    private Integer categoryId;
    private Integer externalSourceId;
    private Integer externalSourceKeywordId;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getExternalSourceId() {
        return externalSourceId;
    }

    public void setExternalSourceId(Integer externalSourceId) {
        this.externalSourceId = externalSourceId;
    }

    public Integer getExternalSourceKeywordId() {
        return externalSourceKeywordId;
    }

    public void setExternalSourceKeywordId(Integer externalSourceKeywordId) {
        this.externalSourceKeywordId = externalSourceKeywordId;
    }
    
    
    
}
