/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;

/**
 *
 * @author ajit @ Intbit
 */
public class CompanyDetailsForFranchise implements Serializable {

    private Integer companyId;
    private String companyName;
    private Boolean isEmailList;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getIsEmailList() {
        return isEmailList;
    }

    public void setIsEmailList(Boolean isEmailList) {
        this.isEmailList = isEmailList;
    }

}
