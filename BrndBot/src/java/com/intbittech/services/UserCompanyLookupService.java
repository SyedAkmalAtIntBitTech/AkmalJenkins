/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserCompanyDetails;
import com.intbittech.model.UserCompanyLookup;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import java.util.List;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UserCompanyLookupService {
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersCompanyLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public boolean isCompanyExist(UserCompanyLookup usersCompanyLookup) throws ProcessFailed;    
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersCompanyLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public String save(UserCompanyLookup usersCompanyLookup) throws ProcessFailed;
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param usersCompanyLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public String getStatus(UserCompanyDetails userCompanyDetails)throws ProcessFailed;    
    /**
     * This method pass usersRoleLookup as input and updates the {@link UsersRoleCompanyLookup} from DAO layer.
     *
     * @param usersCompanyLookup
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public void update(UserCompanyLookup usersCompanyLookup)throws ProcessFailed;
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
    public UserCompanyLookup getUserCompanyLookupById(Integer Id) throws ProcessFailed;

   /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param user
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UserCompanyLookup getUserCompanyLookupByUser(Users user) throws ProcessFailed;

   /**
     * This method pass user as input and get the {@link UsersRoleCompanyLookup} from database
     *
     * @param user
     * @return {@link UsersRoleCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public UserCompanyLookup getUserCompanyLookupByUserAndCompany(Users user, Company company) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link UserRole} from DAO layer
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
