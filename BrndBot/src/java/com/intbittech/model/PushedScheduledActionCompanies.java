/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Entity
@Table(name = "pushed_scheduled_action_companies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PushedScheduledActionCompanies.findAll", query = "SELECT p FROM PushedScheduledActionCompanies p"),
    @NamedQuery(name = "PushedScheduledActionCompanies.findByPushedScheduledActionCompaniesId", query = "SELECT p FROM PushedScheduledActionCompanies p WHERE p.pushedScheduledActionCompaniesId = :pushedScheduledActionCompaniesId"),
    @NamedQuery(name = "PushedScheduledActionCompanies.findByStatus", query = "SELECT p FROM PushedScheduledActionCompanies p WHERE p.status = :status"),
    @NamedQuery(name = "PushedScheduledActionCompanies.findByUpdatedAt", query = "SELECT p FROM PushedScheduledActionCompanies p WHERE p.updatedAt = :updatedAt")})
public class PushedScheduledActionCompanies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pushed_scheduled_action_companies_id")
    private Integer pushedScheduledActionCompaniesId;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne(optional = false)
    private Company fkCompanyId;
    @JoinColumn(name = "fk_pushed_scheduled_action_entity_list_id", referencedColumnName = "pushed_scheduled_entity_list_id")
    @ManyToOne(optional = false)
    private PushedScheduledEntityList fkPushedScheduledActionEntityListId;

    public PushedScheduledActionCompanies() {
    }

    public PushedScheduledActionCompanies(Integer pushedScheduledActionCompaniesId) {
        this.pushedScheduledActionCompaniesId = pushedScheduledActionCompaniesId;
    }

    public PushedScheduledActionCompanies(Integer pushedScheduledActionCompaniesId, String status, Date updatedAt) {
        this.pushedScheduledActionCompaniesId = pushedScheduledActionCompaniesId;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public Integer getPushedScheduledActionCompaniesId() {
        return pushedScheduledActionCompaniesId;
    }

    public void setPushedScheduledActionCompaniesId(Integer pushedScheduledActionCompaniesId) {
        this.pushedScheduledActionCompaniesId = pushedScheduledActionCompaniesId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

    public PushedScheduledEntityList getFkPushedScheduledActionEntityListId() {
        return fkPushedScheduledActionEntityListId;
    }

    public void setFkPushedScheduledActionEntityListId(PushedScheduledEntityList fkPushedScheduledActionEntityListId) {
        this.fkPushedScheduledActionEntityListId = fkPushedScheduledActionEntityListId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pushedScheduledActionCompaniesId != null ? pushedScheduledActionCompaniesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PushedScheduledActionCompanies)) {
            return false;
        }
        PushedScheduledActionCompanies other = (PushedScheduledActionCompanies) object;
        if ((this.pushedScheduledActionCompaniesId == null && other.pushedScheduledActionCompaniesId != null) || (this.pushedScheduledActionCompaniesId != null && !this.pushedScheduledActionCompaniesId.equals(other.pushedScheduledActionCompaniesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbittech.model.PushedScheduledActionCompanies[ pushedScheduledActionCompaniesId=" + pushedScheduledActionCompaniesId + " ]";
    }
    
}
