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
@Table(name = "organization_marketing_category_lookup")
public class OrganizationMarketingCategoryLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organization_marketing_category_id")
    private Integer organizationMarketingCategoryId;
    @JoinColumn(name = "fk_marketing_category_id", referencedColumnName = "marketing_category_id")
    @ManyToOne
    private MarketingCategory fkMarketingCategoryId;
    @JoinColumn(name = "fk_organization_id", referencedColumnName = "organization_id")
    @ManyToOne
    private Organization fkOrganizationId;

    public OrganizationMarketingCategoryLookup() {
    }

    public OrganizationMarketingCategoryLookup(Integer organizationMarketingCategoryId) {
        this.organizationMarketingCategoryId = organizationMarketingCategoryId;
    }

    public Integer getOrganizationMarketingCategoryId() {
        return organizationMarketingCategoryId;
    }

    public void setOrganizationMarketingCategoryId(Integer organizationMarketingCategoryId) {
        this.organizationMarketingCategoryId = organizationMarketingCategoryId;
    }

    public MarketingCategory getFkMarketingCategoryId() {
        return fkMarketingCategoryId;
    }

    public void setFkMarketingCategoryId(MarketingCategory fkMarketingCategoryId) {
        this.fkMarketingCategoryId = fkMarketingCategoryId;
    }

    public Organization getFkOrganizationId() {
        return fkOrganizationId;
    }

    public void setFkOrganizationId(Organization fkOrganizationId) {
        this.fkOrganizationId = fkOrganizationId;
    }

    
}
