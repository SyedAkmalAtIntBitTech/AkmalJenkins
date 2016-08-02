/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public class InvitedUsers {
    private String emailID;
    private String userRole;
    private String InviteStatus;

    public String getInviteStatus() {
        return InviteStatus;
    }

    public void setInviteStatus(String InviteStatus) {
        this.InviteStatus = InviteStatus;
    }
    
    public InvitedUsers(String emailID, String userRole, String InviteStatus) {
        this.emailID = emailID;
        this.userRole = userRole;
        this.InviteStatus = InviteStatus;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    
}
