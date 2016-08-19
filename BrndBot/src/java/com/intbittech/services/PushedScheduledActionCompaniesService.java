/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PushedScheduledActionCompanies;

/**
 * <code>{@link PushedScheduledActionCompaniesService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface PushedScheduledActionCompaniesService {
    
    /**
     * This method pass id as input and get the {@link PushedScheduledActionCompanies} from DAO
     * layer.
     * @param pushedScheduledActionCompanies the pushedScheduledActionCompanies
     * @return {@link PushedScheduledActionCompanies}
     * @throws ProcessFailed the process failed
     */
    public PushedScheduledActionCompanies getByPushedScheduledActionCompaniesId(Integer pushedScheduledActionCompanies) throws ProcessFailed;
    
    /**
     * This method save {@link PushedScheduledActionCompanies} into the database.
     *
     * @param pushedScheduledActionCompanies the pushedScheduledActionCompanies
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed;

    /**
     * This method update {@link PushedScheduledActionCompanies} updates existing data from the
     * database.
     *
     * @param pushedScheduledActionCompanies the pushedScheduledActionCompanies
     * @throws ProcessFailed the process failed
     */
    public void update(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed;

    /**
     * This method delete particular {@link PushedScheduledActionCompanies} based on the
     * Pushed Scheduled Action Companies from the database.
     *
     * @param pushedScheduledActionCompanies the pushedScheduledActionCompanies
     * @throws ProcessFailed the process failed
     */
    public void delete(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed;
  
    
}
