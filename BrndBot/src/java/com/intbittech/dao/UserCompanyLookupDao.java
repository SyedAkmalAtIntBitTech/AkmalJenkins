/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserCompanyLookup;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import java.util.List;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UserCompanyLookupDao {
    /**
     * This method pass userCompanyLookup as input and get the {@link Integer} from database
     *
     * @param userCompanyLookup
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public boolean isCompanyExist(UserCompanyLookup userCompanyLookup) throws ProcessFailed;  
    /**
     * This method pass userCompanyLookup as input and get the {@link Integer} from database
     *
     * @param userCompanyLookup
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public Integer save(UserCompanyLookup userCompanyLookup) throws ProcessFailed;
    
    /**
     * This method pass userCompanyLookup as input and updates the {@link UsersRoleCompanyLookup} from database
     *
     * @param userCompanyLookup
     * @throws ProcessFailed the process failed
     */
    public void update(UserCompanyLookup userCompanyLookup) throws ProcessFailed;
    /**
     * This method pass userCompanyLookup as input and updates the {@link UsersRoleCompanyLookup} from database
     *
     * @param userCompanyLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(UserCompanyLookup userCompanyLookup) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param Id
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UserCompanyLookup getUsersCompanyLookupById(Integer Id) throws ProcessFailed;

    /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param userId
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UserCompanyLookup getUsersCompanyLookupByUser(Users userId) throws ProcessFailed;

   /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param userId
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UserCompanyLookup getUsersCompanyLookupByUserAndCompany(Users user, Company role) throws ProcessFailed;
    
    /**
     * This method pass user as input and get the {@link UserRole} from database
     *
     * @param user
     * @return {@link UserRole}
     * @throws ProcessFailed the process failed
     */
    public UserCompanyLookup getUsersCompanyByUser(Users user) throws ProcessFailed;
    /**
     * This method retrieves the list of {@link SubCategory} from Database.
     *
     * @param user the categoryId
     * @return {@link SubCategory}
     * @throws ProcessFailed the process failed
     */
    public List<UserCompanyLookup> getAllUserCompaniesByUser(Users user) throws ProcessFailed;    
    
}
