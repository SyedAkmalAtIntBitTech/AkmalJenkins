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
@Table(name = "organization_email_block_lookup")
public class OrganizationEmailBlockLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organization_category_id")
    private Integer organizationCategoryId;
    @JoinColumn(name = "fk_email_block_id", referencedColumnName = "email_model_id")
    @ManyToOne
    private EmailModel fkEmailBlockId;
    @JoinColumn(name = "fk_organization_id", referencedColumnName = "organization_id")
    @ManyToOne
    private Organization fkOrganizationId;

    public OrganizationEmailBlockLookup() {
    }

    public OrganizationEmailBlockLookup(Integer organizationCategoryId) {
        this.organizationCategoryId = organizationCategoryId;
    }

    public Integer getOrganizationCategoryId() {
        return organizationCategoryId;
    }

    public void setOrganizationCategoryId(Integer organizationCategoryId) {
        this.organizationCategoryId = organizationCategoryId;
    }

    public EmailModel getFkEmailBlockId() {
        return fkEmailBlockId;
    }

    public void setFkEmailBlockId(EmailModel fkEmailBlockId) {
        this.fkEmailBlockId = fkEmailBlockId;
    }

    public Organization getFkOrganizationId() {
        return fkOrganizationId;
    }

    public void setFkOrganizationId(Organization fkOrganizationId) {
        this.fkOrganizationId = fkOrganizationId;
    }

}
