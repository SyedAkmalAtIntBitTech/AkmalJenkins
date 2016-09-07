/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ilyas
 */
@Entity
@Table(name = "unsubscribed_company_lookup")
public class UnsubscribedCompanyLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unsubscribed_company_id")
    private Integer unsubscribedCompanyId;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @JoinColumn(name = "fk_unsubscribed_email_id", referencedColumnName = "unsubscribed_email_id")
    @ManyToOne
    private UnsubscribedEmails fkUnsubscribedEmailId;

    public UnsubscribedCompanyLookup() {
    }

    public UnsubscribedCompanyLookup(Integer unsubscribedCompanyId) {
        this.unsubscribedCompanyId = unsubscribedCompanyId;
    }

    public Integer getUnsubscribedCompanyId() {
        return unsubscribedCompanyId;
    }

    public void setUnsubscribedCompanyId(Integer unsubscribedCompanyId) {
        this.unsubscribedCompanyId = unsubscribedCompanyId;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

    public UnsubscribedEmails getFkUnsubscribedEmailId() {
        return fkUnsubscribedEmailId;
    }

    public void setFkUnsubscribedEmailId(UnsubscribedEmails fkUnsubscribedEmailId) {
        this.fkUnsubscribedEmailId = fkUnsubscribedEmailId;
    }
}
