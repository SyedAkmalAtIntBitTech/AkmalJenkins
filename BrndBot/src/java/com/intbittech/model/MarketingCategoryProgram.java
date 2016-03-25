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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilyas
 */
@Entity
@Table(name = "marketing_category_program")
public class MarketingCategoryProgram implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "marketing_category_program_id")
    private Integer marketingCategoryProgramId;
    @JoinColumn(name = "fk_marketing_category", referencedColumnName = "marketing_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketingCategory fkMarketingCategory;
    @JoinColumn(name = "fk_marketing_program", referencedColumnName = "marketing_program_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketingProgram fkMarketingProgram;

    public MarketingCategoryProgram() {
    }

    public MarketingCategoryProgram(Integer marketingCategoryProgramId) {
        this.marketingCategoryProgramId = marketingCategoryProgramId;
    }

    public Integer getMarketingCategoryProgramId() {
        return marketingCategoryProgramId;
    }

    public void setMarketingCategoryProgramId(Integer marketingCategoryProgramId) {
        this.marketingCategoryProgramId = marketingCategoryProgramId;
    }

    public MarketingCategory getFkMarketingCategory() {
        return fkMarketingCategory;
    }

    public void setFkMarketingCategory(MarketingCategory fkMarketingCategory) {
        this.fkMarketingCategory = fkMarketingCategory;
    }

    public MarketingProgram getFkMarketingProgram() {
        return fkMarketingProgram;
    }

    public void setFkMarketingProgram(MarketingProgram fkMarketingProgram) {
        this.fkMarketingProgram = fkMarketingProgram;
    }
    
}
