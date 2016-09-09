/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;

/**
 *
 * @author ajit @ Intbit
 */
public class CommentLogDetails extends UserCompanyIds{
    
    private Integer commentLogId;
    private String comment;
    private Integer scheduleId;

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

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

   
    
    
    
}
