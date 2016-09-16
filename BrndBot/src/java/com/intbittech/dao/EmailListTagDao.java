/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTag;
import java.util.List;

/**
 *
 * @author ajit @ Intbit
 */
public interface EmailListTagDao {
    
     /**
     * This method pass id as input and get the {@link EmailListTag} from DAO
     * layer.
     *
     * @param emailListTagId the emailListTagId
     * @return {@link EmailListTag}
     * @throws ProcessFailed the process failed
     */
    public EmailListTag getByEmailListTagId(Integer emailListTagId) throws ProcessFailed;
    
    /**
     * This method save {@link EmailListTag} into the database.
     *
     * @param emailListTag the emailListTag
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailListTag emailListTag) throws ProcessFailed;

    /**
     * This method update {@link EmailListTag} updates existing data from the
     * database.
     *
     * @param emailListTag the emailListTag
     * @throws ProcessFailed the process failed
     */
    public void update(EmailListTag emailListTag) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailListTag} based on the
     * EmailListTag from the database.
     *
     * @param emailListTag the emailListTag
     * @throws ProcessFailed the process failed
     */
    public void delete(EmailListTag emailListTag) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailListTag} from DAO
     * layer.
     *
s     * @return {@link EmailListTag}
     * @throws ProcessFailed the process failed
     */
    public List<EmailListTag> getAllEmailListTag() throws ProcessFailed;
}
