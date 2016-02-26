/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbit.model;

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
 * @author ajit
 */
@Entity
@Table(name = "organization_recuring_email_lookup")
public class OrganizationRecuringEmailLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organization_recuring_email_lookup_id")
    private Integer organizationRecuringEmailLookupId;
    @JoinColumn(name = "fk_organization_id", referencedColumnName = "organization_id")
    @ManyToOne
    private Organization fkOrganizationId;
    @JoinColumn(name = "fk_recuring_email_template_id", referencedColumnName = "recuring_email_template_id")
    @ManyToOne
    private RecuringEmailTemplate fkRecuringEmailTemplateId;

    public OrganizationRecuringEmailLookup() {
    }

    public OrganizationRecuringEmailLookup(Integer organizationRecuringEmailLookupId) {
        this.organizationRecuringEmailLookupId = organizationRecuringEmailLookupId;
    }

    public Integer getOrganizationRecuringEmailLookupId() {
        return organizationRecuringEmailLookupId;
    }

    public void setOrganizationRecuringEmailLookupId(Integer organizationRecuringEmailLookupId) {
        this.organizationRecuringEmailLookupId = organizationRecuringEmailLookupId;
    }

    public Organization getFkOrganizationId() {
        return fkOrganizationId;
    }

    public void setFkOrganizationId(Organization fkOrganizationId) {
        this.fkOrganizationId = fkOrganizationId;
    }

    public RecuringEmailTemplate getFkRecuringEmailTemplateId() {
        return fkRecuringEmailTemplateId;
    }

    public void setFkRecuringEmailTemplateId(RecuringEmailTemplate fkRecuringEmailTemplateId) {
        this.fkRecuringEmailTemplateId = fkRecuringEmailTemplateId;
    }
}
