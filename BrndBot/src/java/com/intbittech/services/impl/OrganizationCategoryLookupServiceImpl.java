/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.OrganizationCategoryLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationCategoryLookup;
import com.intbittech.services.OrganizationCategoryLookupService;
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
public class OrganizationCategoryLookupServiceImpl implements OrganizationCategoryLookupService {

    @Autowired
    private OrganizationCategoryLookupDao organizationCategoryLookupDao;

    /**
     * {@inheritDoc}
     */
    public List<OrganizationCategoryLookup> getAllOrganizationCategoryLookup(Integer organizationId, Integer channelId) throws ProcessFailed {
        List<OrganizationCategoryLookup> OrganizationCategoryList = organizationCategoryLookupDao.getAllOrganizationCategoryLookup(organizationId, channelId);
//        if (OrganizationCategoryList == null) {
//            throw new ProcessFailed("No category found .");
//        }
        return OrganizationCategoryList;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<OrganizationCategoryLookup> getAllOrganizationCategoryLookupByIds(Integer[] organizationIds, Integer channelId) throws ProcessFailed {
        List<OrganizationCategoryLookup> OrganizationCategoryList = organizationCategoryLookupDao.getAllOrganizationCategoryLookupByIds(organizationIds, channelId);
        return OrganizationCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(OrganizationCategoryLookup organizationCategoryLookup) throws ProcessFailed {
        return organizationCategoryLookupDao.save(organizationCategoryLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void update(OrganizationCategoryLookup organizationCategoryLookup) throws ProcessFailed {
        organizationCategoryLookupDao.update(organizationCategoryLookup);
    }

}
