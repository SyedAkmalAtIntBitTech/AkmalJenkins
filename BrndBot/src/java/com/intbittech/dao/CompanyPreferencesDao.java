/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;

/**
 * <code> {@link CategoryDao} </code> Interface to get Category details from
 * Category table
 *
 * @author Ajit
 */
public interface CompanyPreferencesDao {

    public void saveOrUpdate(CompanyPreferences companyPreferences);

    public CompanyPreferences getByCompany(Company company);
    
    
    
    
    
}