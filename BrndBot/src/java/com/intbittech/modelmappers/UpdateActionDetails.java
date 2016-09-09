/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

/**
 *
 * @author ajit
 */
public class UpdateActionDetails {
    
    private Integer scheduleId;
    private Integer userAssignToId;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getUserAssignToId() {
        return userAssignToId;
    }

    public void setUserAssignToId(Integer userAssignToId) {
        this.userAssignToId = userAssignToId;
    }
}
