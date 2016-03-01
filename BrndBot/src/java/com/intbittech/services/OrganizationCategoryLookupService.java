/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationCategoryLookup;
import java.util.List;

/**
 * <code>{@link OrganizationCategoryLookupService}</code> is service layer
 * interface for communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface OrganizationCategoryLookupService {

    /**
     * This method pass id as input and get the
     * {@link OrganizationCategoryLookup} from DAO layer.
     *
     * @param organizationId is the organizationId
     * @param channelId is the channelId
     * @return {@link OrganizationCategoryLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationCategoryLookup> getAllOrganizationCategoryLookup(Integer organizationId, Integer channelId) throws ProcessFailed;

    /**
     * This method save {@link OrganizationCategoryLookup} into the database.
     *
     * @param organizationCategoryLookup the organizationCategoryLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(OrganizationCategoryLookup organizationCategoryLookup) throws ProcessFailed;

    /**
     * This method update {@link organizationCategoryLookup} updates existing
     * data from the database.
     *
     * @param organizationCategoryLookup the organizationCategoryLookup
     * @throws ProcessFailed the process failed
     */
    public void update(OrganizationCategoryLookup organizationCategoryLookup) throws ProcessFailed;

}
