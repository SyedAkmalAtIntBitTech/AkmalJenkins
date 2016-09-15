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
 * @author sandeep
 */
public class UserProfileColorDetails extends UserCompanyIds implements Serializable {
    private String userProfileColor;

    public String getUserProfileColor() {
        return userProfileColor;
    }

    public void setUserProfileColor(String userProfileColor) {
        this.userProfileColor = userProfileColor;
    }
    
}
