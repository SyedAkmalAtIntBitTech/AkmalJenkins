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
@Table(name = "comment_log")
public class CommentLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comment_log_id")
    private Integer commentLogId;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "fk_Scheduled Entity _id", referencedColumnName = "scheduled_entity_list_id")
    @ManyToOne(optional = false)
    private ScheduledEntityList fkScheduledEntityid;
    @JoinColumn(name = "commented_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users commentedBy;

    public CommentLog() {
    }

    public CommentLog(Integer commentLogId) {
        this.commentLogId = commentLogId;
    }

    public CommentLog(Integer commentLogId, String comment, Date createdAt) {
        this.commentLogId = commentLogId;
        this.comment = comment;
        this.createdAt = createdAt;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ScheduledEntityList getFkScheduledEntityid() {
        return fkScheduledEntityid;
    }

    public void setFkScheduledEntityid(ScheduledEntityList fkScheduledEntityid) {
        this.fkScheduledEntityid = fkScheduledEntityid;
    }

    public Users getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(Users commentedBy) {
        this.commentedBy = commentedBy;
    }

    
}
