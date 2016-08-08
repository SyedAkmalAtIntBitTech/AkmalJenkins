/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Invite;
import com.intbittech.model.UserRole;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.model.Users;
import java.util.List;

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
    public boolean isRoleExist(UsersRoleLookup userRoleLookup) throws ProcessFailed;    
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
    public void update(UsersRoleLookup userRoleLookup) throws ProcessFailed;
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

    /**
     * This method pass user as input and get the {@link UsersRoleLookup} from database
     *
     * @param userId
     * @return {@link UsersRoleLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleLookup getUsersRoleLookupByUserId(Users userId) throws ProcessFailed;

   /**
     * This method pass user as input and get the {@link UsersRoleLookup} from database
     *
     * @param userId
     * @return {@link UsersRoleLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleLookup getUsersRoleLookupByUserAndRoleId(Users user, UserRole role) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link UserRole} from database
     *
     * @param user
     * @return {@link UserRole}
     * @throws ProcessFailed the process failed
     */
    public UserRole getUsersRoleByUserId(Users user) throws ProcessFailed;
    /**
     * This method retrieves the list of {@link SubCategory} from Database.
     *
     * @param user the categoryId
     * @return {@link SubCategory}
     * @throws ProcessFailed the process failed
     */
    public List<UsersRoleLookup> getAllUserRolesByUserId(Users user) throws ProcessFailed;    
}
