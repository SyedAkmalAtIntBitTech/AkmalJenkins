/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UsersRoleLookup;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UsersRoleLookUpDao {

    /**
     * This method pass userRoleLookup as input and get the {@link Integer} from database
     *
     * @param userRoleLookup
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public Integer save(UsersRoleLookup userRoleLookup) throws ProcessFailed;
    
    /**
     * This method pass userRoleLookup as input and updates the {@link UsersRoleLookup} from database
     *
     * @param userRoleLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(UsersRoleLookup userRoleLookup) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link UsersRoleLookup} from database
     *
     * @param Id
     * @return {@link UsersRoleLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleLookup getUsersRoleLookupById(Integer Id) throws ProcessFailed;
    
}
