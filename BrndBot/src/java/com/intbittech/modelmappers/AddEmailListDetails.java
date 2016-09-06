/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ilyas
 */
public class AddEmailListDetails  extends UserCompanyIds implements Serializable{
    private Integer emailListId;
    private String emailListName;
    private String emailListDescription;
    private String defaultFromAddress;
    private String emailListType;
    private List<String> emailListTags;
    private Integer numberOfContacts;

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

    public String getEmailListDescription() {
        return emailListDescription;
    }

    public void setEmailListDescription(String emailListDescription) {
        this.emailListDescription = emailListDescription;
    }

    public String getDefaultFromAddress() {
        return defaultFromAddress;
    }

    public void setDefaultFromAddress(String defaultFromAddress) {
        this.defaultFromAddress = defaultFromAddress;
    }

    public String getEmailListType() {
        return emailListType;
    }

    public void setEmailListType(String emailListType) {
        this.emailListType = emailListType;
    }

    public List<String> getEmailListTags() {
        return emailListTags;
    }

    public void setEmailListTags(List<String> emailListTags) {
        this.emailListTags = emailListTags;
    }

    public Integer getNumberOfContacts() {
        return numberOfContacts;
    }

    public void setNumberOfContacts(Integer numberOfContacts) {
        this.numberOfContacts = numberOfContacts;
    }
}
