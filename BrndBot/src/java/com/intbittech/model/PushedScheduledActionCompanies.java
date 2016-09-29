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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ajit @ Intbit
 */
@Entity
@Table(name = "pushed_scheduled_action_companies")
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

   
}
