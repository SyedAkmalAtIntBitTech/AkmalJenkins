/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ContactEmailListLookup;
import java.text.ParseException;
import java.util.List;

/**
 * <code>{@link ContactEmailListLookupService}</code> is service layer interface for communicating
 * between Controller and DAO classes
 *
 * @author Ajit
 */
public interface ContactEmailListLookupService {
    
    /**
     * This method pass id as input and get the {@link ContactEmailListLookup} from DAO
     * layer.
     *
     * @param contactEmailListLookupId the contactEmailListLookupId
     * @return {@link ContactEmailListLookup}
     * @throws ProcessFailed the process failed
     */
    public ContactEmailListLookup getByContactEmailListLookupId(Integer contactEmailListLookupId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link ContactEmailListLookup} from DAO
     * layer.
     *
     * @param emailListId the emailListId
     * @return {@link ContactEmailListLookup}
     * @throws ProcessFailed the process failed
     */
    public List<ContactEmailListLookup> getContactsByEmailListId(Integer emailListId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link ContactEmailListLookup} from DAO
     * layer.
     *
     * @param emailListId the emailListId
     * @param contactId the contactId
     * @return {@link ContactEmailListLookup}
     * @throws ProcessFailed the process failed
     */
    public ContactEmailListLookup getByEmailListIdAndContactId(Integer emailListId, Integer contactId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link ContactEmailListLookup} from DAO
     * layer.
     *
     * @param emailListName the emailListName
     * @param contactId the contactId
     * @return string of emailListName
     * @throws ProcessFailed the process failed
     */
    public List<ContactEmailListLookup> getContactsByEmailListNameAndCompanyId(String emailListName,Integer companyId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link ContactEmailListLookup} from DAO
     * layer.
     *
     * @param emailListName the emailListName
     * @param companyId the companyId
     * @param days the days
     * @return string of emailListName
     * @throws ProcessFailed the process failed
     */
    public List<ContactEmailListLookup> getContactsByEmailListNameAndCompanyIdForToday(String emailListName, Integer companyId, Integer days) throws ProcessFailed, ParseException;
    
    /**
     * This method pass id as input and get the {@link ContactEmailListLookup} from DAO
     * layer.
     *
     * @param companyId the companyId
     * @throws ProcessFailed the process failed
     */
    public void updateUnsubscribedUserEmailLists(Integer companyId) throws ProcessFailed;
    
    /**
     * This method save {@link ContactEmailListLookup} into the database.
     *
     * @param contactEmailListLookup the contactEmailListLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public void saveOrUpdate(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed;

    /**
     * This method update {@link ContactEmailListLookup} updates existing data from the
     * database.
     *
     * @param contactEmailListLookup the contactEmailListLookup
     * @throws ProcessFailed the process failed
     */
    public void update(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed;

    /**
     * This method delete particular {@link ContactEmailListLookup} based on the
     * ContactEmailListLookup from the database.
     *
     * @param contactEmailListLookupId the contactEmailListLookupId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer contactEmailListLookupId) throws ProcessFailed;
    
    
}
