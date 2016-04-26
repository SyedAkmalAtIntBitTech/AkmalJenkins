/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organization_id")
    private Integer organizationId;
    @Column(name = "organization_name")
    private String organizationName;
    @JoinColumn(name = "fk_organization_type_id", referencedColumnName = "organization_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganizationType fkOrganizationTypeId;

    public OrganizationType getFkOrganizationTypeId() {
        return fkOrganizationTypeId;
    }

    public void setFkOrganizationTypeId(OrganizationType fkOrganizationTypeId) {
        this.fkOrganizationTypeId = fkOrganizationTypeId;
    }

    public Organization() {
    }

    public Organization(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
