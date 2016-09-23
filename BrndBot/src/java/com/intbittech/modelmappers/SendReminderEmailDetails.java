/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ajit @ Intbit
 */
public class SendReminderEmailDetails implements Serializable {

    private List<Integer> comapnyIds;
    private Integer emailListTagId;

    public List<Integer> getComapnyIds() {
        return comapnyIds;
    }

    public void setComapnyIds(List<Integer> comapnyIds) {
        this.comapnyIds = comapnyIds;
    }

    public Integer getEmailListTagId() {
        return emailListTagId;
    }

    public void setEmailListTagId(Integer emailListTagId) {
        this.emailListTagId = emailListTagId;
    }

}
