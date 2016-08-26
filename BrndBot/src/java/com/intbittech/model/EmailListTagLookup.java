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
@Table(name = "email_list_tag_lookup")
public class EmailListTagLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_list_tag_id")
    private Integer emailListTagId;
    @JoinColumn(name = "fk_email_list_id", referencedColumnName = "email_list_id")
    @ManyToOne(optional = false)
    private EmailList fkEmailListId;
    @JoinColumn(name = "fk_email_list_tag_id", referencedColumnName = "tag_id")
    @ManyToOne(optional = false)
    private EmailListTag fkEmailListTagId;

    public EmailListTagLookup() {
    }

    public EmailListTagLookup(Integer emailListTagId) {
        this.emailListTagId = emailListTagId;
    }

    public Integer getEmailListTagId() {
        return emailListTagId;
    }

    public void setEmailListTagId(Integer emailListTagId) {
        this.emailListTagId = emailListTagId;
    }

    public EmailList getFkEmailListId() {
        return fkEmailListId;
    }

    public void setFkEmailListId(EmailList fkEmailListId) {
        this.fkEmailListId = fkEmailListId;
    }

    public EmailListTag getFkEmailListTagId() {
        return fkEmailListTagId;
    }

    public void setFkEmailListTagId(EmailListTag fkEmailListTagId) {
        this.fkEmailListTagId = fkEmailListTagId;
    }
    
}
