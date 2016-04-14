/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;

/**
 *
 * @author Haider Khan @ Intbit
 */
public interface UsersService {
    /**
     * 
     * @param userName
     * @return
     * @throws ProcessFailed 
     */
    Users findByUserName(String userName) throws ProcessFailed;
}
