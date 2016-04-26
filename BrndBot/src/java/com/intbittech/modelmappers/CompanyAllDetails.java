/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ilyas
 */
public class CompanyAllDetails extends CompanyDetails  implements Serializable {
    
    List<OrganizationCompanyDetails> groupDetails;
    List<ChannelDetails> channelDetailsList;
    List<EmailBlockDetails> emailBlockDetailsList;
    List<MarketingCategoryDetails> marketingCategoryDetailsList;

    public List<ChannelDetails> getChannelDetailsList() {
        return channelDetailsList;
    }

    public void setChannelDetailsList(List<ChannelDetails> channelDetailsList) {
        this.channelDetailsList = channelDetailsList;
    }

    public List<OrganizationCompanyDetails> getGroupDetails() {
        return groupDetails;
    }

    public void setGroupDetails(List<OrganizationCompanyDetails> groupDetails) {
        this.groupDetails = groupDetails;
    }

    public List<EmailBlockDetails> getEmailBlockDetailsList() {
        return emailBlockDetailsList;
    }

    public void setEmailBlockDetailsList(List<EmailBlockDetails> emailBlockDetailsList) {
        this.emailBlockDetailsList = emailBlockDetailsList;
    }

    public List<MarketingCategoryDetails> getMarketingCategoryDetailsList() {
        return marketingCategoryDetailsList;
    }

    public void setMarketingCategoryDetailsList(List<MarketingCategoryDetails> marketingCategoryDetailsList) {
        this.marketingCategoryDetailsList = marketingCategoryDetailsList;
    }
    
}
