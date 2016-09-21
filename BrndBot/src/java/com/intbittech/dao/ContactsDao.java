/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Contacts;

/**
 *
 * @author ajit @ Intbit
 */
public interface ContactsDao {
   
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
     * This method pass id as input and get the {@link Contacts} from Database
     *
     * @param emailAddress the emailAddress
     * @return {@link Contacts}
     * @throws ProcessFailed the process failed
     */
    public Contacts getContactByEmailAddress(String emailAddress) throws ProcessFailed;
    
    /**
     * This method save {@link Contacts} into the database.
     *
     * @param contacts the contacts
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(Contacts contacts) throws ProcessFailed;

    /**
     * This method update {@link Contacts} updates existing data from the
     * database.
     *
     * @param contacts the contacts
     * @throws ProcessFailed the process failed
     */
    public void update(Contacts contacts) throws ProcessFailed;

    /**
     * This method delete particular {@link Contacts} based on the
     * contacts from the database.
     *
     * @param contacts the contacts
     * @throws ProcessFailed the process failed
     */
    public void delete(Contacts contacts) throws ProcessFailed;
}