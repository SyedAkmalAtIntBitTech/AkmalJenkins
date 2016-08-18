/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ActivityDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Activity;
import com.intbittech.services.ActivityService;
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
public class ActivityServiceImpl implements ActivityService {

    private static Logger logger = Logger.getLogger(ActivityServiceImpl.class);
    @Autowired
    private ActivityDao activityDao;

    /**
     * {@inheritDoc}
     */
    public Integer save(Activity activity) throws ProcessFailed {
        return activityDao.save(activity);
    }

    /**
     * {@inheritDoc}
     */
    public List<Activity> getAllActivity() throws ProcessFailed {
        List<Activity> activityList = activityDao.getAllActivity();
        if (activityList == null) {
            throw new ProcessFailed("No activity found.");
        }
        return activityList;
    }

    /**
     * {@inheritDoc}
     */
    public void update(Activity activity) throws ProcessFailed {
       activityDao.update(activity);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer activityId) throws ProcessFailed {
        Activity activity = activityDao.getActivityByactivityId(activityId);
        if (activity == null) {
            throw new ProcessFailed("No activity with id" + activityId + ".");
        }
        activityDao.delete(activity);
    }

    /**
     * {@inheritDoc}
     */
    public Activity getActivityByactivityId(Integer activityId) throws ProcessFailed {
        Activity activity = activityDao.getActivityByactivityId(activityId);
        if (activity == null) {
            throw new ProcessFailed("No activity with id" + activityId + ".");
        }
        return activity;
    }

}
