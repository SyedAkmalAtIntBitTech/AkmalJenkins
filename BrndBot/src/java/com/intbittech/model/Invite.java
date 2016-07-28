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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
@Entity
@Table(name = "invite", schema = "public"
)
@TypeDefs({
    @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})

public class Invite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "created_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;
    @Column(name = "message")
    private String message;
    @Column(name = "is_used")
    private Boolean isUsed;
    @Column(name = "task")
    @Type(type = "StringJsonObject")
    private String task;
    @JoinColumn(name = "invite_sent_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users inviteSentBy;
    @JoinColumn(name = "invite_sent_to", referencedColumnName = "user_id")
    @ManyToOne
    private Users inviteSentTo;

    public Invite() {
    }

    public Invite(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Users getInviteSentBy() {
        return inviteSentBy;
    }

    public void setInviteSentBy(Users inviteSentBy) {
        this.inviteSentBy = inviteSentBy;
    }

    public Users getInviteSentTo() {
        return inviteSentTo;
    }

    public void setInviteSentTo(Users inviteSentTo) {
        this.inviteSentTo = inviteSentTo;
    }

 
    
}
