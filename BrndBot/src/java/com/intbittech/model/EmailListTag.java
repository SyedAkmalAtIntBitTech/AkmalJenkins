/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Entity
@Table(name = "email_list_tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmailListTag.findAll", query = "SELECT e FROM EmailListTag e"),
    @NamedQuery(name = "EmailListTag.findByTagId", query = "SELECT e FROM EmailListTag e WHERE e.tagId = :tagId"),
    @NamedQuery(name = "EmailListTag.findByTagName", query = "SELECT e FROM EmailListTag e WHERE e.tagName = :tagName"),
    @NamedQuery(name = "EmailListTag.findByTagDescription", query = "SELECT e FROM EmailListTag e WHERE e.tagDescription = :tagDescription")})
public class EmailListTag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tag_id")
    private Integer tagId;
    @Column(name = "tag_name")
    private String tagName;
    @Column(name = "tag_description")
    private String tagDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEmailListTagId")
    private Set<EmailListTagLookup> emailListTagLookupSet;

    public EmailListTag() {
    }

    public EmailListTag(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    @XmlTransient
    public Set<EmailListTagLookup> getEmailListTagLookupSet() {
        return emailListTagLookupSet;
    }

    public void setEmailListTagLookupSet(Set<EmailListTagLookup> emailListTagLookupSet) {
        this.emailListTagLookupSet = emailListTagLookupSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagId != null ? tagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailListTag)) {
            return false;
        }
        EmailListTag other = (EmailListTag) object;
        if ((this.tagId == null && other.tagId != null) || (this.tagId != null && !this.tagId.equals(other.tagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbittech.model.EmailListTag[ tagId=" + tagId + " ]";
    }
    
}
