/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import java.util.List;

/**
 * <code> {@link CompanyDao} </code> Interface to get Company details
 * from Company table
 *
 * @author Ajit
 */
public interface CompanyDao {
    
     /**
     * This method retrieves the list of {@link Company} from database.
     *
     * @return {@link Company}
     * @throws ProcessFailed the process failed
     */
    public List<Company> getAllCompanies() throws ProcessFailed;
    
    /**
     * This method saves {@link Company} to database.
     *
     * @param company
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public Integer save(Company company) throws ProcessFailed;
    
}
