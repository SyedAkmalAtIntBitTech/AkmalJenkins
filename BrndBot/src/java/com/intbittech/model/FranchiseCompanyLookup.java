/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author AbdulRaqeeb
 */
@Entity
@Table(name = "franchise_company_lookup")
public class FranchiseCompanyLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "franchise_company_lookup_id")
    private Integer franchiseCompanyLookupId;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "is_headquarter")
    private boolean isHeadquarter;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @JoinColumn(name = "fk_franchise_id", referencedColumnName = "franchise_id")
    @ManyToOne(optional = false)
    private Franchise fkFranchiseId;
    @JoinColumn(name = "fk_added_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users fkAddedBy;

    public FranchiseCompanyLookup() {
    }

    public FranchiseCompanyLookup(Integer franchiseCompanyLookupId) {
        this.franchiseCompanyLookupId = franchiseCompanyLookupId;
    }

    public FranchiseCompanyLookup(Integer franchiseCompanyLookupId, boolean isHeadquarter) {
        this.franchiseCompanyLookupId = franchiseCompanyLookupId;
        this.isHeadquarter = isHeadquarter;
    }

    public Integer getFranchiseCompanyLookupId() {
        return franchiseCompanyLookupId;
    }

    public void setFranchiseCompanyLookupId(Integer franchiseCompanyLookupId) {
        this.franchiseCompanyLookupId = franchiseCompanyLookupId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getIsHeadquarter() {
        return isHeadquarter;
    }

    public void setIsHeadquarter(boolean isHeadquarter) {
        this.isHeadquarter = isHeadquarter;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

    public Franchise getFkFranchiseId() {
        return fkFranchiseId;
    }

    public void setFkFranchiseId(Franchise fkFranchiseId) {
        this.fkFranchiseId = fkFranchiseId;
    }

    public Users getFkAddedBy() {
        return fkAddedBy;
    }

    public void setFkAddedBy(Users fkAddedBy) {
        this.fkAddedBy = fkAddedBy;
    }

    public static List<Company> getCompanys(List<FranchiseCompanyLookup> franchises) {
        List<Company> companys = new ArrayList<>();
        for (FranchiseCompanyLookup franchiseCompanyLookup : franchises) {
            companys.add(franchiseCompanyLookup.getFkCompanyId());
        }
        return companys;
    }

    
    public static List<Franchise> getFranchises(List<FranchiseCompanyLookup> franchiseCompanyLookups) {
        List<Franchise> franchises = new ArrayList<>();
        for (FranchiseCompanyLookup franchiseCompanyLookup : franchiseCompanyLookups) {
            franchises.add(franchiseCompanyLookup.getFkFranchiseId());
        }
        return franchises;
    }

    public Boolean getIsHeadQuarter() {
        return isHeadquarter;
    }

    public void setIsHeadQuarter(Boolean isHeadQuarter) {
        this.isHeadquarter = isHeadQuarter;
    }
}
