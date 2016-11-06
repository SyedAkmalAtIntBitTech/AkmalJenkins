/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.responsemappers.OperationStatus;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.sendgrid.models.SendGridCNameValidity;
import com.intbittech.sendgrid.models.SendGridStatsList;
import com.intbittech.sendgrid.models.SendGridUser;
import com.intbittech.sendgrid.models.SendGridUsers;
import com.intbittech.sendgrid.models.SubUserAPIKey;
import com.intbittech.sendgrid.models.Subuser;
import com.sendgrid.Mail;
import java.util.Date;
import java.util.List;

/**
 * <code>{@link EmailServiceProviderService}</code> is service layer interface for
 * communicating between Controller and EmailProviderAPI
 *
 * @author AR
 */
public interface EmailServiceProviderService {

    
    /**
     * Creates a sub user in the send grid DB
     * @param subuser
     * @return SendGridUser
     * @throws ProcessFailed 
     */
    public SendGridUser addSubuser(Subuser subuser) throws ProcessFailed;
    
    /**
     * Creates a sub user api key
     * @param subUserId
     * @return SubUserAPIKey
     * @throws ProcessFailed 
     */
    public SubUserAPIKey createSubUserAPIKey(String subUserId) throws ProcessFailed;
    
    /**
     * Reset password of a sub user
     * @param subUserId
     * @param newPassword
     * @param oldPassword
     * @return Boolean
     * @throws ProcessFailed 
     */
    public Boolean changePassword(String subUserId, String newPassword, String oldPassword) throws ProcessFailed;

    /**
     * Returns details of all the sub users of the account
     * @return SendGridUsers
     * @throws ProcessFailed 
     */
    public SendGridUsers getAllSubusers() throws ProcessFailed;
    
    /**
     * Returns sub user details for a given user name
     * @param userName
     * @return SendGridUser
     * @throws ProcessFailed 
     */
    public SendGridUser getSubuserDetails(String userName) throws ProcessFailed;
    
    /**
     * Validating CName for given sub user id
     * @param sendGridUserId
     * @return SendGridCNameValidity
     * @throws ProcessFailed 
     */
    public SendGridCNameValidity validateCNAME(String sendGridUserId) throws ProcessFailed;
    
    /**
     * Send Email. This is only method that needs to be used across the app the send emails.
     * No other method.
     * @param mail
     * @param emailType
     * @return OperationStatus
     * @throws ProcessFailed 
     */
    public OperationStatus sendEmail(Mail mail, EmailType emailType, Integer companyId, String fromEmailId) throws ProcessFailed;
    
    /**
     * Get all statistics based off the category. 
     * @param userId can be null
     * @param categories Category could be max of 9 strings.
     * @return SendGridStatsList
     * @throws ProcessFailed 
     */
    public SendGridStatsList getStatsByCategory(String userId, List<String> categories, Date startDate, Date endDate, Integer companyId) throws ProcessFailed;

}
