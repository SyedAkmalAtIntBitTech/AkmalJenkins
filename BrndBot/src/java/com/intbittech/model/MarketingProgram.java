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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ilyas
 */
@Entity
@Table(name = "marketing_program")
public class MarketingProgram implements Serializable {

    @Column(name = "fk_marketing_category_id")
    private Integer fkMarketingCategoryId;
    @OneToMany(mappedBy = "fkMarketingProgramId")
    private Set<CompanyMarketingProgram> companyMarketingProgramSet;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "marketing_program_id")
    private Integer marketingProgramId;
    @Column(name = "marketing_program_name")
    private String marketingProgramName;
    @Column(name = "html_data")
    private String htmlData;

    public MarketingProgram() {
    }

    public MarketingProgram(Integer marketingProgramId) {
        this.marketingProgramId = marketingProgramId;
    }

    public Integer getMarketingProgramId() {
        return marketingProgramId;
    }

    public void setMarketingProgramId(Integer marketingProgramId) {
        this.marketingProgramId = marketingProgramId;
    }

    public String getMarketingProgramName() {
        return marketingProgramName;
    }

    public void setMarketingProgramName(String marketingProgramName) {
        this.marketingProgramName = marketingProgramName;
    }

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }

    public Integer getFkMarketingCategoryId() {
        return fkMarketingCategoryId;
    }

    public void setFkMarketingCategoryId(Integer fkMarketingCategoryId) {
        this.fkMarketingCategoryId = fkMarketingCategoryId;
    }

    @XmlTransient
    public Set<CompanyMarketingProgram> getCompanyMarketingProgramSet() {
        return companyMarketingProgramSet;
    }

    public void setCompanyMarketingProgramSet(Set<CompanyMarketingProgram> companyMarketingProgramSet) {
        this.companyMarketingProgramSet = companyMarketingProgramSet;
    }

}
