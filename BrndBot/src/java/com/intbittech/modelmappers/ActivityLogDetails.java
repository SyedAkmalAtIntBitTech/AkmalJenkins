/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

/**
 *
 * @author ajit @ Intbit
 */
public class ActivityLogDetails {
    
    private Integer activityLogId;
    private Integer activityId;
    private Integer scheduledEntityId;
    private Integer assignedTo;
    private Integer createdBy;

    public Integer getActivityLogId() {
        return activityLogId;
    }

    public void setActivityLogId(Integer activityLogId) {
        this.activityLogId = activityLogId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getScheduledEntityId() {
        return scheduledEntityId;
    }

    public void setScheduledEntityId(Integer scheduledEntityId) {
        this.scheduledEntityId = scheduledEntityId;
    }

    public Integer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    
    
    
}
