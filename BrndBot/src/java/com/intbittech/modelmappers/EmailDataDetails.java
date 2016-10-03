/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;
import java.io.Serializable;

/**
 *
 * @author ilyas
 */
public class EmailDataDetails extends UserCompanyIds implements Serializable {
    
    private String emailSubject;
    private String htmlData;
    private String emailListName;
    private String replyToEmailAddress;
    private String fromEmailAddress;
    private String fromName;
    private String iFrameName;
    //change to enum
    private String emailCategory;
    
    private enum emailGenre {
        General, Recurring;
    }
    
    private Integer days;

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }

    public String getEmailListName() {
        return emailListName;
    }

    public void setEmailListName(String emailListName) {
        this.emailListName = emailListName;
    }

    public String getReplyToEmailAddress() {
        return replyToEmailAddress;
    }

    public void setReplyToEmailAddress(String replyToEmailAddress) {
        this.replyToEmailAddress = replyToEmailAddress;
    }

    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getiFrameName() {
        return iFrameName;
    }

    public void setiFrameName(String iFrameName) {
        this.iFrameName = iFrameName;
    }

    public String getEmailCategory() {
        return emailCategory;
    }

    public void setEmailCategory(String emailCategory) {
        this.emailCategory = emailCategory;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
    
    
}
