/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PushedScheduledActionCompanies;
import com.intbittech.modelmappers.PushedScheduledActionCompaniesDetails;
import com.intbittech.modelmappers.SendReminderEmailDetails;
import com.intbittech.modelmappers.UserDetails;
import java.sql.Timestamp;
import java.util.List;

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
     * @param pushedScheduledActionCompaniesId the pushedScheduledActionCompaniesId
     * @return {@link PushedScheduledActionCompanies}
     * @throws ProcessFailed the process failed
     */
    public PushedScheduledActionCompanies getByPushedScheduledActionCompaniesId(Integer pushedScheduledActionCompaniesId) throws ProcessFailed;
    
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
     * @param pushedScheduledActionCompaniesId the pushedScheduledActionCompaniesId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer pushedScheduledActionCompaniesId) throws ProcessFailed;
    
     /**
     * This method pass id as input and get the {@link PushedScheduledActionCompanies} from DAO
     * layer.
     * @param ScheduledEntityListId the ScheduledEntityListId
     * @return {@link PushedScheduledActionCompanies}
     * @throws ProcessFailed the process failed
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByScheduledEntityListId(Integer ScheduledEntityListId) throws ProcessFailed;

     /**
     * This method pass id as input and get the {@link PushedScheduledActionCompanies} from DAO
     * layer.
     * @param ScheduledEntityListId the ScheduledEntityListId
     * @return {@link PushedScheduledActionCompanies}
     * @throws ProcessFailed the process failed
     */
    public List<PushedScheduledActionCompanies> getPushedScheduledActionCompaniesByScheduledEntityListIdAndStatus(Integer ScheduledEntityListId, String Status) throws ProcessFailed;
    
     /**
     * This method pass id as input and get the {@link PushedScheduledActionCompanies} from DAO
     * layer.
     * @param franchiseId the franchiseId
     * @return {@link PushedScheduledActionCompanies}
     * @throws ProcessFailed the process failed
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByFranchiseId(Integer franchiseId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link PushedScheduledActionCompanies} from DAO
     * layer.
     * @param companyId the companyId
     * @return {@link PushedScheduledActionCompanies}
     * @throws ProcessFailed the process failed
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByCompanyId(Integer companyId) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link PushedScheduledActionCompanies} from DAO
     * layer.
     * @param companyId the companyId
     * @return {@link PushedScheduledActionCompanies}
     * @throws ProcessFailed the process failed
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByCompanyIdAndDateDifference(Integer companyId, Timestamp fromDate, Timestamp toDate) throws ProcessFailed;
    
     /**
     * This method save {@link PushedScheduledActionCompanies} into the database.
     *
     * @param pushedScheduledActionCompaniesDetails the pushedScheduledActionCompaniesDetails
     * @throws ProcessFailed the process failed
     */
    public void savePushedScheduledActionCompanies(PushedScheduledActionCompaniesDetails pushedScheduledActionCompaniesDetails) throws ProcessFailed; 
    
     /**
     * This method pass id as input and get the {@link SendGridSubUserDetails} from DAO
     * layer.
     * @param companyIds the companyIds
     * @return {@link SendGridSubUserDetails}
     * @throws ProcessFailed the process failed
     */
    public List<UserDetails> getAllUserDetailsOfCompanyIdForSendEmail(SendReminderEmailDetails sendReminderEmailDetails) throws ProcessFailed;
}
