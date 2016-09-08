/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ActivityLog;
import java.util.List;

/**
 *
 * @author ajit @ Intbit
 */
public interface ActivityLogDao {
    
     /**
     * This method save {@link ActivityLog} into the database.
     *
     * @param activityLog the activityLog
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(ActivityLog activityLog) throws ProcessFailed;
    
     /**
     * This method retrieves the list of {@link ActivityLog} from DAO layer.
     *
     * @return {@link ActivityLog}
     * @throws ProcessFailed the process failed
     */
    public List<ActivityLog> getAllActivityLog() throws ProcessFailed;
    
     /**
     * This method retrieves the list of {@link ActivityLog} from DAO layer.
     * @param scheduledEntityListId the scheduledEntityListId
     * @return {@link ActivityLog}
     * @throws ProcessFailed the process failed
     */
    public List<ActivityLog> getAllActivityLogByScheduledEntityListId(Integer scheduledEntityListId ) throws ProcessFailed;
    
    /**
     * This method retrieves the list of {@link ActivityLog} from DAO layer.
     * @param activityLogId the activityLogId
     * @return {@link ActivityLog}
     * @throws ProcessFailed the process failed
     */
    public ActivityLog getActivityLogByActivityLogId(Integer activityLogId ) throws ProcessFailed;
}
