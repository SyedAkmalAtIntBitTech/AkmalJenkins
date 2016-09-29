/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailList;
import java.util.List;

/**
 *
 * @author ajit @ Intbit
 */
public interface EmailListDao {
    
     /**
     * This method pass id as input and get the {@link EmailList} from DAO
     * layer.
     *
     * @param emailListId the emailListId
     * @return {@link EmailList}
     * @throws ProcessFailed the process failed
     */
    public EmailList getByEmailListId(Integer emailListId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailList} from DAO
     * layer.
     *
     * @param companyId the companyId
     * @return {@link EmailList}
     * @throws ProcessFailed the process failed
     */
    public List<EmailList> getEmailListByCompanyId(Integer companyId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailList} from DAO
     * layer.
     *
     * @param companyId the companyId
     * @return {@link EmailList}
     * @throws ProcessFailed the process failed
     */
    public Boolean checkUniqueness(Integer companyId, String emailListName) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailList} from Database
     *
     * @param companyId the companyId
     * @return {@link EmailList}
     * @throws ProcessFailed the process failed
     */
    public EmailList getEmailListByCompanyIdAndEmailListName(Integer companyId, String emailListName) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailList} from DAO
     * layer.
     *
     * @param companyId the companyId
     * @return {@link EmailList}
     * @throws ProcessFailed the process failed
     */
    public List<EmailList> getEmailListByCompanyIdAndType(Integer companyId, Integer typeId) throws ProcessFailed;
    
    /**
     * This method save {@link EmailList} into the database.
     *
     * @param emailList the emailList
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailList emailList) throws ProcessFailed;

    /**
     * This method update {@link EmailList} updates existing data from the
     * database.
     *
     * @param emailList the emailList
     * @throws ProcessFailed the process failed
     */
    public void update(EmailList emailList) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailList} based on the
     * contacts from the database.
     *
     * @param emailList the emailList
     * @throws ProcessFailed the process failed
     */
    public void delete(EmailList emailList) throws ProcessFailed;
    
}
