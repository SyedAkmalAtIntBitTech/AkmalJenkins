/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;
import java.io.Serializable;

/**
 *
 * @author ajit
 */
public class SubCategoryDetails  extends UserCompanyIds implements Serializable{
    
    private Integer subCategoryId;
    private String subCategoryName;
    private Integer categoryId;
    private Integer externalSourceId;
    private Integer externalSourceKeywordId;
    private String externalSourceName;
    private String externalSourceKeywordName;
    private Integer externalSourceKeywordLookupId;

    public Integer getExternalSourceKeywordLookupId() {
        return externalSourceKeywordLookupId;
    }

    public void setExternalSourceKeywordLookupId(Integer externalSourceKeywordLookupId) {
        this.externalSourceKeywordLookupId = externalSourceKeywordLookupId;
    }
    
    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public String getExternalSourceName() {
        return externalSourceName;
    }

    public void setExternalSourceName(String externalSourceName) {
        this.externalSourceName = externalSourceName;
    }

    public String getExternalSourceKeywordName() {
        return externalSourceKeywordName;
    }

    public void setExternalSourceKeywordName(String externalSourceKeywordName) {
        this.externalSourceKeywordName = externalSourceKeywordName;
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
