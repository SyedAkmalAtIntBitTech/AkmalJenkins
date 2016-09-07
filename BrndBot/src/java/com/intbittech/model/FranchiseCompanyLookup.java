/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Column(name = "is_headquarter")
    private Boolean isHeadQuarter;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company fkCompanyId;
    @JoinColumn(name = "fk_franchise_id", referencedColumnName = "franchise_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Franchise fkFranchiseId;
    @JoinColumn(name = "fk_added_by", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users fkAddedByUserId;
 
    public FranchiseCompanyLookup() {
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

    public Users getFkAddedByUserId() {
        return fkAddedByUserId;
    }

    public void setFkAddedByUserId(Users fkAddedByUserId) {
        this.fkAddedByUserId = fkAddedByUserId;
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
        return isHeadQuarter;
    }

    public void setIsHeadQuarter(Boolean isHeadQuarter) {
        this.isHeadQuarter = isHeadQuarter;
    }
}
