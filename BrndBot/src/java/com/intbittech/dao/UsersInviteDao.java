/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Invite;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UsersInviteDao {
    
    
    /**
     * This method pass companyInvite as input and get the {@link Integer} from database
     *
     * @param companyInvite
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public Integer save(Invite companyInvite) throws ProcessFailed;
    
    /**
     * This method pass companyInvite as input and updates the {@link Invite} from database
     *
     * @param companyInvite
     * @throws ProcessFailed the process failed
     */
    public void update(Invite companyInvite) throws ProcessFailed;
    /**
     * This method pass companyInvite as input and deletes the {@link Invite} from database
     *
     * @param companyInvite
     * @throws ProcessFailed the process failed
     */
    public void delete(Invite companyInvite) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link Invite} from database
     *
     * @param Id
     * @return {@link Invite}
     * @throws ProcessFailed the process failed
     */
    public Invite getInvitedUserById(Integer Id) throws ProcessFailed;
    
        /**
     * This method pass inviteCode as input and get the {@link Invite} from database
     *
     * @param inviteCode
     * @return {@link Invite}
     * @throws ProcessFailed the process failed
     */
    public Invite getUserByInviteCode(String inviteCode) throws ProcessFailed;
    
    
}
