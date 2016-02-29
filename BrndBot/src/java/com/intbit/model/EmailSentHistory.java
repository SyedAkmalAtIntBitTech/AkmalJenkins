/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbit.model;

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
 * @author ajit
 */
@Entity
@Table(name = "email_sent_history", schema = "public"
)
@TypeDefs({
    @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class EmailSentHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_sent_history_id")
    private Integer emailSentHistoryId;
    @Column(name = "time_sent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSent;
    @Column(name = "content_html")
    private String contentHtml;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "email_list_name")
    private String emailListName;
    @Column(name = "email_tag")
    private String emailTag;
    @Column(name = "subject")
    private String subject;
    @Column(name = "to_email_addresses")
    @Type(type = "StringJsonObject")
    private String toEmailAddresses;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;

    public EmailSentHistory() {
    }

    public EmailSentHistory(Integer emailSentHistoryId) {
        this.emailSentHistoryId = emailSentHistoryId;
    }

    public Integer getEmailSentHistoryId() {
        return emailSentHistoryId;
    }

    public void setEmailSentHistoryId(Integer emailSentHistoryId) {
        this.emailSentHistoryId = emailSentHistoryId;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailListName() {
        return emailListName;
    }

    public void setEmailListName(String emailListName) {
        this.emailListName = emailListName;
    }

    public String getEmailTag() {
        return emailTag;
    }

    public void setEmailTag(String emailTag) {
        this.emailTag = emailTag;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getToEmailAddresses() {
        return toEmailAddresses;
    }

    public void setToEmailAddresses(String toEmailAddresses) {
        this.toEmailAddresses = toEmailAddresses;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

}
