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
    
    List<OrganizationDetails> groupDetails;
    List<ChannelDetails> channelDetailsList;
    List<EmailBlockDetails> emailBlockDetailsList;

    public List<ChannelDetails> getChannelDetailsList() {
        return channelDetailsList;
    }

    public void setChannelDetailsList(List<ChannelDetails> channelDetailsList) {
        this.channelDetailsList = channelDetailsList;
    }

    public List<OrganizationDetails> getGroupDetails() {
        return groupDetails;
    }

    public void setGroupDetails(List<OrganizationDetails> groupDetails) {
        this.groupDetails = groupDetails;
    }

    public List<EmailBlockDetails> getEmailBlockDetailsList() {
        return emailBlockDetailsList;
    }

    public void setEmailBlockDetailsList(List<EmailBlockDetails> emailBlockDetailsList) {
        this.emailBlockDetailsList = emailBlockDetailsList;
    }
    
}
