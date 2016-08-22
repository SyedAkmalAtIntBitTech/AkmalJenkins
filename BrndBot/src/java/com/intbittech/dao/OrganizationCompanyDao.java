/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationCompanyLookup;
import java.util.List;

/**
 * <code> {@link OrganizationCompanyDao} </code> Interface to get Company details
 * from organization_company_lookup table
 *
 * @author ilyas
 */
public interface OrganizationCompanyDao {
    
    /**
     * This method retrieves the list of {@link OrganizationCompanyLookup} from database.
     *
     * @return list of {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationCompanyLookup> getAllOrganizationCompanies() throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from database.
     *
     * @param companyId
     * @return {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public OrganizationCompanyLookup getOrganizationCompanyById(Integer companyId) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from database.
     *
     * @param companyId
     * @return list of {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationCompanyLookup> getAllOrganizationCompanyById(Integer companyId) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from database.
     *
     * @param companyId
     * @return list of {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationCompanyLookup> getAllOrganizationsByCompanyId(Integer companyId) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from database.
     *
     * @param organizationCompanyLookup
     * @return {@link organizationCompanyLookupId}
     * @throws ProcessFailed the process failed
     */
    public Integer save(OrganizationCompanyLookup organizationCompanyLookup) throws ProcessFailed;

    /**
     * This method updates the {@link OrganizationCompanyLookup} in database.
     *
     * @param organizationCompanyLookup
     * @return {@link organizationCompanyLookupId}
     * @throws ProcessFailed the process failed
     */
    public void update(OrganizationCompanyLookup organizationCompanyLookup) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from database.
     *
     * @param organizationCompanyLookupId
     * @throws ProcessFailed the process failed
     */
    public void delete(OrganizationCompanyLookup organizationCompanyLookup) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from database.
     *
     * @param organizationCompanyLookupId
     * @return {@link OrganizationCompanyLookup}
     * @throws ProcessFailed the process failed
     */
    public OrganizationCompanyLookup getById(Integer organizationCompanyLookupId) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link Organization} from database.
     *
     * @param organizationIds
     * @return list of {@link Organization}
     * @throws ProcessFailed the process failed
     */
    public List<Organization> getNonAddedGroups(Integer[] organizationIds) throws ProcessFailed;
    
}
