/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Haider Khan @ Intbit
 */
public class GlobalImageDetails  extends UserCompanyIds implements Serializable{
    
    private Integer globalImageId;
    private String imageName;
    private String imageData;
    private String imageType;
    private Date createdDate;

    public Integer getGlobalImageId() {
        return globalImageId;
    }

    public void setGlobalImageId(Integer globalImagesId) {
        this.globalImageId = globalImagesId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    
    
    
    
    
    
}
