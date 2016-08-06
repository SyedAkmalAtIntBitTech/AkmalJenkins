/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.Franchise;
import java.io.Serializable;

/**
 *
 * @author AR
 */
public class FranchiseDetails implements Serializable{

    public static Franchise deserialize(FranchiseDetails franchiseDetails) {
        Franchise franchise = new Franchise();
        franchise.setFranchiseName(franchiseDetails.getFranchiseName());
        return franchise;
    }
    
    String franchiseName;

    public FranchiseDetails(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }
    
    
}
