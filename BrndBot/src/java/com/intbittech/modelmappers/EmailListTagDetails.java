/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public class EmailListTagDetails extends UserCompanyIds{
    private String emailListTagName;
    private String emailListTagDescription;

    public String getEmailListTagName() {
        return emailListTagName;
    }

    public void setEmailListTagName(String emailListTagName) {
        this.emailListTagName = emailListTagName;
    }

    public String getEmailListTagDescription() {
        return emailListTagDescription;
    }

    public void setEmailListTagDescription(String emailListTagDescription) {
        this.emailListTagDescription = emailListTagDescription;
    }
    
    
}
