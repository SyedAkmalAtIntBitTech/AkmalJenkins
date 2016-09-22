/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Contact;

/**
 *
 * @author ajit @ Intbit
 */
public interface ContactsDao {
   
     /**
     * This method pass id as input and get the {@link Contact} from DAO
     * layer.
     *
     * @param contactsId the contactsId
     * @return {@link Contact}
     * @throws ProcessFailed the process failed
     */
    public Contact getByContactsId(Integer contactsId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link Contact} from Database
     *
     * @param emailAddress the emailAddress
     * @return {@link Contact}
     * @throws ProcessFailed the process failed
     */
    public Contact getContactByEmailAddress(String emailAddress) throws ProcessFailed;
    
    /**
     * This method save {@link Contact} into the database.
     *
     * @param contacts the contacts
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(Contact contacts) throws ProcessFailed;

    /**
     * This method update {@link Contact} updates existing data from the
     * database.
     *
     * @param contacts the contacts
     * @throws ProcessFailed the process failed
     */
    public void update(Contact contacts) throws ProcessFailed;

    /**
     * This method delete particular {@link Contact} based on the
     * contacts from the database.
     *
     * @param contacts the contacts
     * @throws ProcessFailed the process failed
     */
    public void delete(Contact contacts) throws ProcessFailed;
}
