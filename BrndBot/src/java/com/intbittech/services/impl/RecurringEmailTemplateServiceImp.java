/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.RecurringEmailTemplate;
import com.intbittech.services.RecurringEmailTemplateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intbittech.dao.RecurringEmailTemplateDao;
import com.intbittech.dao.OrganizationRecurringEmailLookUpDao;
import com.intbittech.model.OrganizationRecurringEmailLookup;
import java.util.Locale;
import org.springframework.context.MessageSource;

/**
 *
 * @author Mohammed-Tameem
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class RecurringEmailTemplateServiceImp implements RecurringEmailTemplateService {

    @Autowired
    private RecurringEmailTemplateDao recurringEmailTemplateDao;

    @Autowired
    private OrganizationRecurringEmailLookUpDao organizationRecurringEmailLookupDao;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<OrganizationRecurringEmailLookup> getAllRecurringByOrganizationId(Integer organizationId) throws ProcessFailed {

        List<OrganizationRecurringEmailLookup> organizationRecurringLookup = organizationRecurringEmailLookupDao.getAllRecurringByOrganizationId(organizationId);
        if (organizationRecurringLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
        }
        return organizationRecurringLookup;
    }

    /**
     * {@inheritDoc}
     */
    public RecurringEmailTemplate getRecurringEmailTemplateById(Integer recurringEmailTemplateId) throws ProcessFailed {

        RecurringEmailTemplate recurringEmailTemplate = recurringEmailTemplateDao.getRecurringEmailTemplateById(recurringEmailTemplateId);
        if (recurringEmailTemplate == null) {
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
        }
        return recurringEmailTemplate;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(RecurringEmailTemplate recurringEmailTemplate) throws ProcessFailed {
        return recurringEmailTemplateDao.save(recurringEmailTemplate);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer recurringEmailTemplateId) throws ProcessFailed {
        RecurringEmailTemplate recurringEmailTemplate = recurringEmailTemplateDao.getRecurringEmailTemplateById(recurringEmailTemplateId);
        if (recurringEmailTemplateId == null) {
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
        recurringEmailTemplateDao.delete(recurringEmailTemplate);
    }

    /**
     * {@inheritDoc}
     */
    public void update(RecurringEmailTemplate recurringEmailTemplate) throws ProcessFailed {
        recurringEmailTemplateDao.update(recurringEmailTemplate);

    }

    /**
     * {@inheritDoc}
     */

    public Integer saveRecurringEmailOrganization(OrganizationRecurringEmailLookup organizationRecurringEmailLookup) throws ProcessFailed {
        return organizationRecurringEmailLookupDao.saveRecurringEmailOrganization(organizationRecurringEmailLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteRecurringEmailOrganization(Integer organizationRecurringEmailLookupId) throws ProcessFailed {
        OrganizationRecurringEmailLookup organizationRecurringEmailLookup = organizationRecurringEmailLookupDao.getOrganizationRecurringEmailById(organizationRecurringEmailLookupId);
        if (organizationRecurringEmailLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
        organizationRecurringEmailLookupDao.deleteRecurringEmailOrganization(organizationRecurringEmailLookup);

    }

    /**
     * {@inheritDoc}
     */
    public List<RecurringEmailTemplate> getAllNonRecurringEmail(Integer organizationId) throws ProcessFailed {
        List<OrganizationRecurringEmailLookup> organizationRecurringEmailList = organizationRecurringEmailLookupDao.getAllRecurringByOrganizationId(organizationId);
//         if (organizationRecurringEmailList == null) {
//            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
//         }
        Integer organizationRecurringSize = 1;
        if (organizationRecurringEmailList != null)
            organizationRecurringSize = organizationRecurringEmailList.size() +1;
        Integer[] recurringEmailIds = new Integer[organizationRecurringSize];
        Integer i = 0;
        recurringEmailIds[i++] = 0;
        if (organizationRecurringEmailList != null) {
        for (OrganizationRecurringEmailLookup organizationRecurringEmailObject : organizationRecurringEmailList) {
            recurringEmailIds[i++] = organizationRecurringEmailObject.getFkRecurringEmailTemplateId().getRecurringEmailTemplateId();
        }
        }
        List<RecurringEmailTemplate> recurringEmailTemplateList = recurringEmailTemplateDao.getAllNonRecurringEmail(recurringEmailIds);
        return recurringEmailTemplateList;
    }

}
