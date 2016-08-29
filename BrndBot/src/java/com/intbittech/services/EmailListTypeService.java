/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListType;

/**
 * <code>{@link EmailListTypeService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface EmailListTypeService {
    
     /**
     * This method pass id as input and get the {@link EmailListType} from DAO
     * layer.
     *
     * @param emailListTypeId the emailListTypeId
     * @return {@link EmailListType}
     * @throws ProcessFailed the process failed
     */
    public EmailListType getByEmailListTypeId(Integer emailListTypeId) throws ProcessFailed;
    
    /**
     * This method save {@link EmailListType} into the database.
     *
     * @param emailListType the emailListType
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailListType emailListType) throws ProcessFailed;

    /**
     * This method update {@link EmailListType} updates existing data from the
     * database.
     *
     * @param emailListType the emailListType
     * @throws ProcessFailed the process failed
     */
    public void update(EmailListType emailListType) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailListType} based on the
     * EmailListType from the database.
     *
     * @param emailListTypeId the emailListTypeId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer emailListTypeId) throws ProcessFailed;
    
}
