/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserCompanyDetails;
import com.intbittech.model.UserRole;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.model.Users;
import java.util.List;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UserRoleCompanyLookUpService {

    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public boolean isRoleExist(UsersRoleCompanyLookup usersRoleLookup) throws ProcessFailed;    
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public String save(UsersRoleCompanyLookup usersRoleLookup) throws ProcessFailed;
    /**
     * This method pass usersRoleLookup as input and updates the {@link UsersRoleLookup} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed    /**
     * This method pass usersRoleLookup as input and updates the {@link UsersRoleLookup} from DAO layer.
     *
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    
    /**
     * This method pass usersRoleLookup as input and updates the {@link UsersRoleCompanyLookup} from DAO layer.
     * @param usersRoleLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public void update(UsersRoleCompanyLookup usersRoleLookup)throws ProcessFailed;
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
    public UsersRoleCompanyLookup getUsersRoleLookupByLookUpId(Integer Id) throws ProcessFailed;

   /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param user
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleCompanyLookup getUsersRoleLookupByUser(Users user) throws ProcessFailed;

   /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param user
     * @param role
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleCompanyLookup getUsersRoleLookupByUserAndRole(Users user, UserRole role) throws ProcessFailed;

   /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param user
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleCompanyLookup getUsersRoleLookupByUserAndCompany(Users user, Company company) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link UserRole} from DAO layer
     *
     * @param Users
     * @return {@link UserRole}
     * @throws ProcessFailed the process failed
     */
    public UserRole getUsersRoleByUser(Users user) throws ProcessFailed;    
    /**
     * This method retrieves the list of {@link SubCategory} from Database.
     *
     * @param user the categoryId
     * @return {@link SubCategory}
     * @throws ProcessFailed the process failed
     */
    public List<UsersRoleCompanyLookup> getAllUserRolesByUser(Users user) throws ProcessFailed;    
    /**
     * This method pass userCompanyDetails as input and get the {@link String} from DAO layer.
     *
     * @param usersCompanyDetails
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public String getStatus(UserCompanyDetails userCompanyDetails)throws ProcessFailed;
  
     /**
     * This method retrieves the list of {@link UsersRoleCompanyLookup} from Database.
     *
     * @param companyId the companyId
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<UsersRoleCompanyLookup> getAllUsersRoleCompanyLookupByCompanyId(Integer companyId) throws ProcessFailed;  
}
    

