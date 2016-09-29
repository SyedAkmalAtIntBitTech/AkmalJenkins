/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SendGridSubUserDetails;

/**
 * <code> {@link SendGridSubUserDetailsDao} </code> Interface to get SendGridSubUserDetails details from
 * SendGridSubUserDetailsDao table
 *
 * @author Ajit
 */
public interface SendGridSubUserDetailsDao {
    
    /**
     * This method pass id as input and get the {@link SendGridSubUserDetails} from DAO
     * layer.
     *
     * @param sendGridSubUserDetailsId the sendGridSubUserDetailsId
     * @return {@link SendGridSubUserDetails}
     * @throws ProcessFailed the process failed
     */
    public SendGridSubUserDetails getBySendGridSubUserDetailsId(Integer sendGridSubUserDetailsId) throws ProcessFailed;
    
    /**
     * This method save {@link SendGridSubUserDetails} into the database.
     *
     * @param sendGridSubUserDetails the SendGridSubUserDetails
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed;

    /**
     * This method update {@link SendGridSubUserDetails} updates existing data from the
     * database.
     *
     * @param sendGridSubUserDetails the sendGridSubUserDetails
     * @throws ProcessFailed the process failed
     */
    public void update(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed;

    /**
     * This method delete particular {@link SendGridSubUserDetails} based on the
     * sendGridSubUserDetails from the database.
     *
     * @param sendGridSubUserDetails the sendGridSubUserDetails
     * @throws ProcessFailed the process failed
     */
    public void delete(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed;
    
     /**
     * This method pass id as input and get the {@link SendGridSubUserDetails} from DAO
     * layer.
     *
     * @param sendGridSubUserId the sendGridSubUserId
     * @param companyId the companyId
     * @return {@link SendGridSubUserDetails}
     * @throws ProcessFailed the process failed
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsBySendGridSubUserIdAndCompanyId(String sendGridSubUserId,Integer companyId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link SendGridSubUserDetails} from DAO
     * layer.
     *
     * @param companyId the companyId
     * @return {@link SendGridSubUserDetails}
     * @throws ProcessFailed the process failed
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsByCompanyId(Integer companyId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link SendGridSubUserDetails} from DAO
     * layer.
     *
     * @param sendGridSubUserId the sendGridSubUserId
     * @return {@link SendGridSubUserDetails}
     * @throws ProcessFailed the process failed
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsBySendGridSubUserId(String sendGridSubUserId) throws ProcessFailed;
}
