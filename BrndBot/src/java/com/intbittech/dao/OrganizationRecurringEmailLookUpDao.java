/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationRecuringEmailLookup;
import java.util.List;

/**
 *
 * @author Mohammed-Tameem
 */
public interface OrganizationRecurringEmailLookUpDao 

      
{
     /**
     * This method saves {@link OrganizationRecuringEmailLookup} into the database.
     *
     * @param organizationRecuringEmailLookup the OrganizationRecuringEmailLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    
     public Integer saveRecuringEmailOrg(OrganizationRecuringEmailLookup organizationRecuringEmailLookup) throws ProcessFailed;
     
     
     /**
     * This method delete particular {@link OrganizationRecurringEmailLookUp} based on the
     * OrganizationRecurringEmailLookUp from the database.
     *
     * @param organizationRecuringEmailLookup  the OrganizationRecuringEmailLookup
     * @throws ProcessFailed the process failed
     */
    
     public void deleteRecuringEmailOrg(OrganizationRecuringEmailLookup organizationRecuringEmailLookup)throws ProcessFailed;
     
     
     /**
     * This method retrieves the list of {@link OrganizationRecuringEmailLookup} from Database.
     *
     * @param  organizationRecuringEmailLookup the OrganizationRecuringEmailLookup
     * @return {@link OrganizationRecuringEmailLookup}
     * @throws ProcessFailed the process failed
     */
     
    
     public List<OrganizationRecuringEmailLookup>  getAllRecuringByOrganizationId(Integer organizationId)throws ProcessFailed;
     
     
     /**
     * This method retrieves particular {@link OrganizationRecuringEmailLookup} from the database.
     *
     * @param organizationRecuringEmailLookup the OrganizationRecuringEmailLookup
     * @return {@link OrganizationRecuringEmailLookup}
     * @throws ProcessFailed the process failed
     */
     
     public OrganizationRecuringEmailLookup getRecuringEmailById(Integer organizationRecuringEmailLookupId) throws ProcessFailed;
    
}
