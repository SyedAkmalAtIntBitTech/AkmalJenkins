/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;

/**
 *
 * @author ajit
 */
public class OrganizationDetails implements Serializable {

    private Integer OrganizationId;
    private String OrganizationName;

    public Integer getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(Integer OrganizationId) {
        this.OrganizationId = OrganizationId;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String OrganizationName) {
        this.OrganizationName = OrganizationName;
    }

}
