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
public class PushedActionDetails {
    
    private String pushedActionName;
    private Long pushedactionDateTime;
    private Integer scheduledEntityListId;
    private Integer entityId;

    public String getPushedActionName() {
        return pushedActionName;
    }

    public void setPushedActionName(String pushedActionName) {
        this.pushedActionName = pushedActionName;
    }

    public Long getPushedactionDateTime() {
        return pushedactionDateTime;
    }

    public void setPushedactionDateTime(Long pushedactionDateTime) {
        this.pushedactionDateTime = pushedactionDateTime;
    }

    public Integer getScheduledEntityListId() {
        return scheduledEntityListId;
    }

    public void setScheduledEntityListId(Integer scheduledEntityListId) {
        this.scheduledEntityListId = scheduledEntityListId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
    
    
    
}
