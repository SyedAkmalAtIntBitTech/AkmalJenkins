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
public class CommentLogResponse {

    private String commentName;
    private String commentByFirstName;
    private String commentByLastName;
    private String commentByEmailId;
    private Date   createdAt;
    private Integer scheduledEntityListId;
    private Boolean isLoginUser;
    private String initials;
    
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

    public String getCommentByFirstName() {
        return commentByFirstName;
    }

    public void setCommentByFirstName(String commentByFirstName) {
        this.commentByFirstName = commentByFirstName;
    }

    public String getCommentByLastName() {
        return commentByLastName;
    }

    public void setCommentByLastName(String commentByLastName) {
        this.commentByLastName = commentByLastName;
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

    public String getCommentByEmailId() {
        return commentByEmailId;
    }

    public void setCommentByEmailId(String commentByEmailId) {
        this.commentByEmailId = commentByEmailId;
    }
    
    
}
