/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.enums;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public enum ActivityStatus {
    ACTIVITY_CREATED_ACTION_ID(1), ACTIVITY_ASSIGNED_TO_ID(2),
    ACTIVITY_REASSIGNED_TO_ID(3), ACTIVITY_ADDED_TEMPLATE_ID(4),
    ACTIVITY_UPDATED_TEMPLATE_ID(5), ACTIVITY_REMOVED_TEMPLATE_ID(6), 
    ACTIVITY_UPDATED_ACTION_ID(7), ACTIVITY_APPROVED_ACTION_ID(8), 
    ACTIVITY_DISAPPROVED_ACTION_ID(9), ACTIVITY_DELETED_COMMENT_ACTION_ID(10),
    ACTIVITY_PLAY_ACTION_ID(11), ACTIVITY_PAUSE_ACTION_ID(12);
    
    private ActivityStatus(Integer displayName) {
        this.displayName = displayName;
    }
    
    private Integer displayName;
    
    public Integer getDisplayName(){
        return this.displayName;
    }

}
