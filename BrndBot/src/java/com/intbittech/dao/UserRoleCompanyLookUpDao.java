/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserRole;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.model.Users;
import java.util.List;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UserRoleCompanyLookUpDao {

/**
     * This method pass userRoleLookup as input and get the {@link Integer} from database
     *
     * @param userRoleLookup
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public boolean isRoleExist(UsersRoleCompanyLookup userRoleLookup) throws ProcessFailed;    
    /**
     * This method pass userRoleLookup as input and get the {@link Integer} from database
     *
     * @param userRoleLookup
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public Integer save(UsersRoleCompanyLookup userRoleLookup) throws ProcessFailed;
    
    /**
     * This method pass userRoleLookup as input and updates the {@link UsersRoleCompanyLookup} from database
     *
     * @param userRoleLookup
     * @throws ProcessFailed the process failed
     */
    public void update(UsersRoleCompanyLookup userRoleLookup) throws ProcessFailed;
    /**
     * This method pass userRoleLookup as input and updates the {@link UsersRoleCompanyLookup} from database
     *
     * @param userRoleLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(UsersRoleCompanyLookup userRoleLookup) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
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
     * @param userId
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleCompanyLookup getUsersRoleLookupByUserAndRole(Users user, UserRole role) throws ProcessFailed;

   /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param user
     * @param company
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleCompanyLookup getUsersRoleLookupByUserAndCompany(Users user, Company company) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link UserRole} from database
     *
     * @param user
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
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param userRoleName the userRoleName
     * @param companyId the companyId
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UsersRoleCompanyLookup getUsersRoleCompanyLookupByUserRoleIdAndCompanyId(String userRoleName, Integer companyId) throws ProcessFailed;
    
   /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param userRolesName the userRolesName
     * @param companyIds the companyIds
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<UsersRoleCompanyLookup> getAllUsersRoleCompanyLookupByuserRolesNameAndCompanyId(List<String> userRolesName, List<Integer> companyIds) throws ProcessFailed;
}
