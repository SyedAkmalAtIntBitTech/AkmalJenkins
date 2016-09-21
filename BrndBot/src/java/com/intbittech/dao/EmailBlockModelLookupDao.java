/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModelLookup;
import java.util.List;

/**
 * <code> {@link EmailBlockModelLookupDao} </code> Interface to get
 * EmailBlockModelLookup details from email_block_model_lookup table
 *
 * @author ilyas
 */
public interface EmailBlockModelLookupDao {

    /**
     * This method pass id as input and get the {@link EmailBlockModelLookup}
     * from database.
     *
     * @param emailBlockModelLookupId is the emailBlockModelLookupId
     * @return {@link EmailBlockModelLookup}
     * @throws ProcessFailed the process failed
     */
    public EmailBlockModelLookup getEmailBlockModelLookupById(Integer emailBlockModelLookupId) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link EmailBlockModelLookup}
     * from database.
     *
     * @param emailBlockId is the emailBlockId
     * @param isRecurring
     * @return list of {@link EmailBlockModelLookup}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockModelLookup> getAllEmailBlockModel(Integer emailBlockId, Boolean isRecurring) throws ProcessFailed;

    /**
     * This method save {@link EmailBlockModelLookup} into the database.
     *
     * @param emailBlockModelLookup the emailBlockModelLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed;

    /**
     * This method {@link EmailBlockModelLookup} updates existing data from the
     * database.
     *
     * @param emailBlockModelLookup the emailBlockModelLookup
     * @throws ProcessFailed the process failed
     */
    public void update(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed;

    /**
     * This method {@link EmailBlockModelLookup} deletes existing data from the
     * database.
     *
     * @param emailBlockModelLookup the emailBlockModelLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed;

    public EmailBlockModelLookup getEmailBlockModelByEmailModelId(Integer emailModelId);
    
    /**
     * This method pass id as input and get the {@link EmailBlockModelLookup}
     * from database.
     *
     * @param emailBlockId is the emailBlockId
     * @return list of {@link EmailBlockModelLookup}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockModelLookup> getAllRecuringEmailBlockModel(Integer emailBlockId) throws ProcessFailed;

}
