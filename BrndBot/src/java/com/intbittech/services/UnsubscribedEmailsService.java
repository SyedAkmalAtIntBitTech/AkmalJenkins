/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UnsubscribedEmails;
import java.util.List;

/**
 * <code>{@link UnsubscribedEmailsService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface UnsubscribedEmailsService {
    
    /**
     * This method pass id as input and get the {@link UnsubscribedEmails} from DAO
     * layer.
     *
     * @param unsubscribedEmailsId the unsubscribedEmailsId
     * @return {@link UnsubscribedEmails}
     * @throws ProcessFailed the process failed
     */
    public UnsubscribedEmails getByUnsubscribedEmailsId(Integer unsubscribedEmailsId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link UnsubscribedEmails} from DAO
     * layer.
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
    public Integer save(Integer companyId, List<String> emailList) throws ProcessFailed;

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
     * @param unsubscribedEmailsId the unsubscribedEmailsId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer unsubscribedEmailsId) throws ProcessFailed;
}
