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
@Table(name = "email_list")
public class EmailList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_list_id")
    private Integer emailListId;
    @Column(name = "email_list_name")
    private String emailListName;
    @Column(name = "description")
    private String description;
    @Column(name = "default_from_address")
    private String defaultFromAddress;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "fk_type_id", referencedColumnName = "type_id")
    @ManyToOne
    private EmailListType fkTypeId;
    
    

    public EmailList() {
    }

    public EmailList(Integer emailListId) {
        this.emailListId = emailListId;
    }

    public Integer getEmailListId() {
        return emailListId;
    }

    public void setEmailListId(Integer emailListId) {
        this.emailListId = emailListId;
    }

    public String getEmailListName() {
        return emailListName;
    }

    public void setEmailListName(String emailListName) {
        this.emailListName = emailListName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultFromAddress() {
        return defaultFromAddress;
    }

    public void setDefaultFromAddress(String defaultFromAddress) {
        this.defaultFromAddress = defaultFromAddress;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public EmailListType getFkTypeId() {
        return fkTypeId;
    }

    public void setFkTypeId(EmailListType fkTypeId) {
        this.fkTypeId = fkTypeId;
    }
    
}
