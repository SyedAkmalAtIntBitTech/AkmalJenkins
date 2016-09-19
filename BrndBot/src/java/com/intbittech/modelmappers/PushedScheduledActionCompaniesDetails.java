/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.util.List;

/**
 *
 * @author ajit @ Intbit
 */
public class PushedScheduledActionCompaniesDetails {
    
    private PushedScheduledEntityDetails pushedScheduledEntityDetails;
    private List<ActionCompaniesDetails> actionCompaniesDetails;

    public PushedScheduledEntityDetails getPushedScheduledEntityDetails() {
        return pushedScheduledEntityDetails;
    }

    public void setPushedScheduledEntityDetails(PushedScheduledEntityDetails pushedScheduledEntityDetails) {
        this.pushedScheduledEntityDetails = pushedScheduledEntityDetails;
    }

    public List<ActionCompaniesDetails> getActionCompaniesDetails() {
        return actionCompaniesDetails;
    }

    public void setActionCompaniesDetails(List<ActionCompaniesDetails> actionCompaniesDetails) {
        this.actionCompaniesDetails = actionCompaniesDetails;
    }
    
    
    
}
