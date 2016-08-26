/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.modelmappers.CategoryDetails;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.sendgrid.models.SendGridStats;
import com.intbittech.sendgrid.models.SendGridUser;
import com.intbittech.sendgrid.models.Subuser;
import com.sendgrid.Mail;
import com.sendgrid.Response;
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
     * @return
     * @throws ProcessFailed 
     */
    public SendGridUser addSubuser(Subuser subuser) throws ProcessFailed;

    /**
     * Returns details of all the sub users of the account
     * @return
     * @throws ProcessFailed 
     */
    public Response getAllSubusers() throws ProcessFailed;
    
    /**
     * Returns sub user details for a given user name
     * @param userName
     * @return
     * @throws ProcessFailed 
     */
    public SendGridUser getSubserDetails(String userName) throws ProcessFailed;

    
    /**
     * Validating CName for given sub user id
     * @param sendGridUserId
     * @return
     * @throws ProcessFailed 
     */
    public Response validateCNAME(String sendGridUserId) throws ProcessFailed;

    
    
    /**
     * Send Email. This is only method that needs to be used across the app the send emails.
     * No other method.
     * @param mail
     * @param emailType
     * @return
     * @throws ProcessFailed 
     */
    public Response sendEmail(Mail mail, EmailType emailType) throws ProcessFailed;
    
    /**
     * Get all statistics based off the category. 
     * @param userId can be null
     * @param categories Category could be max of 9 strings.
     * @throws ProcessFailed 
     */
    public SendGridStats getStatsByCategory(String userId, List<String> categories, Date startDate, Date endDate) throws ProcessFailed;

}
