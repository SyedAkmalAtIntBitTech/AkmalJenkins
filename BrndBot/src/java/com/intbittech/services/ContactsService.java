/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Contacts;
import com.intbittech.modelmappers.ContactDetails;

/**
 * <code>{@link ContactsService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface ContactsService {
    
     /**
     * This method pass id as input and get the {@link Contacts} from DAO
     * layer.
     *
     * @param contactsId the contactsId
     * @return {@link Contacts}
     * @throws ProcessFailed the process failed
     */
    public Contacts getByContactsId(Integer contactsId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link Contacts} from DAO
     * layer.
     *
     * @param emailAddress the emailAddress
     * @return {@link Contacts}
     * @throws ProcessFailed the process failed
     */
    public Contacts getContactByEmailAddress(String emailAddress) throws ProcessFailed;
    
    /**
     * This method save {@link Contacts} into the database.
     *
     * @param contact the contact
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(Contacts contact) throws ProcessFailed;

    /**
     * This method update {@link Contacts} updates existing data from the
     * database.
     *
     * @param contact the contact
     * @throws ProcessFailed the process failed
     */
    public void update(Contacts contact) throws ProcessFailed;

    /**
     * This method delete particular {@link Contacts} based on the
     * contact from the database.
     *
     * @param contactId the contactId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer contactId )throws ProcessFailed;
    
    /**
     * This method save {@link Contacts} into the database.
     *
     * @param contactDetails the contactDetails
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer addContact(ContactDetails contactDetails);
    
}
