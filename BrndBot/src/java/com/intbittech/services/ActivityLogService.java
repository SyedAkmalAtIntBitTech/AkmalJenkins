/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ActivityLog;
import com.intbittech.modelmappers.ActivityLogDetails;
import com.intbittech.responsemappers.ActivityLogResponse;
import java.util.List;

/**
 * <code>{@link ActivityLogService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface ActivityLogService {
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
    public List<ActivityLogResponse> getAllActivityLog() throws ProcessFailed;
    
     /**
     * This method retrieves the list of {@link ActivityLog} from DAO layer.
     * @param scheduledEntityListId the scheduledEntityListId
     * @return {@link ActivityLog}
     * @throws ProcessFailed the process failed
     */
    public List<ActivityLogResponse> getAllActivityLogByScheduledEntityListId(Integer scheduledEntityListId ) throws ProcessFailed;
    
    /**
     * This method retrieves the list of {@link ActivityLog} from DAO layer.
     * @param activityLogId the activityLogId
     * @return {@link ActivityLog}
     * @throws ProcessFailed the process failed
     */
    public ActivityLog getActivityLogByActivityLogId(Integer activityLogId ) throws ProcessFailed;
    
      /**
     * This method save {@link ActivityLog} into the database.
     *
     * @param activityLogDetails the activityLogDetails
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public void saveActivityLog(ActivityLogDetails activityLogDetails) throws ProcessFailed;       

      /**
     * This method save {@link ActivityLog} into the database.
     *
     * @param activityLogDetails the activityLogDetails
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public void activityLogSave(ActivityLogDetails activityLogDetails) throws ProcessFailed;

}
