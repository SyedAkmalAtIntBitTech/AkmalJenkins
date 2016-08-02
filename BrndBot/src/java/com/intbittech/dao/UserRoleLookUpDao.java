/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UserRole;
import com.intbittech.model.UserRoleLookup;
import com.intbittech.model.Users;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UserRoleLookUpDao {

/**
     * This method pass userRoleLookup as input and get the {@link Integer} from database
     *
     * @param userRoleLookup
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public boolean isRoleExist(UserRoleLookup userRoleLookup) throws ProcessFailed;    
    /**
     * This method pass userRoleLookup as input and get the {@link Integer} from database
     *
     * @param userRoleLookup
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public Integer save(UserRoleLookup userRoleLookup) throws ProcessFailed;
    
    /**
     * This method pass userRoleLookup as input and updates the {@link UserRoleLookup} from database
     *
     * @param userRoleLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(UserRoleLookup userRoleLookup) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link UserRoleLookup} from database
     *
     * @param Id
     * @return {@link UserRoleLookup}
     * @throws ProcessFailed the process failed
     */
    public UserRoleLookup getUsersRoleLookupById(Integer Id) throws ProcessFailed;

    /**
     * This method pass user as input and get the {@link UserRole} from database
     *
     * @param user
     * @return {@link UserRole}
     * @throws ProcessFailed the process failed
     */
    public UserRole getUsersRoleByUserId(Users user) throws ProcessFailed;
    
}
