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
    private String commentByName;
    private Date createdAt;
    private Integer scheduledEntityListId;

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentByName() {
        return commentByName;
    }

    public void setCommentByName(String commentByName) {
        this.commentByName = commentByName;
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

}
