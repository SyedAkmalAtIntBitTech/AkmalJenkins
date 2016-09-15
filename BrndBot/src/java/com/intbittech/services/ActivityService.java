/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Activity;
import java.util.List;

/**
 * <code>{@link ActivityService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface ActivityService {
      /**
     * This method save {@link Activity} into the database.
     *
     * @param activity the activity
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(Activity activity) throws ProcessFailed;
    
    /**
     * This method update {@link Activity} updates existing data from the
     * database.
     *
     * @param activity the activity
     * @throws ProcessFailed the process failed
     */
    public void update(Activity activity) throws ProcessFailed;

    /**
     * This method delete particular {@link Activity} based on the
     * activity from the database.
     *
     * @param activityId the activityId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer activityId) throws ProcessFailed;
    
    
     /**
     * This method retrieves the list of {@link Activity} from DAO layer.
     *
     * @return {@link Activity}
     * @throws ProcessFailed the process failed
     */
    public List<Activity> getAllActivity() throws ProcessFailed;
    
    /**
     * This method retrieves the list of {@link Activity} from DAO layer.
     *
     * @param activityId the activityId
     * @return {@link Activity}
     * @throws ProcessFailed the process failed
     */
    public Activity getActivityByActivityId(Integer activityId) throws ProcessFailed;
}
