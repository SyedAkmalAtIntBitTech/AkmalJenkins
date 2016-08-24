/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.Franchise;
import com.intbittech.model.FranchiseCompanyLookup;
import java.util.List;

/**
 * <code> {@link FranchiseDao} </code> Interface to get Franchise details from
 * Franchise table
 *
 * @author AbdulRaqeeb
 */
public interface FranchiseCompanyLookupDao {
   
    /**
     * This method pass id as input and get the {@link Franchise} from DAO
     * layer.
     *
     * @param franchise the Franchise
     * @return {@link Company}
     * @throws ProcessFailed the process failed
     */
    public List<Company> getCompanyForFranchiseId(Franchise franchise) throws ProcessFailed;
    
     /**
     * This method pass id as input and get the {@link FranchiseCompanyLookup} from DAO
     * layer.
     *
     * @param company the company
     * @return {@link FranchiseCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<Franchise> getFranchiseForCompanyId(Company company) throws ProcessFailed;
    
    /**
     * This method save {@link Franchise} into the database.
     *
     * @param franchiseCompanyLookup the FranchiseCompanyLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(FranchiseCompanyLookup franchiseCompanyLookup) throws ProcessFailed;

    /**
     * This method update {@link FranchiseCompanyLookup} updates existing data from the
     * database.
     *
     * @param franchiseCompanyLookup the FranchiseCompanyLookup
     * @throws ProcessFailed the process failed
     */
    public void update(FranchiseCompanyLookup franchiseCompanyLookup) throws ProcessFailed;

    /**
     * This method delete particular {@link FranchiseCompanyLookup} based on the
     * organization from the database.
     *
     * @param franchiseCompanyLookup the FranchiseCompanyLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(FranchiseCompanyLookup franchiseCompanyLookup) throws ProcessFailed;

    public FranchiseCompanyLookup getFranchiseLookup(Integer companyId, Integer franchiseId)throws ProcessFailed;
    
    public FranchiseCompanyLookup getFranchiseHeadquarter(Integer franchiseId)throws ProcessFailed;
}
