/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ActivityLogDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ActivityLog;
import com.intbittech.services.ActivityLogService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class ActivityLogServiceImpl implements ActivityLogService{
    
     private static Logger logger = Logger.getLogger(ActivityLogServiceImpl.class);
    @Autowired
    private ActivityLogDao activityLogDao;

    /**
     * {@inheritDoc}
     */
    public Integer save(ActivityLog activityLog) throws ProcessFailed {
        
        return activityLogDao.save(activityLog);
    }

   /**
     * {@inheritDoc}
     */
    public List<ActivityLog> getAllActivityLog() throws ProcessFailed {
           List<ActivityLog> activityLogList = activityLogDao.getAllActivityLog();
        if (activityLogList == null) {
            throw new ProcessFailed("No activity log found.");
        }
        return activityLogList;
    }
 /**
     * {@inheritDoc}
     */
   
    public List<ActivityLog> getAllActivityLogByScheduledEntityListId(Integer scheduledEntityListId) throws ProcessFailed {
        List<ActivityLog> activityLogList = activityLogDao.getAllActivityLogByScheduledEntityListId(scheduledEntityListId);
        if(activityLogList == null)
        {
             throw new ProcessFailed("No activity log found.");
        }
              return  activityLogList;
    }

   /**
     * {@inheritDoc}
     */
    public ActivityLog getActivityLogByActivityLogId(Integer activityLogId) throws ProcessFailed {
         ActivityLog activityLog = activityLogDao.getActivityLogByActivityLogId(activityLogId);
        if(activityLog == null)
        {
             throw new ProcessFailed("No activity log found.");
        }
              return  activityLog;
    }

    
}
