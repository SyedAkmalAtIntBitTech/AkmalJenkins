/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationCompanyLookup;
import com.intbittech.modelmappers.CompanyDetails;
import java.util.List;

/**
 * <code>{@link CompanyService}</code> is service layer interface for communicating
 * between Controller and DAO classes
 *
 * @author Ajit
 */
public interface CompanyService {
    
    /**
     * This method retrieves the list of {@link Company} from DAO layer.
     *
     * @return {@link Company}
     * @throws ProcessFailed the process failed
     */
    public List<Company> getAllCompanies() throws ProcessFailed;
   /**
     * This method retrieves a company out of {@link Company} from database.
     *
     * @param companyId
     * @return {@link Company}
     * @throws ProcessFailed the process failed
     */
    public Company getCompanyById(Integer companyId) throws ProcessFailed;    
    /**
     * This method retrieves the list of {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @return list of {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationCompanyLookup> getAllOrganizationCompanies() throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @param companyId
     * @return {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public OrganizationCompanyLookup getOrganizationCompanyById(Integer companyId) throws ProcessFailed;
       
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @param companyId
     * @return list of {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationCompanyLookup> getAllOrganizationCompanyById(Integer companyId) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @param companyId
     * @return list of {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationCompanyLookup> getAllOrganizationsByCompanyId(Integer companyId) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @param organizationCompanyLookup
     * @return {@link organizationCompanyLookupId}
     * @throws ProcessFailed the process failed
     */
    public Integer save(OrganizationCompanyLookup organizationCompanyLookup) throws ProcessFailed;

    /**
     * This method updates the {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @param organizationCompanyLookup
     * @return {@link organizationCompanyLookupId}
     * @throws ProcessFailed the process failed
     */
    public void updateOrganizationCompanyLookUp(OrganizationCompanyLookup organizationCompanyLookup) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @param organizationCompanyLookupId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer organizationCompanyLookupId) throws ProcessFailed;
    
   /**
     * This method retrieves the {@link Organization} from DAO layer.
     *
     * @param organizationIds
     * @return list of {@link Organization}
     * @throws ProcessFailed the process failed
     */
    public List<Organization> getNonAddedGroups(Integer[] organizationIds) throws ProcessFailed;
    
    /**
     * This method saves {@link Company} to DAO layer.
     *
     * @param companyDetails
     * @throws ProcessFailed the process failed
     */
    public String saveCompany(CompanyDetails companyDetails) throws ProcessFailed;
    
    /**
     * This method saves {@link CompanyPreferences} to DAO layer.
     *
     * @param companyPreferences
     * @throws ProcessFailed the process failed
     */
    public Integer saveCompanyPreferences(CompanyPreferences companyPreferences) throws ProcessFailed;
}
