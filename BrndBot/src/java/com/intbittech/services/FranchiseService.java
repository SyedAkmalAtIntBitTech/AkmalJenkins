/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.model.Company;
import com.intbittech.model.Franchise;
import com.intbittech.model.FranchiseCompanyLookup;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.FranchiseDetails;
import java.util.List;

/**
 * <code>{@link CategoryService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author AR
 */
public interface FranchiseService {

    public boolean activateCompanyAsFranchise(Integer companyId, Integer franchiseId) throws ProcessFailed;

    public void associateCompanyToFranchise(Integer companyId, Users user, Integer franchiseId) throws ProcessFailed;

    public void removeCompanyFromFranchise(Integer companyId, Integer franchiseId) throws ProcessFailed;

    public List<Company> getCompaniesForFranchises(Integer franchiseId) throws ProcessFailed;

    public List<Franchise> getFranchisesForCompanyId(Integer companyId) throws ProcessFailed;

      /**
     * This method pass id as input and get the {@link Category} from DAO layer.
     *
     * @param categoryId the categoryId
     * @return {@link Category}
     * @throws ProcessFailed the process failed
     */
    public List<Franchise> getAllFranchises() throws ProcessFailed;

    /**
     * This method delete particular {@link Category} based on the organization
     * from the database.
     *
     * @param categoryId the categoryId
     * @throws ProcessFailed the process failed
     */
    public void deleteFranchise(Integer franchiseId) throws ProcessFailed;

    /**
     * This method update {@link Category} updates existing data from the
     * database.
     *
     * @param category the category
     * @throws ProcessFailed the process failed
     */
    public void updateFranchise(FranchiseDetails franchiseDetails, Integer franchiseId) throws ProcessFailed;

     /**
     * This method save {@link Category} into the database.
     *
     * @param category the Category
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public void saveFranchise(FranchiseDetails franchiseDetails) throws ProcessFailed;

    public FranchiseCompanyLookup getFranchiseLookup(Integer companyId, Integer franchiseId)throws ProcessFailed;
    
    public String getFranchiseHeadquarter(Integer franchiseId)throws ProcessFailed;
    
    /**
     * This method delete particular {@link FranchiseCompanyLookup} based on the
     * organization from the database.
     *
     * @param companyId the companyId
     * @return FranchiseCompanyLookup
     * @throws ProcessFailed the process failed
     */
    public FranchiseCompanyLookup getFranchiseByCompanyId(Integer companyId)throws ProcessFailed;

    
}
