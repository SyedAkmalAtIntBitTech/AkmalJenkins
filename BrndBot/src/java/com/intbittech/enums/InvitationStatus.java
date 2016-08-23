/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.enums;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public enum InvitationStatus {
    Invite_Sent("Invite Sent"),Invite_Success("Invite Success"),Account_Activated("Activated"),Account_Deactivated("Deactivated");
    private InvitationStatus(String displayName) {
        this.displayName = displayName;
    }
    
    private String displayName;
    
    public String getDisplayName(){
        return this.displayName;
    }
    
}
