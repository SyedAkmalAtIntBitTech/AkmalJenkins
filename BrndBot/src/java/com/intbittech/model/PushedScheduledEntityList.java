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
@Table(name = "pushed_scheduled_entity_list")
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
    @Column(name = "editable")
    private Boolean editable;
    @JoinColumn(name = "fk_franchise_id", referencedColumnName = "franchise_id")
    @ManyToOne(optional = false)
    private Franchise fkFranchiseId;
    @JoinColumn(name = "fk_scheduled_entity_list_id", referencedColumnName = "scheduled_entity_list_id")
    @ManyToOne(optional = false)
    private ScheduledEntityList fkScheduledEntityListId;
    
    public PushedScheduledEntityList() {
    }

    public PushedScheduledEntityList(Integer pushedScheduledEntityListId) {
        this.pushedScheduledEntityListId = pushedScheduledEntityListId;
    }

    public PushedScheduledEntityList(Integer pushedScheduledEntityListId, boolean autoApproved) {
        this.pushedScheduledEntityListId = pushedScheduledEntityListId;
        this.autoApproved = autoApproved;
        
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

     
    
}
