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
public class CommentLogDetails {
    
    private Integer commentLogId;
    private String comment;
    private Integer scheduledEntityListId;

    public Integer getCommentLogId() {
        return commentLogId;
    }

    public void setCommentLogId(Integer commentLogId) {
        this.commentLogId = commentLogId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScheduledEntityListId() {
        return scheduledEntityListId;
    }

    public void setScheduledEntityListId(Integer scheduledEntityListId) {
        this.scheduledEntityListId = scheduledEntityListId;
    }
    
    
    
}
