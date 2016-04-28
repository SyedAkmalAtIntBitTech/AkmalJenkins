/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.UserDetails;

/**
 *
 * @author ilyas
 */
public interface UsersService {
    
    /**
     * 
     * @param userName
     * @return
     * @throws com.intbittech.exception.ProcessFailed
     * @throws ProcessFailed 
     */
    Users findByUserName(String userName) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Boolean} from DAO layer.
     *
     * @param user
     * @return {@link Boolean}
     * @throws ProcessFailed the process failed
     */
    public Boolean checkUniqueUser(Users user) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Integer} from DAO layer.
     *
     * @param user
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public String save(UserDetails usersDetails) throws ProcessFailed;
    
    /**
     * This method pass user as input and updates the {@link Users} from DAO layer.
     *
     * @param user
     * @throws ProcessFailed the process failed
     */
    public void update(Users user) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Users} from DAO layer
     *
     * @param userId
     * @return {@link Users}
     * @throws ProcessFailed the process failed
     */
    public Users getUserById(Integer userId) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link Users} from DAO layer
     *
     * @param emailId
     * @return {@link Users}
     * @throws ProcessFailed the process failed
     */
    public Users getUserByEmailId(String emailId) throws ProcessFailed;
    
}
