/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;

/**
 *
 * @author Haider Khan @ Intbit
 */

public interface UsersDao {
    /**
     * This method get user detail  {@link Category} based on the
     * organization from the database.
     * 
     * @param userName
     * @return user detail from users table
     * @throws ProcessFailed the process failed
     */
    Users findByUserName(String userName) throws ProcessFailed;
}
