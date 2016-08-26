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
@Table(name = "unsubscribed_emails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnsubscribedEmails.findAll", query = "SELECT u FROM UnsubscribedEmails u"),
    @NamedQuery(name = "UnsubscribedEmails.findByUnsubscribedEmailId", query = "SELECT u FROM UnsubscribedEmails u WHERE u.unsubscribedEmailId = :unsubscribedEmailId"),
    @NamedQuery(name = "UnsubscribedEmails.findByEmailAddress", query = "SELECT u FROM UnsubscribedEmails u WHERE u.emailAddress = :emailAddress"),
    @NamedQuery(name = "UnsubscribedEmails.findByCreatedDate", query = "SELECT u FROM UnsubscribedEmails u WHERE u.createdDate = :createdDate")})
public class UnsubscribedEmails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unsubscribed_email_id")
    private Integer unsubscribedEmailId;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public UnsubscribedEmails() {
    }

    public UnsubscribedEmails(Integer unsubscribedEmailId) {
        this.unsubscribedEmailId = unsubscribedEmailId;
    }

    public Integer getUnsubscribedEmailId() {
        return unsubscribedEmailId;
    }

    public void setUnsubscribedEmailId(Integer unsubscribedEmailId) {
        this.unsubscribedEmailId = unsubscribedEmailId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unsubscribedEmailId != null ? unsubscribedEmailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnsubscribedEmails)) {
            return false;
        }
        UnsubscribedEmails other = (UnsubscribedEmails) object;
        if ((this.unsubscribedEmailId == null && other.unsubscribedEmailId != null) || (this.unsubscribedEmailId != null && !this.unsubscribedEmailId.equals(other.unsubscribedEmailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbittech.model.UnsubscribedEmails[ unsubscribedEmailId=" + unsubscribedEmailId + " ]";
    }
    
}
