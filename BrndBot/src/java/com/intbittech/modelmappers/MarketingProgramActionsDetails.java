/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.util.List;


/**
 *
 * @author intbit
 */
public class MarketingProgramActionsDetails extends MarketingProgramDetails{
    
    
    private String htmlData;
    private Integer marketingActionId;
    private List<MarketingActionDetails> marketingActions;

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }

    public Integer getMarketingActionId() {
        return marketingActionId;
    }

    public void setMarketingActionId(Integer marketingActionId) {
        this.marketingActionId = marketingActionId;
    }

    public List<MarketingActionDetails> getMarketingActions() {
        return marketingActions;
    }

    public void setMarketingActions(List<MarketingActionDetails> marketingActions) {
        this.marketingActions = marketingActions;
    }
    
    
    
    
    
}
