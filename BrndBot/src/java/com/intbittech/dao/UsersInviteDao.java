/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyInvite;
import com.intbittech.model.Users;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UsersInviteDao {
    
    
    /**
     * This method pass user as input and get the {@link Integer} from database
     *
     * @param companyInvite
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public Integer save(CompanyInvite companyInvite) throws ProcessFailed;
    
    /**
     * This method pass user as input and updates the {@link Users} from database
     *
     * @param companyInvite
     * @throws ProcessFailed the process failed
     */
    public void delete(CompanyInvite companyInvite) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Users} from database
     *
     * @param Id
     * @return {@link Users}
     * @throws ProcessFailed the process failed
     */
    public CompanyInvite getUserById(Integer Id) throws ProcessFailed;
    
        /**
     * This method pass user as input and get the {@link Users} from database
     *
     * @param inviteCode
     * @return {@link Users}
     * @throws ProcessFailed the process failed
     */
    public CompanyInvite getUserByInviteCode(String inviteCode) throws ProcessFailed;
    
    
}
