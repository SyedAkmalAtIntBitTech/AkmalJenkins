/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbit.model;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author ajit
 */
@Entity
@Table(name = "forgot_password")
public class ForgotPassword implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "forgot_password_id")
    private Integer forgotPasswordId;
    @Column(name = "random_link")
    private String randomLink;
    @Basic(optional = false)
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Column(name = "expiry_time")
    private BigInteger expiryTime;
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users fkUserId;

    public ForgotPassword() {
    }

    public ForgotPassword(Integer forgotPasswordId) {
        this.forgotPasswordId = forgotPasswordId;
    }

    public ForgotPassword(Integer forgotPasswordId, Date expiryDate) {
        this.forgotPasswordId = forgotPasswordId;
        this.expiryDate = expiryDate;
    }

    public Integer getForgotPasswordId() {
        return forgotPasswordId;
    }

    public void setForgotPasswordId(Integer forgotPasswordId) {
        this.forgotPasswordId = forgotPasswordId;
    }

    public String getRandomLink() {
        return randomLink;
    }

    public void setRandomLink(String randomLink) {
        this.randomLink = randomLink;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigInteger getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(BigInteger expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Users getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Users fkUserId) {
        this.fkUserId = fkUserId;
    }
}
