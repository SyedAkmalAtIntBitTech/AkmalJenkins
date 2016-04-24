/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ForgotPassword;
import com.intbittech.model.Users;

/**
 * <code> {@link UsersDao} </code> Interface to get User details from
 * users table
 *
 * @author ilyas
 */
public interface ForgotPasswordDao {
    
   
    public Integer save(ForgotPassword forgotPassword) throws ProcessFailed;
    
    /**
     * This method pass user as input and updates the {@link Users} from database
     *
     * @param user
     * @throws ProcessFailed the process failed
     */
    public void update(ForgotPassword forgotPassword) throws ProcessFailed;

    public void delete(ForgotPassword forgotPassword)  throws ProcessFailed;

    public ForgotPassword getByRandomHash(String hashURL) throws ProcessFailed;
    
    
    
}
