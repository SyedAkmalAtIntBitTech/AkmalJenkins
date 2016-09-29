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
@Table(name = "activity_log")
public class ActivityLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "activity_log_id")
    private Integer activityLogId;
    @Basic(optional = false)
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private Users createdBy;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "activity_id")
    @ManyToOne(optional = false)
    private Activity fkActivityId;
    @JoinColumn(name = "fk_Scheduled Entity _id", referencedColumnName = "scheduled_entity_list_id")
    @ManyToOne(optional = false)
    private ScheduledEntityList fkScheduledEntityid;
    @JoinColumn(name = "assigned_to", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users assignedTo;

    public ActivityLog() {
    }

    public ActivityLog(Integer activityLogId) {
        this.activityLogId = activityLogId;
    }

    public ActivityLog(Integer activityLogId,Date createdAt) {
        this.activityLogId = activityLogId;
        this.createdAt = createdAt;
    }

    public Integer getActivityLogId() {
        return activityLogId;
    }

    public void setActivityLogId(Integer activityLogId) {
        this.activityLogId = activityLogId;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Activity getFkActivityId() {
        return fkActivityId;
    }

    public void setFkActivityId(Activity fkActivityId) {
        this.fkActivityId = fkActivityId;
    }

    public ScheduledEntityList getFkScheduledEntityid() {
        return fkScheduledEntityid;
    }

    public void setFkScheduledEntityid(ScheduledEntityList fkScheduledEntityid) {
        this.fkScheduledEntityid = fkScheduledEntityid;
    }

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }

        
}
