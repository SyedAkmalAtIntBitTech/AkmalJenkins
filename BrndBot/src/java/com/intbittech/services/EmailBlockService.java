/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlock;

/**
 * <code>{@link EmailBlockService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface EmailBlockService {
    
    /**
     * This method pass id as input and get the {@link EmailBlock} from
     * database via Dao.
     *
     * @param emailBlockId the emailBlockId
     * @return {@link EmailBlock}
     * @throws ProcessFailed the process failed
     */
    public EmailBlock getByEmailBlockId(Integer emailBlockId) throws ProcessFailed;

    /**
     * This method save {@link EmailBlock} into the database via Dao.
     *
     * @param emailBlock the emailBlock
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailBlock emailBlock) throws ProcessFailed;

    /**
     * This method update {@link EmailBlock} updates existing data from the
     * database via Dao.
     *
     * @param emailBlock the emailBlock
     * @throws ProcessFailed the process failed
     */
    public void update(EmailBlock emailBlock) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailBlock} based on the EmailBlock
     * from the database via Dao.
     *
     * @param emailBlockId the emailBlockId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer emailBlockId) throws ProcessFailed;
    
}
