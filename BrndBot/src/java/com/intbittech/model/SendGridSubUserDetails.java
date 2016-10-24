/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author ajit @ Intbit
 */
@Entity
@Table(name = "send_grid_sub_user_details", schema = "public"
)
@TypeDefs({
    @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class SendGridSubUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "send_grid_sub_user_details_id")
    private Integer sendGridSubUserDetailsId;
    @Basic(optional = false)
    @Column(name = "send_grid_user_id")
    private String sendGridUserId;
    @Column(name = "ips")
    @Type(type = "StringJsonObject")
    private String ips;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users fkUserId;
    @Column(name = "email_api_key")
    @Type(type = "StringJsonObject")
    private String emailAPIKey;

    public SendGridSubUserDetails() {
    }

    public SendGridSubUserDetails(Integer sendGridSubUserDetailsId) {
        this.sendGridSubUserDetailsId = sendGridSubUserDetailsId;
    }

    public SendGridSubUserDetails(Integer sendGridSubUserDetailsId, String sendGridUserId, String ips) {
        this.sendGridSubUserDetailsId = sendGridSubUserDetailsId;
        this.sendGridUserId = sendGridUserId;
        this.ips = ips;
    }

    public Integer getSendGridSubUserDetailsId() {
        return sendGridSubUserDetailsId;
    }

    public void setSendGridSubUserDetailsId(Integer sendGridSubUserDetailsId) {
        this.sendGridSubUserDetailsId = sendGridSubUserDetailsId;
    }

    public String getSendGridUserId() {
        return sendGridUserId;
    }

    public void setSendGridUserId(String sendGridUserId) {
        this.sendGridUserId = sendGridUserId;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

    public Users getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Users fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getEmailAPIKey() {
        return emailAPIKey;
    }

    public void setEmailAPIKey(String emailAPIKey) {
        this.emailAPIKey = emailAPIKey;
    }
}
