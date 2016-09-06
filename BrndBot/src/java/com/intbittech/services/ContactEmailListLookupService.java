/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ContactEmailListLookup;
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
     * This method save {@link ContactEmailListLookup} into the database.
     *
     * @param contactEmailListLookup the contactEmailListLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed;

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
