/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationRecurringEmailLookup;
import com.intbittech.model.RecurringEmailTemplate;
import java.util.List;

/**
 * <code>{@link RecurringEmailTemplateService}</code> is service layer interface
 * for communicating between Controller and DAO classes
 *
 * @author Mohammed-Tameem
 */
public interface RecurringEmailTemplateService {

    /**
     * This method pass id as input and get the {@link OrganizationEmaiLookup}
     * from DAO layer.
     *
     * @param organizationId
     * @return list of {@link OrganizationRecurringEmailLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationRecurringEmailLookup> getAllRecurringByOrganizationId(Integer organizationId) throws ProcessFailed;

    /**
     * This method save {@link RecurringEmailTemlate} into the database via DAO
     * layer.
     *
     * @param recurringEmailTemplate
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(RecurringEmailTemplate recurringEmailTemplate) throws ProcessFailed;

    /**
     * This method {@link RecurringEmailTemplate} deletes existing data from the
     * database via DAO layer.
     *
     * @param recurringEmailTemplateId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer recurringEmailTemplateId) throws ProcessFailed;

    /**
     * This method {@link RecurringEmailTemplate} updates existing data from the
     * database via DAO layer.
     *
     * @param recurringEmailTemplate
     * @throws ProcessFailed the process failed
     */
    public void update(RecurringEmailTemplate recurringEmailTemplate) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link RecurringEmailTemplate}
     * from DAO layer.
     *
     * @param recurringEmailTemplateId
     * @return {@link RecurringEmailTemplate}
     * @throws ProcessFailed the process failed
     */
    public RecurringEmailTemplate getRecurringEmailTemplateById(Integer recurringEmailTemplateId) throws ProcessFailed;

    /**
     * This method save {@link OrganizationRecurringEmailLookup} into the
     * database via DAO layer.
     *
     * @param organizationRecurringEmailLookup is the
     * OrganizationRecurringEmailLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    /**
     * This method save {@link OrganizationRecurringEmailLookup} into the
     * database via DAO layer.
     *
     * @param organizationRecurringEmailLookup is the
     * OrganizationRecurringEmailLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer saveRecurringEmailOrganization(OrganizationRecurringEmailLookup organizationRecurringEmailLookup) throws ProcessFailed;

    /**
     * This method deletes {@link OrganizationRecurringEmailLookup} into the
     * database via DAO layer.
     *
     * @param organizationRecurringEmailLookup is the
     * OrganizationRecurringEmailLookup
     *
     * @throws ProcessFailed the process failed
     */
    public void deleteRecurringEmailOrganization(Integer organizationRecurringEmailLookup) throws ProcessFailed;

    /**
     * This method returns {@link RecurringEmailTemplate} into the database via
     * DAO layer.
     *
     * @param nonRecurringEmailIds
     * @return list of recurringEmailTemplate
     *
     * @throws ProcessFailed the process failed
     */
    public List<RecurringEmailTemplate> getAllNonRecurringEmail(Integer nonRecurringEmailIds) throws ProcessFailed;

    /**
     * This method retrieves the list of {@link RecurringEmailTemplate} from
     * Database via DAO layer.
     *
     * @return list of {@link RecurringEmailTemplate}
     * @throws ProcessFailed the process failed
     */
    public List<RecurringEmailTemplate> getAllRecurringEmails() throws ProcessFailed;

}