/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
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
@Table(name = "email_list_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmailListType.findAll", query = "SELECT e FROM EmailListType e"),
    @NamedQuery(name = "EmailListType.findByTypeId", query = "SELECT e FROM EmailListType e WHERE e.typeId = :typeId"),
    @NamedQuery(name = "EmailListType.findByTypeName", query = "SELECT e FROM EmailListType e WHERE e.typeName = :typeName")})
public class EmailListType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeId;
    @Column(name = "type_name")
    private String typeName;
    @OneToMany(mappedBy = "fkTypeId")
    private Set<EmailList> emailListSet;

    public EmailListType() {
    }

    public EmailListType(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @XmlTransient
    public Set<EmailList> getEmailListSet() {
        return emailListSet;
    }

    public void setEmailListSet(Set<EmailList> emailListSet) {
        this.emailListSet = emailListSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailListType)) {
            return false;
        }
        EmailListType other = (EmailListType) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbittech.model.EmailListType[ typeId=" + typeId + " ]";
    }
    
}
