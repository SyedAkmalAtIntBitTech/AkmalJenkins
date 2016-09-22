/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ajit @ Intbit
 */
@Entity
@Table(name = "contact_email_list_lookup")
public class ContactEmailListLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contact_lookup_id")
    private Integer contactLookupId;
    @Column(name = "added_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @Column(name = "unsubscribed")
    private Boolean unsubscribed;
    @JoinColumn(name = "fk_contact_id", referencedColumnName = "contact_id")
    @ManyToOne
    private Contact fkContactId;
    @JoinColumn(name = "fk_email_list_id", referencedColumnName = "email_list_id")
    @ManyToOne
    private EmailList fkEmailListId;

    public ContactEmailListLookup() {
    }

    public ContactEmailListLookup(Integer contactLookupId) {
        this.contactLookupId = contactLookupId;
    }

    public Integer getContactLookupId() {
        return contactLookupId;
    }

    public void setContactLookupId(Integer contactLookupId) {
        this.contactLookupId = contactLookupId;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Boolean getUnsubscribed() {
        return unsubscribed;
    }

    public void setUnsubscribed(Boolean unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    public Contact getFkContactId() {
        return fkContactId;
    }

    public void setFkContactId(Contact fkContactId) {
        this.fkContactId = fkContactId;
    }

    public EmailList getFkEmailListId() {
        return fkEmailListId;
    }

    public void setFkEmailListId(EmailList fkEmailListId) {
        this.fkEmailListId = fkEmailListId;
    }

}
