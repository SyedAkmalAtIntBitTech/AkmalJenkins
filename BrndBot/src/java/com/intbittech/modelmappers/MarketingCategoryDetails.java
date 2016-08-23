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
 * @author intbit
 */
public class MarketingCategoryDetails  extends UserCompanyIds implements Serializable{
    private Integer marketingCategoryId;
    private String marketingCategoryName;
    private Integer organizationId;

    public Integer getMarketingCategoryId() {
        return marketingCategoryId;
    }

    public void setMarketingCategoryId(Integer marketingCategoryId) {
        this.marketingCategoryId = marketingCategoryId;
    }

    public String getMarketingCategoryName() {
        return marketingCategoryName;
    }

    public void setMarketingCategoryName(String marketingCategoryName) {
        this.marketingCategoryName = marketingCategoryName;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    
    
}
