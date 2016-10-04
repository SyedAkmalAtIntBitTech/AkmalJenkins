/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.sendgrid.models;

import java.util.List;

/**
 *
 * @author ar
 */
public class SendGridUsers extends BaseSendGridModel{

    public List<SendGridUsers> getSendGridUsers() {
        return sendGridUsers;
    }

    public void setSendGridUsers(List<SendGridUsers> sendGridUsers) {
        this.sendGridUsers = sendGridUsers;
    }
    
    List<SendGridUsers> sendGridUsers;
    
    
}
