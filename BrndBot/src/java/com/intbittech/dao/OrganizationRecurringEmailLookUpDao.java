/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationRecurringEmailLookup;
import java.util.List;

/**
 *
 * @author Mohammed-Tameem
 */
public interface OrganizationRecurringEmailLookUpDao 

      
{
     /**
     * This method saves {@link OrganizationRecurringEmailLookup} into the database.
     *
     * @param organizationRecurringEmailLookup the OrganizationRecurringEmailLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    
     public Integer saveRecurringEmailOrganization(OrganizationRecurringEmailLookup organizationRecurringEmailLookup) throws ProcessFailed;
     
     
     /**
     * This method delete particular {@link OrganizationRecurringEmailLookUp} based on the
     * OrganizationRecurringEmailLookUp from the database.
     *
     * @param organizationRecurringEmailLookup  the OrganizationRecurringEmailLookup
     * @throws ProcessFailed the process failed
     */
    
     public void deleteRecurringEmailOrganization(OrganizationRecurringEmailLookup organizationRecurringEmailLookup)throws ProcessFailed;
     
     
     /**
     * This method retrieves the list of {@link OrganizationRecurringEmailLookup} from Database.
     *
     * @param  organizationId the organizationId
     * @return {@link OrganizationRecurringEmailLookup}
     * @throws ProcessFailed the process failed
     */
     
    
     public List<OrganizationRecurringEmailLookup>  getAllRecurringByOrganizationId(Integer organizationId)throws ProcessFailed;
     
     
     /**
     * This method retrieves particular {@link OrganizationRecurringEmailLookup} from the database.
     *
     * @param organizationRecurringEmailLookupId the organizationRecurringEmailLookupId
     * @return {@link OrganizationRecurringEmailLookup}
     * @throws ProcessFailed the process failed
     */
     
     public OrganizationRecurringEmailLookup getOrganizationRecurringEmailById(Integer organizationRecurringEmailLookupId) throws ProcessFailed;
    
}
