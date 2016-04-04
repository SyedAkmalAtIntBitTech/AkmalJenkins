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
public class OrganizationCompanyDetails extends OrganizationDetails implements Serializable{
    
    private Integer organizationCompanyLookupId;

    public Integer getOrganizationCompanyLookupId() {
        return organizationCompanyLookupId;
    }

    public void setOrganizationCompanyLookupId(Integer organizationCompanyLookupId) {
        this.organizationCompanyLookupId = organizationCompanyLookupId;
    }
    
}
