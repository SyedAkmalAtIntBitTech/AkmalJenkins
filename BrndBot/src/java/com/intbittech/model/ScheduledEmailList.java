/**
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
 * @author ajit
 */
@Entity
@Table(name = "scheduled_email_list", schema = "public"
)
@TypeDefs({
    @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class ScheduledEmailList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduled_email_list_id")
    private Integer scheduledEmailListId;
    @Column(name = "subject")
    private String subject;
    @Column(name = "body")
    private String body;
    @Column(name = "from_address")
    private String fromAddress;
    @Column(name = "email_list_name")
    private String emailListName;
    @Column(name = "from_name")
    private String fromName;
    @Column(name = "to_email_addresses")
    @Type(type = "StringJsonObject")
    private String toEmailAddresses;
    @Column(name = "reply_to_email_address")
    private String replyToEmailAddress;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @JoinColumn(name = "fk_scheduled_entity_list_id", referencedColumnName = "scheduled_entity_list_id")
    @ManyToOne
    private ScheduledEntityList fkScheduledEntityListId;

    public ScheduledEmailList() {
    }

    public ScheduledEmailList(Integer scheduledEmailListId) {
        this.scheduledEmailListId = scheduledEmailListId;
    }

    public Integer getScheduledEmailListId() {
        return scheduledEmailListId;
    }

    public void setScheduledEmailListId(Integer scheduledEmailListId) {
        this.scheduledEmailListId = scheduledEmailListId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getEmailListName() {
        return emailListName;
    }

    public void setEmailListName(String emailListName) {
        this.emailListName = emailListName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToEmailAddresses() {
        return toEmailAddresses;
    }

    public void setToEmailAddresses(String toEmailAddresses) {
        this.toEmailAddresses = toEmailAddresses;
    }

    public String getReplyToEmailAddress() {
        return replyToEmailAddress;
    }

    public void setReplyToEmailAddress(String replyToEmailAddress) {
        this.replyToEmailAddress = replyToEmailAddress;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

    public ScheduledEntityList getFkScheduledEntityListId() {
        return fkScheduledEntityListId;
    }

    public void setFkScheduledEntityListId(ScheduledEntityList fkScheduledEntityListId) {
        this.fkScheduledEntityListId = fkScheduledEntityListId;
    }

}
