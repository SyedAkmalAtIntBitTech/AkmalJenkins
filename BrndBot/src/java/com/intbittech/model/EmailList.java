/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Entity
@Table(name = "email_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmailList.findAll", query = "SELECT e FROM EmailList e"),
    @NamedQuery(name = "EmailList.findByEmailListId", query = "SELECT e FROM EmailList e WHERE e.emailListId = :emailListId"),
    @NamedQuery(name = "EmailList.findByEmailListName", query = "SELECT e FROM EmailList e WHERE e.emailListName = :emailListName"),
    @NamedQuery(name = "EmailList.findByDescription", query = "SELECT e FROM EmailList e WHERE e.description = :description"),
    @NamedQuery(name = "EmailList.findByDefaultFromAddress", query = "SELECT e FROM EmailList e WHERE e.defaultFromAddress = :defaultFromAddress"),
    @NamedQuery(name = "EmailList.findByCreatedDate", query = "SELECT e FROM EmailList e WHERE e.createdDate = :createdDate")})
public class EmailList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_list_id")
    private Integer emailListId;
    @Column(name = "email_list_name")
    private String emailListName;
    @Column(name = "description")
    private String description;
    @Column(name = "default_from_address")
    private String defaultFromAddress;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEmailListId")
    private Set<EmailListTagLookup> emailListTagLookupSet;
    @JoinColumn(name = "fk_type_id", referencedColumnName = "type_id")
    @ManyToOne
    private EmailListType fkTypeId;
    @OneToMany(mappedBy = "fkEmailListId")
    private Set<ContactEmailListLookup> contactEmailListLookupSet;

    public EmailList() {
    }

    public EmailList(Integer emailListId) {
        this.emailListId = emailListId;
    }

    public Integer getEmailListId() {
        return emailListId;
    }

    public void setEmailListId(Integer emailListId) {
        this.emailListId = emailListId;
    }

    public String getEmailListName() {
        return emailListName;
    }

    public void setEmailListName(String emailListName) {
        this.emailListName = emailListName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultFromAddress() {
        return defaultFromAddress;
    }

    public void setDefaultFromAddress(String defaultFromAddress) {
        this.defaultFromAddress = defaultFromAddress;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Set<EmailListTagLookup> getEmailListTagLookupSet() {
        return emailListTagLookupSet;
    }

    public void setEmailListTagLookupSet(Set<EmailListTagLookup> emailListTagLookupSet) {
        this.emailListTagLookupSet = emailListTagLookupSet;
    }

    public EmailListType getFkTypeId() {
        return fkTypeId;
    }

    public void setFkTypeId(EmailListType fkTypeId) {
        this.fkTypeId = fkTypeId;
    }

    @XmlTransient
    public Set<ContactEmailListLookup> getContactEmailListLookupSet() {
        return contactEmailListLookupSet;
    }

    public void setContactEmailListLookupSet(Set<ContactEmailListLookup> contactEmailListLookupSet) {
        this.contactEmailListLookupSet = contactEmailListLookupSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailListId != null ? emailListId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailList)) {
            return false;
        }
        EmailList other = (EmailList) object;
        if ((this.emailListId == null && other.emailListId != null) || (this.emailListId != null && !this.emailListId.equals(other.emailListId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbittech.model.EmailList[ emailListId=" + emailListId + " ]";
    }
    
}
