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
@Table(name = "organization_recurring_email_lookup")
public class OrganizationRecurringEmailLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organization_recurring_email_lookup_id")
    private Integer organizationRecurringEmailLookupId;
    @JoinColumn(name = "fk_organization_id", referencedColumnName = "organization_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization fkOrganizationId;
    @JoinColumn(name = "fk_recurring_email_template_id", referencedColumnName = "recurring_email_template_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RecurringEmailTemplate fkRecurringEmailTemplateId;

    public OrganizationRecurringEmailLookup() {
    }

    public OrganizationRecurringEmailLookup(Integer organizationRecurringEmailLookupId) {
        this.organizationRecurringEmailLookupId = organizationRecurringEmailLookupId;
    }

    public Integer getOrganizationRecurringEmailLookupId() {
        return organizationRecurringEmailLookupId;
    }

    public void setOrganizationRecurringEmailLookupId(Integer organizationRecurringEmailLookupId) {
        this.organizationRecurringEmailLookupId = organizationRecurringEmailLookupId;
    }

    public Organization getFkOrganizationId() {
        return fkOrganizationId;
    }

    public void setFkOrganizationId(Organization fkOrganizationId) {
        this.fkOrganizationId = fkOrganizationId;
    }

    public RecurringEmailTemplate getFkRecurringEmailTemplateId() {
        return fkRecurringEmailTemplateId;
    }

    public void setFkRecurringEmailTemplateId(RecurringEmailTemplate fkRecurringEmailTemplateId) {
        this.fkRecurringEmailTemplateId = fkRecurringEmailTemplateId;
    }
}
