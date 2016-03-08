/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlock;

/**
 * <code> {@link EmailBlockDao} </code> Interface to get Email Block details
 * from email_block table
 *
 * @author ilyas
 */
public interface EmailBlockDao {

    /**
     * This method pass id as input and get the {@link EmailBlock} from
     * database.
     *
     * @param emailBlockId the emailBlockId
     * @return {@link EmailBlock}
     * @throws ProcessFailed the process failed
     */
    public EmailBlock getByEmailBlockId(Integer emailBlockId) throws ProcessFailed;

    /**
     * This method save {@link EmailBlock} into the database.
     *
     * @param emailBlock the emailBlock
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailBlock emailBlock) throws ProcessFailed;

    /**
     * This method update {@link EmailBlock} updates existing data from the
     * database.
     *
     * @param emailBlock the emailBlock
     * @throws ProcessFailed the process failed
     */
    public void update(EmailBlock emailBlock) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailBlock} based on the EmailBlock
     * from the database.
     *
     * @param emailBlock the emailBlock
     * @throws ProcessFailed the process failed
     */
    public void delete(EmailBlock emailBlock) throws ProcessFailed;

}
