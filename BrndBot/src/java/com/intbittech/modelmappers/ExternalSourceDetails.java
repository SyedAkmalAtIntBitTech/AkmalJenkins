/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;

/**
 *
 * @author ilyas
 */
public class ExternalSourceDetails implements Serializable{
    
    private Integer externalSourceId;
    private String externalSourceName;

    public Integer getExternalSourceId() {
        return externalSourceId;
    }

    public void setExternalSourceId(Integer externalSourceId) {
        this.externalSourceId = externalSourceId;
    }

    public String getExternalSourceName() {
        return externalSourceName;
    }

    public void setExternalSourceName(String externalSourceName) {
        this.externalSourceName = externalSourceName;
    }
    
}