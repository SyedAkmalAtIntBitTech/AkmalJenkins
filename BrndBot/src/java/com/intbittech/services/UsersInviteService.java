/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyInvite;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.UserDetails;
import org.json.simple.JSONObject;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UsersInviteService {
    
    /**
     * This method pass user as input and get the {@link Integer} from DAO layer.
     *
     * @param companyInvite
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public String save(CompanyInvite companyInvite) throws ProcessFailed;
    
    /**
     * This method pass user as input and updates the {@link Users} from DAO layer.
     *
     * @param user
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer id) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Users} from DAO layer
     *
     * @param Id
     * @return {@link Users}
     * @throws ProcessFailed the process failed
     */
    public CompanyInvite getInvitedUserById(Integer Id) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Users} from DAO layer
     *
     * @param inviteCode
     * @return {@link Users}
     * @throws ProcessFailed the process failed
     */
    public CompanyInvite getInvitedUserByInviteCode(String inviteCode) throws ProcessFailed;

    public void sendMail(String from_email_id,String to_email_id, String imageContextPath, JSONObject task);    
}
