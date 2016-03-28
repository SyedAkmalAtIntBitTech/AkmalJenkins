/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.RecuringEmailTemplate;
import com.intbittech.services.RecuringEmailTemplateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intbittech.dao.RecurringEmailTemplateDao;
import com.intbittech.dao.OrganizationRecurringEmailLookUpDao;
import com.intbittech.model.OrganizationRecuringEmailLookup;
import java.util.Locale;
import org.springframework.context.MessageSource;

/**
 *
 * @author Mohammed-Tameem
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class RecuringEmailTemplateServiceImp implements RecuringEmailTemplateService {

    @Autowired
    private RecurringEmailTemplateDao recurringEmailTemplateDao;

    @Autowired
    private OrganizationRecurringEmailLookUpDao organizationRecurringEmailLookupDao;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<OrganizationRecuringEmailLookup> getAllRecuringByOrganizationId(Integer recuringEmailTemplateId) throws ProcessFailed {

        List<OrganizationRecuringEmailLookup> organizationRecuringLookup = organizationRecurringEmailLookupDao.getAllRecuringByOrganizationId(recuringEmailTemplateId);
        if (organizationRecuringLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
        }
        return organizationRecuringLookup;
    }

    /**
     * {@inheritDoc}
     */
    public RecuringEmailTemplate getRecuringEmailTemplateById(Integer recuringEmailTemplateId) throws ProcessFailed {

        RecuringEmailTemplate recuringEmailTemplate = recurringEmailTemplateDao.getRecuringEmailTemplateById(recuringEmailTemplateId);
        if (recuringEmailTemplate == null) {
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
        }
        return recuringEmailTemplate;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(RecuringEmailTemplate recuringEmailTemplate) throws ProcessFailed {
        return recurringEmailTemplateDao.save(recuringEmailTemplate);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer recuringEmailTemplateId) throws ProcessFailed {
        RecuringEmailTemplate recuringEmailTemplate = recurringEmailTemplateDao.getRecuringEmailTemplateById(recuringEmailTemplateId);
        if (recuringEmailTemplateId == null) {
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
        recurringEmailTemplateDao.delete(recuringEmailTemplate);
    }

    /**
     * {@inheritDoc}
     */
    public void update(RecuringEmailTemplate recuringEmailTemplate) throws ProcessFailed {
        recurringEmailTemplateDao.update(recuringEmailTemplate);

    }

    /**
     * {@inheritDoc}
     */

    public Integer saveRecuringEmailOrganization(OrganizationRecuringEmailLookup organizationRecuringEmailLookup) throws ProcessFailed {
        return organizationRecurringEmailLookupDao.saveRecuringEmailOrganization(organizationRecuringEmailLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteRecuringEmailOrganization(Integer organizationRecuringEmailLookupId) throws ProcessFailed {
        OrganizationRecuringEmailLookup organizationRecuringEmailLookup = organizationRecurringEmailLookupDao.getOrganizationRecuringEmailById(organizationRecuringEmailLookupId);
        if (organizationRecuringEmailLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
        organizationRecurringEmailLookupDao.deleteRecuringEmailOrganization(organizationRecuringEmailLookup);

    }

    /**
     * {@inheritDoc}
     */
    public List<RecuringEmailTemplate> getAllNonRecuringEmail(Integer nonRecuringEmailId) throws ProcessFailed {
        List<OrganizationRecuringEmailLookup> organizationRecuringEmailList = organizationRecurringEmailLookupDao.getAllRecuringByOrganizationId(nonRecuringEmailId);
         if (organizationRecuringEmailList == null) {
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
         }
         
        Integer[] recuringEmailIds = new Integer[organizationRecuringEmailList.size()];
        Integer i = 0;
        for (OrganizationRecuringEmailLookup organizationRecuringEmailObject : organizationRecuringEmailList) {
            recuringEmailIds[i++] = organizationRecuringEmailObject.getFkRecuringEmailTemplateId().getRecuringEmailTemplateId();
        }
        List<RecuringEmailTemplate> recuringEmailTemplateList = recurringEmailTemplateDao.getAllNonRecurringEmail(recuringEmailIds);
        return recuringEmailTemplateList;
    }

}
