/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Activity;
import java.util.List;

/**
 * <code> {@link ActivityDao} </code> Interface to get Activity details from
 * Category table
 *
 * @author Ajit
 */
public interface ActivityDao {
    
      /**
     * This method save {@link Category} into the database.
     *
     * @param activity the activity
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(Activity activity) throws ProcessFailed;
    
     /**
     * This method retrieves the list of {@link Activity} from DAO layer.
     *
     * @return {@link Activity}
     * @throws ProcessFailed the process failed
     */
    public List<Activity> getAllActivity() throws ProcessFailed;
    
}
