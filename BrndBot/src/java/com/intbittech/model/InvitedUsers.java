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
    private Integer inviteId;
    private String userRoleLookUpId;
    private String emailID;
    private String userRole;
    private String InviteStatus;

    public String getUserRoleLookUp() {
        return userRoleLookUpId;
    }

    public void setUserRoleLookUp(String userRoleLookUpId) {
        this.userRoleLookUpId = userRoleLookUpId;
    }
    
    public String getInviteStatus() {
        return InviteStatus;
    }

    public void setInviteStatus(String InviteStatus) {
        this.InviteStatus = InviteStatus;
    }

    public Integer getInviteId() {
        return inviteId;
    }

    public void setInviteId(Integer inviteId) {
        this.inviteId = inviteId;
    }

    public String getUserRoleLookUpId() {
        return userRoleLookUpId;
    }

    public void setUserRoleLookUpId(String userRoleLookUpId) {
        this.userRoleLookUpId = userRoleLookUpId;
    }
    
    public InvitedUsers(Integer inviteId, String userRoleLookUpId, String emailID, String userRole, String InviteStatus) {
        this.inviteId = inviteId;
        this.userRoleLookUpId = userRoleLookUpId;
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
