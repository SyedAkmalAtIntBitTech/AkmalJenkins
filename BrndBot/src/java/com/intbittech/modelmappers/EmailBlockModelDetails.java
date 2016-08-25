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
 * @author ilyas
 */
public class EmailBlockModelDetails  extends UserCompanyIds implements Serializable{
    
    private Integer emailBlockModelLookupId;
    private Integer emailBlockId;
    private String emailBlockName;
    private Integer modelId;
    private String emailBlockModelName;
    private String imageFileName;
    private String imageFileData;
    private String htmlData;
    private boolean isRecurring;

    public boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }
    
    public Integer getEmailBlockModelLookupId() {
        return emailBlockModelLookupId;
    }

    public void setEmailBlockModelLookupId(Integer emailBlockModelLookupId) {
        this.emailBlockModelLookupId = emailBlockModelLookupId;
    }

    public Integer getEmailBlockId() {
        return emailBlockId;
    }

    public void setEmailBlockId(Integer emailBlockId) {
        this.emailBlockId = emailBlockId;
    }

    public String getEmailBlockName() {
        return emailBlockName;
    }

    public void setEmailBlockName(String emailBlockName) {
        this.emailBlockName = emailBlockName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
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

    public String getImageFileData() {
        return imageFileData;
    }

    public void setImageFileData(String imageFileData) {
        this.imageFileData = imageFileData;
    }
    
    
    
}
