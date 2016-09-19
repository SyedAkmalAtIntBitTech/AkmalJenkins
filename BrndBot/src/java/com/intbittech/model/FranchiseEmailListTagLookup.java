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
 * @author ajit @ Intbit
 */
@Entity
@Table(name = "franchise_email_list_tag_lookup")
public class FranchiseEmailListTagLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "franchise_email_list_tag_lookup_id")
    private Integer franchiseEmailListTagLookupId;
    @JoinColumn(name = "fk_email_list_tag_id", referencedColumnName = "tag_id")
    @ManyToOne(optional = false)
    private EmailListTag fkEmailListTagId;
    @JoinColumn(name = "fk_franchise__id", referencedColumnName = "franchise_id")
    @ManyToOne(optional = false)
    private Franchise fkFranchiseId;

    public FranchiseEmailListTagLookup() {
    }

    public FranchiseEmailListTagLookup(Integer franchiseEmailListTagLookupId) {
        this.franchiseEmailListTagLookupId = franchiseEmailListTagLookupId;
    }

    public Integer getFranchiseEmailListTagLookupId() {
        return franchiseEmailListTagLookupId;
    }

    public void setFranchiseEmailListTagLookupId(Integer franchiseEmailListTagLookupId) {
        this.franchiseEmailListTagLookupId = franchiseEmailListTagLookupId;
    }

    public EmailListTag getFkEmailListTagId() {
        return fkEmailListTagId;
    }

    public void setFkEmailListTagId(EmailListTag fkEmailListTagId) {
        this.fkEmailListTagId = fkEmailListTagId;
    }

    public Franchise getFkFranchiseId() {
        return fkFranchiseId;
    }

    public void setFkFranchiseId(Franchise fkFranchiseId) {
        this.fkFranchiseId = fkFranchiseId;
    }
}
