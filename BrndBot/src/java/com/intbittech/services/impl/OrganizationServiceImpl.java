/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.OrganizationDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Organization;
import com.intbittech.services.OrganizationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    /**
     * {@inheritDoc}
     */
    public Organization getById(Integer id) throws ProcessFailed {
        Organization organization = organizationDao.getById(id);
        if(organization == null){
            throw new ProcessFailed("No organization found with id " + id + ".");
        }
        return organization;
    }

    /**
     * {@inheritDoc}
     */
    public List<Organization> getAllOrganizations() throws ProcessFailed {
        List<Organization> organizationList= organizationDao.getAllOrganizations();
        if(organizationList == null){
            throw new ProcessFailed("No organization found .");
        }
        return organizationList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(Organization organization) throws ProcessFailed {
        return  organizationDao.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    public void update(Organization organization) throws ProcessFailed {
        organizationDao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer id) throws ProcessFailed {
        Organization organization = organizationDao.getById(id);
        if(organization == null){
            throw new ProcessFailed("No organization found with id " + id + ".");
        }
         organizationDao.delete(organization); 
    }

}
