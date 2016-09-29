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
 * @author ajit @ Intbit
 */
public class SendReminderEmailDetails extends UserCompanyIds implements Serializable{

    private List<Integer> companyIds;
    private Integer emailListTagId;

    public List<Integer> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Integer> companyIds) {
        this.companyIds = companyIds;
    }

    public Integer getEmailListTagId() {
        return emailListTagId;
    }

    public void setEmailListTagId(Integer emailListTagId) {
        this.emailListTagId = emailListTagId;
    }

}
