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
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "sub_category_external_source")
public class SubCategoryExternalSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_category_external_source_id")
    private Integer subCategoryExternalSourceId;
    @JoinColumn(name = "fk_external_source_keyword_lookup_id", referencedColumnName = "external_source_keyword_lookup_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ExternalSourceKeywordLookup fkExternalSourceKeywordLookupId;
    @JoinColumn(name = "fk_sub_category_id", referencedColumnName = "sub_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategory fkSubCategoryId;

    public SubCategoryExternalSource() {
    }

    public SubCategoryExternalSource(Integer subCategoryExternalSourceId) {
        this.subCategoryExternalSourceId = subCategoryExternalSourceId;
    }

    public Integer getSubCategoryExternalSourceId() {
        return subCategoryExternalSourceId;
    }

    public void setSubCategoryExternalSourceId(Integer subCategoryExternalSourceId) {
        this.subCategoryExternalSourceId = subCategoryExternalSourceId;
    }

    public ExternalSourceKeywordLookup getFkExternalSourceKeywordLookupId() {
        return fkExternalSourceKeywordLookupId;
    }

    public void setFkExternalSourceKeywordLookupId(ExternalSourceKeywordLookup fkExternalSourceKeywordLookupId) {
        this.fkExternalSourceKeywordLookupId = fkExternalSourceKeywordLookupId;
    }

    public SubCategory getFkSubCategoryId() {
        return fkSubCategoryId;
    }

    public void setFkSubCategoryId(SubCategory fkSubCategoryId) {
        this.fkSubCategoryId = fkSubCategoryId;
    }

}
