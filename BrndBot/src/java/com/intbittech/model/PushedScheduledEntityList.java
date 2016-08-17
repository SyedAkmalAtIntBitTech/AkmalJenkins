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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pushed_scheduled_entity_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PushedScheduledEntityList.findAll", query = "SELECT p FROM PushedScheduledEntityList p"),
    @NamedQuery(name = "PushedScheduledEntityList.findByPushedScheduledEntityListId", query = "SELECT p FROM PushedScheduledEntityList p WHERE p.pushedScheduledEntityListId = :pushedScheduledEntityListId"),
    @NamedQuery(name = "PushedScheduledEntityList.findByAutoApproved", query = "SELECT p FROM PushedScheduledEntityList p WHERE p.autoApproved = :autoApproved"),
    @NamedQuery(name = "PushedScheduledEntityList.findByStatus", query = "SELECT p FROM PushedScheduledEntityList p WHERE p.status = :status"),
    @NamedQuery(name = "PushedScheduledEntityList.findByEditable", query = "SELECT p FROM PushedScheduledEntityList p WHERE p.editable = :editable")})
public class PushedScheduledEntityList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pushed_scheduled_entity_list_id")
    private Integer pushedScheduledEntityListId;
    @Basic(optional = false)
    @Column(name = "auto_approved")
    private boolean autoApproved;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Column(name = "editable")
    private Boolean editable;
    @JoinColumn(name = "fk_franchise_id", referencedColumnName = "franchise_id")
    @ManyToOne(optional = false)
    private Franchise fkFranchiseId;
    @JoinColumn(name = "fk_scheduled_entity_list_id", referencedColumnName = "scheduled_entity_list_id")
    @ManyToOne(optional = false)
    private ScheduledEntityList fkScheduledEntityListId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPushedScheduledActionEntityListId")
    private Set<PushedScheduledActionCompanies> pushedScheduledActionCompaniesSet;

    public PushedScheduledEntityList() {
    }

    public PushedScheduledEntityList(Integer pushedScheduledEntityListId) {
        this.pushedScheduledEntityListId = pushedScheduledEntityListId;
    }

    public PushedScheduledEntityList(Integer pushedScheduledEntityListId, boolean autoApproved, String status) {
        this.pushedScheduledEntityListId = pushedScheduledEntityListId;
        this.autoApproved = autoApproved;
        this.status = status;
    }

    public Integer getPushedScheduledEntityListId() {
        return pushedScheduledEntityListId;
    }

    public void setPushedScheduledEntityListId(Integer pushedScheduledEntityListId) {
        this.pushedScheduledEntityListId = pushedScheduledEntityListId;
    }

    public boolean getAutoApproved() {
        return autoApproved;
    }

    public void setAutoApproved(boolean autoApproved) {
        this.autoApproved = autoApproved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Franchise getFkFranchiseId() {
        return fkFranchiseId;
    }

    public void setFkFranchiseId(Franchise fkFranchiseId) {
        this.fkFranchiseId = fkFranchiseId;
    }

    public ScheduledEntityList getFkScheduledEntityListId() {
        return fkScheduledEntityListId;
    }

    public void setFkScheduledEntityListId(ScheduledEntityList fkScheduledEntityListId) {
        this.fkScheduledEntityListId = fkScheduledEntityListId;
    }

    @XmlTransient
    public Set<PushedScheduledActionCompanies> getPushedScheduledActionCompaniesSet() {
        return pushedScheduledActionCompaniesSet;
    }

    public void setPushedScheduledActionCompaniesSet(Set<PushedScheduledActionCompanies> pushedScheduledActionCompaniesSet) {
        this.pushedScheduledActionCompaniesSet = pushedScheduledActionCompaniesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pushedScheduledEntityListId != null ? pushedScheduledEntityListId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PushedScheduledEntityList)) {
            return false;
        }
        PushedScheduledEntityList other = (PushedScheduledEntityList) object;
        if ((this.pushedScheduledEntityListId == null && other.pushedScheduledEntityListId != null) || (this.pushedScheduledEntityListId != null && !this.pushedScheduledEntityListId.equals(other.pushedScheduledEntityListId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbittech.model.PushedScheduledEntityList[ pushedScheduledEntityListId=" + pushedScheduledEntityListId + " ]";
    }
    
}
