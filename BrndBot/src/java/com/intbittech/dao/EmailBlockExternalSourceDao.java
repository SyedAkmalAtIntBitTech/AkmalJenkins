/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockExternalSource;
import java.util.List;

/**
 * <code> {@link EmailBlockExternalSourceDao} </code> Interface to get
 * EmailBlockExternalSource details from email_block_external_source table
 *
 * @author ilyas
 */
public interface EmailBlockExternalSourceDao {
    
    /**
     * This method pass id as input and get the {@link EmailBlockExternalSource}
     * from database.
     *
     * @param emailBlockExternalSourceId is the emailBlockExternalSourceId
     * @return {@link EmailBlockModelLookup}
     * @throws ProcessFailed the process failed
     */
    public EmailBlockExternalSource getEmailBlockExternalSourceById(Integer emailBlockExternalSourceId) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link EmailBlockExternalSource}
     * from database.
     *
     * @param emailBlockId is the emailBlockId
     * @return list of {@link EmailBlockExternalSource}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockExternalSource> getAllEmailBlockExternalSource(Integer emailBlockId) throws ProcessFailed;

    /**
     * This method save {@link EmailBlockExternalSource} into the database.
     *
     * @param emailBlockExternalSource the emailBlockExternalSource
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailBlockExternalSource emailBlockExternalSource) throws ProcessFailed;

    /**
     * This method {@link EmailBlockExternalSource} updates existing data from the
     * database.
     *
     * @param emailBlockExternalSource the emailBlockExternalSource
     * @throws ProcessFailed the process failed
     */
    public void update(EmailBlockExternalSource emailBlockExternalSource) throws ProcessFailed;

    /**
     * This method {@link EmailBlockExternalSource} deletes existing data from the
     * database.
     *
     * @param emailBlockExternalSource the emailBlockExternalSource
     * @throws ProcessFailed the process failed
     */
    public void delete(EmailBlockExternalSource emailBlockExternalSource) throws ProcessFailed;
    
}
