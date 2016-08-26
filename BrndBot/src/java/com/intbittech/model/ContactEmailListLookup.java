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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Entity
@Table(name = "contact_email_list_lookup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactEmailListLookup.findAll", query = "SELECT c FROM ContactEmailListLookup c"),
    @NamedQuery(name = "ContactEmailListLookup.findByContactLookupId", query = "SELECT c FROM ContactEmailListLookup c WHERE c.contactLookupId = :contactLookupId"),
    @NamedQuery(name = "ContactEmailListLookup.findByAddedDate", query = "SELECT c FROM ContactEmailListLookup c WHERE c.addedDate = :addedDate"),
    @NamedQuery(name = "ContactEmailListLookup.findByUnsubscribed", query = "SELECT c FROM ContactEmailListLookup c WHERE c.unsubscribed = :unsubscribed")})
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
    private Contacts fkContactId;
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

    public Contacts getFkContactId() {
        return fkContactId;
    }

    public void setFkContactId(Contacts fkContactId) {
        this.fkContactId = fkContactId;
    }

    public EmailList getFkEmailListId() {
        return fkEmailListId;
    }

    public void setFkEmailListId(EmailList fkEmailListId) {
        this.fkEmailListId = fkEmailListId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactLookupId != null ? contactLookupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactEmailListLookup)) {
            return false;
        }
        ContactEmailListLookup other = (ContactEmailListLookup) object;
        if ((this.contactLookupId == null && other.contactLookupId != null) || (this.contactLookupId != null && !this.contactLookupId.equals(other.contactLookupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbittech.model.ContactEmailListLookup[ contactLookupId=" + contactLookupId + " ]";
    }
    
}
