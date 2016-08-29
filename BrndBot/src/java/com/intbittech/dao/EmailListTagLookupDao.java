/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTagLookup;

/**
 *
 * @author ajit @ Intbit
 */
public interface EmailListTagLookupDao {
     /**
     * This method pass id as input and get the {@link EmailListTagLookup} from DAO
     * layer.
     *
     * @param emailListTagLookupId the emailListTagLookupId
     * @return {@link EmailListTagLookup}
     * @throws ProcessFailed the process failed
     */
    public EmailListTagLookup getByEmailListTagLookupId(Integer emailListTagLookupId) throws ProcessFailed;
    
    /**
     * This method save {@link EmailListTagLookup} into the database.
     *
     * @param emailListTagLookup the emailListTagLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailListTagLookup emailListTagLookup) throws ProcessFailed;

    /**
     * This method update {@link EmailListTagLookup} updates existing data from the
     * database.
     *
     * @param emailListTagLookup the emailListTagLookup
     * @throws ProcessFailed the process failed
     */
    public void update(EmailListTagLookup emailListTagLookup) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailListTagLookup} based on the
     * EmailListTagLookup from the database.
     *
     * @param emailListTagLookup the emailListTagLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(EmailListTagLookup emailListTagLookup) throws ProcessFailed;
    
    
}
