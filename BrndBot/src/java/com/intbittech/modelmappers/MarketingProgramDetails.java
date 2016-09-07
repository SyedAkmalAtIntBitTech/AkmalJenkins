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
public class MarketingProgramDetails  extends UserCompanyIds implements Serializable{
    private Integer marketingProgramId;
    private String marketingProgramName;
    private String htmlData;

    public Integer getMarketingProgramId() {
        return marketingProgramId;
    }

    public void setMarketingProgramId(Integer marketingProgramId) {
        this.marketingProgramId = marketingProgramId;
    }

    public String getMarketingProgramName() {
        return marketingProgramName;
    }

    public void setMarketingProgramName(String marketingProgramName) {
        this.marketingProgramName = marketingProgramName;
    }

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }
    
}
