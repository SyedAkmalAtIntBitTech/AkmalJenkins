/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;

/**
 *
 * @author ilyas
 */
public class TagAndEmailListDetails extends UserCompanyIds{
    private Integer emailListTagId;
    private Integer emailListId;
    private String emailListName;
    private Integer tagId;
    private String tagName;

    public Integer getEmailListTagId() {
        return emailListTagId;
    }

    public void setEmailListTagId(Integer emailListTagId) {
        this.emailListTagId = emailListTagId;
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

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
    
}
