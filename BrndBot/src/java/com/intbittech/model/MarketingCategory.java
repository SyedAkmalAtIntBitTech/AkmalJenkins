/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ilyas
 */
@Entity
@Table(name = "marketing_category")
public class MarketingCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "marketing_category_id")
    private Integer marketingCategoryId;
    @Column(name = "marketing_category_name")
    private String marketingCategoryName;
    
    public MarketingCategory() {
    }

    public MarketingCategory(Integer marketingCategoryId) {
        this.marketingCategoryId = marketingCategoryId;
    }

    public Integer getMarketingCategoryId() {
        return marketingCategoryId;
    }

    public void setMarketingCategoryId(Integer marketingCategoryId) {
        this.marketingCategoryId = marketingCategoryId;
    }

    public String getMarketingCategoryName() {
        return marketingCategoryName;
    }

    public void setMarketingCategoryName(String marketingCategoryName) {
        this.marketingCategoryName = marketingCategoryName;
    }

}
