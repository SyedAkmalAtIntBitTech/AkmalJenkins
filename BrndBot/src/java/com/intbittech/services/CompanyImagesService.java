/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyImages;
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
public interface CompanyImagesService {
    
    /**
     * This method retrieves the list of {@link Company} from DAO layer.
     *
     * @return {@link CompanyImages}
     * @throws ProcessFailed the process failed
     */
    public List<CompanyImages> getAllImagesForCompany(Integer companyId) throws ProcessFailed;
    
     
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @param companyImage
     * @return {@link organizationCompanyLookupId}
     * @throws ProcessFailed the process failed
     */
    public Integer save(CompanyImages companyImage) throws ProcessFailed;
    
    /**
     * This method retrieves the {@link OrganizationCompanyLookup} from DAO layer.
     *
     * @param companyImagesId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer companyImagesId) throws ProcessFailed;
  
}
