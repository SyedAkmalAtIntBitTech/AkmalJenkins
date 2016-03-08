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
@Table(name = "email_block_external_source")
public class EmailBlockExternalSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_block_external_source_id")
    private Integer emailBlockExternalSourceId;
    @JoinColumn(name = "fk_email_block_id", referencedColumnName = "email_block_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EmailBlock fkEmailBlockId;
    @JoinColumn(name = "fk_external_source_keyword_lookup_id", referencedColumnName = "external_source_keyword_lookup_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ExternalSourceKeywordLookup fkExternalSourceKeywordLookupId;

    public EmailBlockExternalSource() {
    }

    public EmailBlockExternalSource(Integer emailBlockExternalSourceId) {
        this.emailBlockExternalSourceId = emailBlockExternalSourceId;
    }

    public Integer getEmailBlockExternalSourceId() {
        return emailBlockExternalSourceId;
    }

    public void setEmailBlockExternalSourceId(Integer emailBlockExternalSourceId) {
        this.emailBlockExternalSourceId = emailBlockExternalSourceId;
    }

    public EmailBlock getFkEmailBlockId() {
        return fkEmailBlockId;
    }

    public void setFkEmailBlockId(EmailBlock fkEmailBlockId) {
        this.fkEmailBlockId = fkEmailBlockId;
    }

    public ExternalSourceKeywordLookup getFkExternalSourceKeywordLookupId() {
        return fkExternalSourceKeywordLookupId;
    }

    public void setFkExternalSourceKeywordLookupId(ExternalSourceKeywordLookup fkExternalSourceKeywordLookupId) {
        this.fkExternalSourceKeywordLookupId = fkExternalSourceKeywordLookupId;
    }
}
