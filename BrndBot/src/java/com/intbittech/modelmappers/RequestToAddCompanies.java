/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;

/**
 *
 * @author Sandeep Kumar at IntBit Technologies.
 */
public class RequestToAddCompanies extends UserCompanyIds {
    private String companyNames;
    private String franchiseName;

    public String getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(String companyNames) {
        this.companyNames = companyNames;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }
    
    
}
