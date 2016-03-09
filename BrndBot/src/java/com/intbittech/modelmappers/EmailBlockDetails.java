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
public class EmailBlockDetails implements Serializable  {
    private Integer emailBlockId;
    private String emailBlockName;
    private Integer organizationId;
    private Integer externalSourceKeywordLookupId;

    public Integer getExternalSourceKeywordLookupId() {
        return externalSourceKeywordLookupId;
    }

    public void setExternalSourceKeywordLookupId(Integer externalSourceKeywordLookupId) {
        this.externalSourceKeywordLookupId = externalSourceKeywordLookupId;
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

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    
    
}
