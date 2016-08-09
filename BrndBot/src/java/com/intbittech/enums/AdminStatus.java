/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.enums;

/**
 *
 * @author Mohamed
 */
public enum AdminStatus {
    ROLE_ADMIN("Admin"), ROLE_MANAGER("Manager"),Invite_Sent("Invite Sent"),Invite_Success("Invite Success"),ROLE_CREATOR("Creator"),Account_Activated("Activated"),Account_Deactivated("Deactivated");
    
    private AdminStatus(String displayName) {
        this.displayName = displayName;
    }
    
    private String displayName;
    
    public String getDisplayName(){
        return this.displayName;
    }
}
