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
 * @author ajit
 */
@Entity
@Table(name = "company_marketing_program")
public class CompanyMarketingProgram implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_marketing_program_id")
    private Integer companyMarketingProgramId;
    @Column(name = "company_marketing_program_name")
    private String companyMarketingProgramName;
    @Column(name = "date_event")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEvent;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "status")
    private String status;
    @Column(name = "url")
    private String url;
    @Column(name = "link_name")
    private String linkName;
    @JoinColumn(name = "fk_marketing_program_id")
    @ManyToOne
    private MarketingProgram fkMarketingProgramId;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @Column(name = "is_pushed")
    private Boolean isPushed;

    public CompanyMarketingProgram() {
    }

    public CompanyMarketingProgram(Integer companyMarketingProgramId) {
        this.companyMarketingProgramId = companyMarketingProgramId;
    }

    public Integer getCompanyMarketingProgramId() {
        return companyMarketingProgramId;
    }

    public void setCompanyMarketingProgramId(Integer companyMarketingProgramId) {
        this.companyMarketingProgramId = companyMarketingProgramId;
    }

    public String getCompanyMarketingProgramName() {
        return companyMarketingProgramName;
    }

    public void setCompanyMarketingProgramName(String companyMarketingProgramName) {
        this.companyMarketingProgramName = companyMarketingProgramName;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public MarketingProgram getFkMarketingProgramId() {
        return fkMarketingProgramId;
    }

    public void setFkMarketingProgramId(MarketingProgram fkMarketingProgramId) {
        this.fkMarketingProgramId = fkMarketingProgramId;
    }

  

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }
    public Boolean getIsPushed() {
        return isPushed;
    }

    public void setIsPushed(Boolean isPushed) {
        this.isPushed = isPushed;
    }

}
