/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.PushedScheduledActionCompaniesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PushedScheduledActionCompanies;
import com.intbittech.services.PushedScheduledActionCompaniesService;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
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
public class PushedScheduledActionCompaniesServiceImpl implements PushedScheduledActionCompaniesService{
    
  private static Logger logger = Logger.getLogger(PushedScheduledActionCompaniesServiceImpl.class);
  @Autowired
  private PushedScheduledActionCompaniesDao pushedScheduledActionCompaniesDao;
  @Autowired
  private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public PushedScheduledActionCompanies getByPushedScheduledActionCompaniesId(Integer pushedScheduledActionCompaniesId) throws ProcessFailed {
        PushedScheduledActionCompanies pushedScheduledActionCompanies = pushedScheduledActionCompaniesDao.getByPushedScheduledActionCompaniesId(pushedScheduledActionCompaniesId);
        if (pushedScheduledActionCompanies == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found",new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompanies;
    }

   /**
     * {@inheritDoc}
     */
    public Integer save(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed {
        return pushedScheduledActionCompaniesDao.save(pushedScheduledActionCompanies);
    }

    /**
     * {@inheritDoc}
     */
    public void update(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed {
          pushedScheduledActionCompaniesDao.update(pushedScheduledActionCompanies);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer pushedScheduledActionCompaniesId) throws ProcessFailed {
        PushedScheduledActionCompanies pushedScheduledActionCompanies = pushedScheduledActionCompaniesDao.getByPushedScheduledActionCompaniesId(pushedScheduledActionCompaniesId);
        if (pushedScheduledActionCompanies == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found",new String[]{}, Locale.US));
        }
        pushedScheduledActionCompaniesDao.delete(pushedScheduledActionCompanies);
    }

     /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByScheduledEntityListId(Integer ScheduledEntityListId) throws ProcessFailed {
        List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesDao.getAllPushedScheduledActionCompaniesByScheduledEntityListId(ScheduledEntityListId);
        if (pushedScheduledActionCompaniesList == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found",new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompaniesList;
    }

     /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByFranchiseId(Integer franchiseId) throws ProcessFailed {
         List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesDao.getAllPushedScheduledActionCompaniesByFranchiseId(franchiseId);
        if (pushedScheduledActionCompaniesList == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found",new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompaniesList;
    }

     /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByCompanyId(Integer companyId) throws ProcessFailed {
        List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesDao.getAllPushedScheduledActionCompaniesByCompanyId(companyId);
        if (pushedScheduledActionCompaniesList == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found",new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompaniesList;
    }
    
}
