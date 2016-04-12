/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.OrganizationCompanyDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.MarketingCategoryProgram;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationCompanyLookup;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.CompanyDetails;
import com.intbittech.services.CompanyService;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;
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
    private UsersDao usersDao;
    
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
    public Integer saveCompany(CompanyDetails companyDetails) throws ProcessFailed {
        try {
            //Save company
            Company company = new Company();
            company.setCompanyName(companyDetails.getCompanyName());
            company.setCreatedDate(new Date());
            company.setInviteCode(RandomStringUtils.randomAlphanumeric(10));
            Integer companyId = companyDao.save(company);

            //Update user table with companyId
            Company companyObject = new Company();
            companyObject.setCompanyId(companyId);

            
            Users user = usersDao.getUserById(companyDetails.getUserId());
            if(user == null)
            {
                 throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
            }
            user.setFkCompanyId(companyObject);
            usersDao.update(user);

            //Relate company and organization
            OrganizationCompanyLookup organizationCompanyLookup = new OrganizationCompanyLookup();

            Organization organization = new Organization();
            organization.setOrganizationId(companyDetails.getOrganizationId());

            organizationCompanyLookup.setFkOrganizationId(organization);
            organizationCompanyLookup.setFkCompanyId(companyObject);

            organizationCompanyDao.save(organizationCompanyLookup);
            
            return companyId;
        } catch(Throwable throwable) {
            throw new ProcessFailed(messageSource.getMessage("company_save_error", new String[]{}, Locale.US));
        }        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer saveCompanyPreferences(CompanyPreferences companyPreferences) throws ProcessFailed {
        try {
            return companyDao.saveCompanyPreferences(companyPreferences);
        } catch (Throwable throwable) {
            throw new ProcessFailed(messageSource.getMessage("company_preferences_save_error", new String[]{}, Locale.US));
        }
    }
    
}
