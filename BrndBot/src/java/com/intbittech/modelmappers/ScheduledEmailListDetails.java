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
public class ScheduledEmailListDetails {
    
    private Integer scheduledEmailListId;
    private String subject;
    private String body;
    private String fromAddress;
    private String emailListName;
    private String fromName;
    private String replyToEmailAddress;
    private Integer ScheduledEntityListId;

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

    public String getReplyToEmailAddress() {
        return replyToEmailAddress;
    }

    public void setReplyToEmailAddress(String replyToEmailAddress) {
        this.replyToEmailAddress = replyToEmailAddress;
    }

    public Integer getScheduledEntityListId() {
        return ScheduledEntityListId;
    }

    public void setScheduledEntityListId(Integer ScheduledEntityListId) {
        this.ScheduledEntityListId = ScheduledEntityListId;
    }
    
    
    
    
}
