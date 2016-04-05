/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

/**
 *
 * @author intbit
 */
public class MarketingCategoryProgramDetails extends MarketingProgramDetails{
    private Integer marketingCategoryProgramId;
    private Integer marketingCategoryId;

    public Integer getMarketingCategoryProgramId() {
        return marketingCategoryProgramId;
    }

    public void setMarketingCategoryProgramId(Integer marketingCategoryProgramId) {
        this.marketingCategoryProgramId = marketingCategoryProgramId;
    }

    public Integer getMarketingCategoryId() {
        return marketingCategoryId;
    }

    public void setMarketingCategoryId(Integer marketingCategoryId) {
        this.marketingCategoryId = marketingCategoryId;
    }

}
