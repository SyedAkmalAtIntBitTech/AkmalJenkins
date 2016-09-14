/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UserRole;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.model.Users;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UserRoleService {
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public boolean isRoleExist(UserRole userRole) throws ProcessFailed;    
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public String save(UserRole userRole) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link UsersRoleCompanyLookup} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer id) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link UsersRoleCompanyLookup} from DAO layer
     *
     * @param Id
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UserRole getUserRoleById(Integer Id) throws ProcessFailed;
    
}
