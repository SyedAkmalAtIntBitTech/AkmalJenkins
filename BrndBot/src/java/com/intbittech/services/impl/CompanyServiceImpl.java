/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.OrganizationCompanyDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.MarketingCategoryProgram;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationCompanyLookup;
import com.intbittech.services.CompanyService;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class CompanyServiceImpl implements CompanyService{
    
    @Autowired
    private CompanyDao companyDao;
    
    @Autowired
    private OrganizationCompanyDao organizationCompanyDao;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<Company> getAllCompanies() throws ProcessFailed {
        List<Company> companyList = companyDao.getAllCompanies();
        if(companyList == null)
        {
             throw new ProcessFailed(messageSource.getMessage("company_list_not_found",new String[]{}, Locale.US));
        }
              return companyList;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<OrganizationCompanyLookup> getAllOrganizationCompanies() throws ProcessFailed {
        List<OrganizationCompanyLookup> companyList = organizationCompanyDao.getAllOrganizationCompanies();
        if(companyList == null)
        {
             throw new ProcessFailed(messageSource.getMessage("company_list_not_found",new String[]{}, Locale.US));
        }
              return companyList;
    }

    /**
     * {@inheritDoc}
     */
    public OrganizationCompanyLookup getOrganizationCompanyById(Integer companyId) throws ProcessFailed {
        OrganizationCompanyLookup company = organizationCompanyDao.getOrganizationCompanyById(companyId);
        if(company == null)
        {
             throw new ProcessFailed(messageSource.getMessage("company_not_found",new String[]{}, Locale.US));
        }
              return company;
    }

    /**
     * {@inheritDoc}
     */
    public List<OrganizationCompanyLookup> getAllOrganizationCompanyById(Integer companyId) throws ProcessFailed {
        List<OrganizationCompanyLookup> companyList = organizationCompanyDao.getAllOrganizationCompanyById(companyId);
//        if(companyList == null)
//        {
//             throw new ProcessFailed(messageSource.getMessage("company_not_found",new String[]{}, Locale.US));
//        }
              return companyList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(OrganizationCompanyLookup organizationCompanyLookup) throws ProcessFailed {
        try {
            return organizationCompanyDao.save(organizationCompanyLookup);
        } catch (Throwable throwable) {
            throw new ProcessFailed(messageSource.getMessage("group_save_error", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer organizationCompanyLookupId) throws ProcessFailed {
        OrganizationCompanyLookup organizationCompanyLookup = organizationCompanyDao.getById(organizationCompanyLookupId);
        if(organizationCompanyLookup == null)
            throw new ProcessFailed(messageSource.getMessage("group_delete_error",new String[]{}, Locale.US));
        organizationCompanyDao.delete(organizationCompanyLookup);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getNonAddedGroups(Integer[] organizationIds) throws ProcessFailed {
        List<Organization> organizationList = organizationCompanyDao.getNonAddedGroups(organizationIds);
        if(organizationList == null)
        {
             throw new ProcessFailed(messageSource.getMessage("group_list_not_found",new String[]{}, Locale.US));
        }
              return organizationList;
        
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer saveCompany(Company company) throws ProcessFailed {
        return companyDao.save(company);
        
    }
    
}
