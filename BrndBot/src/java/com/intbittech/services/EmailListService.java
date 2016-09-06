/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.EmailList;
import java.util.List;
import java.util.Map;
/**
 * <code>{@link EmailListService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface EmailListService {

    public String getEmailList(String queryParameter, Integer companyId, String emailListName) throws Exception;

    public Boolean setEmailList(Map<String, Object> requestBodyMap, Integer companyId) throws Exception;

    public void updateUnsubscribedUserEmailLists(CompanyPreferences companyPreferences);
    
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
     * @param emailListId the emailListId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer emailListId) throws ProcessFailed;
}
