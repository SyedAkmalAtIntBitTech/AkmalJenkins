/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;

/**
 *
 * @author ajit @ Intbit
 */
public class SendGridSubUserDetailsRequest extends UserCompanyIds{
    
   private Integer sendGridSubUserDetailsId;
   private String sendGridUserId;
   private String ips;

    public Integer getSendGridSubUserDetailsId() {
        return sendGridSubUserDetailsId;
    }

    public void setSendGridSubUserDetailsId(Integer sendGridSubUserDetailsId) {
        this.sendGridSubUserDetailsId = sendGridSubUserDetailsId;
    }

    public String getSendGridUserId() {
        return sendGridUserId;
    }

    public void setSendGridUserId(String sendGridUserId) {
        this.sendGridUserId = sendGridUserId;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
   
   
    
}
