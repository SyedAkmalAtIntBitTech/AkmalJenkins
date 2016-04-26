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
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "external_source_keyword")
public class ExternalSourceKeyword implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "external_source_keyword_id")
    private Integer externalSourceKeywordId;
    @Column(name = "external_source_keyword_name")
    private String externalSourceKeywordName;

    public ExternalSourceKeyword() {
    }

    public ExternalSourceKeyword(Integer externalSourceKeywordId) {
        this.externalSourceKeywordId = externalSourceKeywordId;
    }

    public Integer getExternalSourceKeywordId() {
        return externalSourceKeywordId;
    }

    public void setExternalSourceKeywordId(Integer externalSourceKeywordId) {
        this.externalSourceKeywordId = externalSourceKeywordId;
    }

    public String getExternalSourceKeywordName() {
        return externalSourceKeywordName;
    }

    public void setExternalSourceKeywordName(String externalSourceKeywordName) {
        this.externalSourceKeywordName = externalSourceKeywordName;
    }
}
