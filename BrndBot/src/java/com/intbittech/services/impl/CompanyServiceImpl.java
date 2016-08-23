/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.AppConstants;
import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.OrganizationCompanyDao;
import com.intbittech.dao.UserRoleLookUpDao;
import com.intbittech.enums.InvitationStatus;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationCompanyLookup;
import com.intbittech.model.UserCompanyLookup;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.modelmappers.CompanyDetails;
import com.intbittech.services.CompanyService;
import com.intbittech.services.UserCompanyLookupService;
import com.intbittech.services.UsersService;
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
    private MessageSource messageSource;
    
    @Autowired
    private UserRoleLookUpDao usersRoleLookUpDao;    

    @Autowired
    private UserCompanyLookupService userCompanyLookUpService;
    
    @Autowired
    private UsersService usersService;
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
        if(companyList == null)
        {
             throw new ProcessFailed(messageSource.getMessage("company_not_found",new String[]{}, Locale.US));
        }
              return companyList;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<OrganizationCompanyLookup> getAllOrganizationsByCompanyId(Integer companyId) throws ProcessFailed {
        List<OrganizationCompanyLookup> companyList = organizationCompanyDao.getAllOrganizationsByCompanyId(companyId);
        if(companyList == null)
        {
             throw new ProcessFailed(messageSource.getMessage("company_not_found",new String[]{}, Locale.US));
        }
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
    public String saveCompany(CompanyDetails companyDetails) throws ProcessFailed {
        String returnMessage = "false";UsersRoleLookup usersRoleLookUp = null;
        UserCompanyLookup userCompanyLookup = null;Company company = null,companyObject = null;
        try {
            //update company

            company = new Company();
            Users user = usersService.getUserById(companyDetails.getUserId());
            company.setCompanyName(companyDetails.getCompanyName());
            company.setCreatedDate(new Date());
            company.setInviteCode(RandomStringUtils.randomAlphanumeric(10));
            Integer companyId = companyDao.save(company);
            userCompanyLookup = new UserCompanyLookup();

            companyObject = new Company();
            companyObject.setCompanyId(companyId);

            userCompanyLookup.setCompanyid(company);
            userCompanyLookup.setUserid(user);
            userCompanyLookup.setAccountStatus(AppConstants.Account_Activated);
            
            userCompanyLookUpService.save(userCompanyLookup);

            if(user == null)
            {
                throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
            }
            
            usersRoleLookUp = usersRoleLookUpDao.getUsersRoleLookupByUser(user);

            UserRole userRole = new UserRole();
            userRole.setUserRoleId(AppConstants.UserRoleManagerValue);

            usersRoleLookUp.setUserId(user);
            usersRoleLookUp.setRoleId(userRole);
            usersRoleLookUpDao.update(usersRoleLookUp);
            
            //Relate company and organization
            OrganizationCompanyLookup organizationCompanyLookup = new OrganizationCompanyLookup();

            Organization organization = new Organization();
            organization.setOrganizationId(companyDetails.getOrganizationId());
            
            companyObject = new Company();
            companyObject.setCompanyId(companyId);

            organizationCompanyLookup.setFkOrganizationId(organization);
            organizationCompanyLookup.setFkCompanyId(companyObject);

            organizationCompanyDao.save(organizationCompanyLookup);
            
            returnMessage = companyId.toString();
        } catch(Throwable throwable) {
            returnMessage = "false";
            throw new ProcessFailed(messageSource.getMessage("company_save_error", new String[]{}, Locale.US));
        }
        return returnMessage;
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

    @Override
    public Company getCompanyById(Integer companyId) throws ProcessFailed {
        Company company = companyDao.getCompanyById(companyId);
        if(company == null)
        {
             throw new ProcessFailed(messageSource.getMessage("company_list_not_found",new String[]{}, Locale.US));
        }
        return company;
    }
   
}
