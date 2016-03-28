/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.util.List;

/**
 *
 * @author ilyas
 */
public class CompanyAllDetails extends CompanyDetails{
    
    List<OrganizationDetails> groupDetails;

    public List<OrganizationDetails> getGroupDetails() {
        return groupDetails;
    }

    public void setGroupDetails(List<OrganizationDetails> groupDetails) {
        this.groupDetails = groupDetails;
    }
    
}
