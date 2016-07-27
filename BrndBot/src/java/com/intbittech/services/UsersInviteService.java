/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyInvite;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.InviteDetails;
import com.intbittech.modelmappers.UserDetails;
import org.json.simple.JSONObject;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UsersInviteService {
    
    /**
     * This method pass companyInvite as input and get the {@link String} from DAO layer.
     *
     * @param companyInvite
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public String save(CompanyInvite companyInvite) throws ProcessFailed;
    
    /**
     * This method pass user as input and updates the {@link CompanyInvite} from DAO layer.
     *
     * @param companyInvite
     * @throws ProcessFailed the process failed
     */
    public void update(CompanyInvite companyInvite) throws ProcessFailed;
    /**
     * This method pass id as input and deletes the {@link CompanyInvite} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer id) throws ProcessFailed;
    
    /**
     * This method pass Id as input and get the {@link CompanyInvite} from DAO layer
     *
     * @param Id
     * @return {@link CompanyInvite}
     * @throws ProcessFailed the process failed
     */
    public CompanyInvite getInvitedUserById(Integer Id) throws ProcessFailed;
    
    /**
     * This method pass inviteCode as input and get the {@link CompanyInvite} from DAO layer
     *
     * @param inviteCode
     * @return {@link CompanyInvite}
     * @throws ProcessFailed the process failed
     */
    public CompanyInvite getInvitedUserByInviteCode(String inviteCode) throws ProcessFailed;

    /**
     * This method pass fromEmailId,imageContextPath,inviteDetails as input and sends mail from Service layer
     *
     * @param fromEmailId
     * @param imageContextPath
     * @param inviteDetails
     * @throws ProcessFailed the process failed
     */
    public void sendMail(String fromEmailId, String imageContextPath, InviteDetails inviteDetails) throws ProcessFailed;    
    
    /**
     * This method pass inviteCode as input and get the {@link String} from Service layer
     *
     * @param inviteCode
     * @return {@link CompanyInvite}
     * @throws ProcessFailed the process failed
     */
    public boolean checkOutValidity(String inviteCode);
}
