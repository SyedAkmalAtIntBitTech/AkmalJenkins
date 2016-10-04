//TODO remove this
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "email_mandrill_history")
public class EmailMandrillHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_mandrill_history_id")
    private Integer emailMandrillHistoryId;
    @Column(name = "to_email")
    private String toEmail;
    @Column(name = "status")
    private String status;
    @Column(name = "reject_reason")
    private String rejectReason;
    @Column(name = "mandrill_email_id")
    private String mandrillEmailId;

    public EmailMandrillHistory() {
    }

    public EmailMandrillHistory(Integer emailMandrillHistoryId) {
        this.emailMandrillHistoryId = emailMandrillHistoryId;
    }

    public Integer getEmailMandrillHistoryId() {
        return emailMandrillHistoryId;
    }

    public void setEmailMandrillHistoryId(Integer emailMandrillHistoryId) {
        this.emailMandrillHistoryId = emailMandrillHistoryId;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getMandrillEmailId() {
        return mandrillEmailId;
    }

    public void setMandrillEmailId(String mandrillEmailId) {
        this.mandrillEmailId = mandrillEmailId;
    }

}
