/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.responsemappers;

import java.util.Date;

/**
 *
 * @author ajit @ Intbit
 */
public class CommentActivityLogResponse implements Comparable<CommentActivityLogResponse> {

    private String commentName;
    private String activityName;
    private String createdByFirstName;
    private String createdByLastName;
    private String createdByByEmailId;
    private Integer createdByUserId;
    private Date createdAt;
    private Integer scheduledEntityListId;
    private Boolean isLoginUser;
    private String initials;
    private Integer commentId;
    private String assignedToFirstName;
    private String assignedToLastName;
    private String assignedToEmailId;
    private Boolean isActivity;

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCommentName() {
        return commentName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getScheduledEntityListId() {
        return scheduledEntityListId;
    }

    public void setScheduledEntityListId(Integer scheduledEntityListId) {
        this.scheduledEntityListId = scheduledEntityListId;
    }

    public Boolean getIsLoginUser() {
        return isLoginUser;
    }

    public void setIsLoginUser(Boolean isLoginUser) {
        this.isLoginUser = isLoginUser;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCreatedByFirstName() {
        return createdByFirstName;
    }

    public void setCreatedByFirstName(String createdByFirstName) {
        this.createdByFirstName = createdByFirstName;
    }

    public String getCreatedByLastName() {
        return createdByLastName;
    }

    public void setCreatedByLastName(String createdByLastName) {
        this.createdByLastName = createdByLastName;
    }

    public String getCreatedByByEmailId() {
        return createdByByEmailId;
    }

    public void setCreatedByByEmailId(String createdByByEmailId) {
        this.createdByByEmailId = createdByByEmailId;
    }

    public String getAssignedToFirstName() {
        return assignedToFirstName;
    }

    public void setAssignedToFirstName(String assignedToFirstName) {
        this.assignedToFirstName = assignedToFirstName;
    }

    public String getAssignedToLastName() {
        return assignedToLastName;
    }

    public void setAssignedToLastName(String assignedToLastName) {
        this.assignedToLastName = assignedToLastName;
    }

    public String getAssignedToEmailId() {
        return assignedToEmailId;
    }

    public void setAssignedToEmailId(String assignedToEmailId) {
        this.assignedToEmailId = assignedToEmailId;
    }

    public Boolean getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Boolean isActivity) {
        this.isActivity = isActivity;
    }
    

    @Override
    public int compareTo(CommentActivityLogResponse o) {
        int value = 0;

        if (this.getCreatedAt().getTime() < o.getCreatedAt().getTime()) {
            value = -1;

        } else if (this.getCreatedAt().getTime() > o.getCreatedAt().getTime()) {
            value = 1;

        }
        return value;
    }

}
