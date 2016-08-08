/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

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
public interface UserRoleLookUpService {

    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public boolean isRoleExist(UsersRoleLookup usersRoleLookup) throws ProcessFailed;    
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public String save(UsersRoleLookup usersRoleLookup) throws ProcessFailed;
    /**
     * This method pass usersRoleLookup as input and updates the {@link UsersRoleLookup} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    
    public void update(UsersRoleLookup usersRoleLookup)throws ProcessFailed;
    /**
     * This method pass id as input and deletes the {@link UsersRoleLookup} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer id) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link UsersRoleLookup} from DAO layer
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
    public UsersRoleLookup getUsersRoleLookupByUserAndRoleId(Users userId, UserRole roleId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link UserRole} from DAO layer
     *
     * @param Users
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
