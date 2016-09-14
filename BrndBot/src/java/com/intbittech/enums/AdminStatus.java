/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.enums;

/**
 *
 * @author Syed
 */
public enum AdminStatus {
    ROLE_ADMIN("Admin"), ROLE_MANAGER("Manager"),ROLE_CREATOR("Creator"),ROLE_ACCOUNT_OWNER("Owner");
    
    private AdminStatus(String displayName) {
        this.displayName = displayName;
    }
    
    private String displayName;
    
    public String getDisplayName(){
        return this.displayName;
    }
}
