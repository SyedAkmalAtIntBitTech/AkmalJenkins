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
 * @author ajit
 */
@Entity
@Table(name = "external_source_keyword_lookup")
public class ExternalSourceKeywordLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "external_source_keyword_lookup_id")
    private Integer externalSourceKeywordLookupId;
    @JoinColumn(name = "fk_external_source_id", referencedColumnName = "external_source_id")
    @ManyToOne
    private ExternalSource fkExternalSourceId;
    @JoinColumn(name = "fk_external_source_keyword_id", referencedColumnName = "external_source_keyword_id")
    @ManyToOne
    private ExternalSourceKeyword fkExternalSourceKeywordId;

    public ExternalSourceKeywordLookup() {
    }

    public ExternalSourceKeywordLookup(Integer externalSourceKeywordLookupId) {
        this.externalSourceKeywordLookupId = externalSourceKeywordLookupId;
    }

    public Integer getExternalSourceKeywordLookupId() {
        return externalSourceKeywordLookupId;
    }

    public void setExternalSourceKeywordLookupId(Integer externalSourceKeywordLookupId) {
        this.externalSourceKeywordLookupId = externalSourceKeywordLookupId;
    }

    public ExternalSource getFkExternalSourceId() {
        return fkExternalSourceId;
    }

    public void setFkExternalSourceId(ExternalSource fkExternalSourceId) {
        this.fkExternalSourceId = fkExternalSourceId;
    }

    public ExternalSourceKeyword getFkExternalSourceKeywordId() {
        return fkExternalSourceKeywordId;
    }

    public void setFkExternalSourceKeywordId(ExternalSourceKeyword fkExternalSourceKeywordId) {
        this.fkExternalSourceKeywordId = fkExternalSourceKeywordId;
    }
}
