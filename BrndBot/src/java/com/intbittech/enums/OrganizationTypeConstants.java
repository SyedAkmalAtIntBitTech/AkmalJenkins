/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbit;

/**
 *
 * @author ajit
 */
public enum OrganizationTypeConstants {
    
    group(1),organization(2);
      private OrganizationTypeConstants(Integer OrganizationType) {
        this.OrganizationType = OrganizationType;
    }
    
    private Integer OrganizationType;
    
    public Integer getOrganizationType(){
        return this.OrganizationType;
    }
    
}
