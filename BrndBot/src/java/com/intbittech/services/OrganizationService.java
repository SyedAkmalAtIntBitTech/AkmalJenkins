/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Organization;
import java.util.List;

/**
 * <code>{@link OrganizationService}</code> is service layer interface for communicating
 * between Controller and DAO classes
 *
 * @author Ajit
 */
public interface OrganizationService {
    
     /**
     * This method pass id as input and get the {@link Organization} from DAO
     * layer.
     *
     * @param id the id
     * @return {@link Organization}
     * @throws ProcessFailed the process failed
     */
    public Organization getById(Integer id) throws ProcessFailed;

    /**
     * This method retrieves the list of {@link Organization} from DAO layer.
     *
     * @return {@link Organization}
     * @throws ProcessFailed the process failed
     */
    public List<Organization> getAllOrganizations() throws ProcessFailed;

    /**
     * This method save {@link Organization} into the database.
     *
     * @param organization the Organization
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(Organization organization) throws ProcessFailed;

    /**
     * This method update {@link Organization} updates existing data from the
     * database.
     *
     * @param organization the Organization
     * @throws ProcessFailed the process failed
     */
    public void update(Organization organization) throws ProcessFailed;

    /**
     * This method delete particular {@link Organization} based on the
     * organization from the database.
     *
     * @param id the id
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer id) throws ProcessFailed;

    
}
