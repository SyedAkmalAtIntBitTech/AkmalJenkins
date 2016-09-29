/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UnsubscribedEmails;

/**
 *
 * @author ajit @ Intbit
 */
public interface UnsubscribedEmailsDao {
    
    /**
     * This method pass id as input and get the {@link UnsubscribedEmails} from Database.
     *
     * @param unsubscribedEmailsId the unsubscribedEmailsId
     * @return {@link UnsubscribedEmails}
     * @throws ProcessFailed the process failed
     */
    public UnsubscribedEmails getByUnsubscribedEmailsId(Integer unsubscribedEmailsId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link UnsubscribedEmails} from Database.
     *
     * @param emailAddress the emailAddress
     * @return {@link UnsubscribedEmails}
     * @throws ProcessFailed the process failed
     */
    public UnsubscribedEmails getByUnsubscribedEmailsAddress(String emailAddress) throws ProcessFailed;
    
    /**
     * This method save {@link UnsubscribedEmails} into the database.
     *
     * @param unsubscribedEmails the unsubscribedEmails
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(UnsubscribedEmails unsubscribedEmails) throws ProcessFailed;

    /**
     * This method update {@link UnsubscribedEmails} updates existing data from the
     * database.
     *
     * @param unsubscribedEmails the unsubscribedEmails
     * @throws ProcessFailed the process failed
     */
    public void update(UnsubscribedEmails unsubscribedEmails) throws ProcessFailed;

    /**
     * This method delete particular {@link UnsubscribedEmails} based on the
     * contact from the database.
     *
     * @param unsubscribedEmails the unsubscribedEmails
     * @throws ProcessFailed the process failed
     */
    public void delete(UnsubscribedEmails unsubscribedEmails) throws ProcessFailed;
    
}
