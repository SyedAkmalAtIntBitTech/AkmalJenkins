/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlock;
import com.intbittech.model.OrganizationEmailBlockLookup;
import java.util.List;

/**
 * <code> {@link OrganizationEmailBlockLookupDao} </code> Interface to get Email Block For Organization details from
 * organization_marketing_category_lookup table
 *
 * @author ilyas
 */
public interface OrganizationEmailBlockLookupDao {
    
    /**
     * This method pass id as input and get the {@link OrganizationEmailBlockLookup} from database.
     *
     * @param organizationEmailBlockId the organizationEmailBlockId
     * @return {@link OrganizationEmailBlockLookup}
     * @throws ProcessFailed the process failed
     */
    public OrganizationEmailBlockLookup getByOrganizationEmailBlockId(Integer organizationEmailBlockId) throws ProcessFailed;
    
    /**
     * This method save {@link EmailBlock} into the database.
     *
     * @param organizationEmailBlockLookup the organizationEmailBlockLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(OrganizationEmailBlockLookup organizationEmailBlockLookup) throws ProcessFailed;
    
    /**
     * This method pass id as input and get {@link OrganizationEmailBlockLookup}
     * from database.
     *
     * @param organizationId is the organizationId
     * @return list of {@link OrganizationEmailBlockLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationEmailBlockLookup> getAllOrganizationEmailBlock(Integer organizationId) throws ProcessFailed;

    
    
}
