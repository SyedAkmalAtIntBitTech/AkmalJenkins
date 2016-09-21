/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModelLookup;
import java.util.List;

/**
 * <code>{@link EmailBlockModelLookupService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface EmailBlockModelLookupService {
    
    /**
     * This method pass id as input and get the {@link EmailBlockModelLookup} from
     * database via Dao.
     *
     * @param emailBlockModelLookupId the emailBlockModelLookupId
     * @return {@link EmailBlockModelLookup}
     * @throws ProcessFailed the process failed
     */
    public EmailBlockModelLookup getByEmailBlockModelLookupId(Integer emailBlockModelLookupId) throws ProcessFailed;

    /**
     * This method save {@link EmailBlockModelLookup} into the database via Dao.
     *
     * @param emailBlockModelLookup the emailBlockModelLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed;

    /**
     * This method update {@link EmailBlockModelLookup} updates existing data from the
     * database via Dao.
     *
     * @param emailBlockModelLookup the emailBlockModelLookup
     * @throws ProcessFailed the process failed
     */
    public void update(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailBlockModelLookup} based on the EmailBlock
     * from the database via Dao.
     *
     * @param emailBlockModelLookupId the emailBlockModelLookupId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer emailBlockModelLookupId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailBlockModelLookup} from
     * database via Dao.
     *
     * @param emailBlockId the emailBlockId
     * @param isRecurring
     * @return List of {@link EmailBlockModelLookup}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockModelLookup> getAllEmailBlockModel(Integer emailBlockId, Boolean isRecurring) throws ProcessFailed;

    public EmailBlockModelLookup getByEmailModelId(Integer emailModelId) throws ProcessFailed;
    
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
