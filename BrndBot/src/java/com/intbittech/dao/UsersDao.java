/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;

/**
 * <code> {@link UsersDao} </code> Interface to get User details from
 * users table
 *
 * @author ilyas
 */
public interface UsersDao {
    
    /**
     * This method pass user as input and get the {@link Boolean} from database
     *
     * @param user
     * @return {@link Boolean}
     * @throws ProcessFailed the process failed
     */
    public Boolean checkUniqueUser(Users user) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Integer} from database
     *
     * @param user
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public Integer save(Users user) throws ProcessFailed;
    
    /**
     * This method pass user as input and updates the {@link Users} from database
     *
     * @param user
     * @throws ProcessFailed the process failed
     */
    public void update(Users user) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Users} from database
     *
     * @param userId
     * @return {@link Users}
     * @throws ProcessFailed the process failed
     */
    public Users getUserById(Integer userId) throws ProcessFailed;
    
}
