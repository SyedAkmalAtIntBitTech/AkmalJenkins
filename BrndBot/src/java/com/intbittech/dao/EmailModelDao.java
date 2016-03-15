/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailModel;
import com.intbittech.model.ImageModel;
import java.util.List;

/**
 * <code> {@link EmailModelDao} </code> Interface to get Email Model details from
 * EmailModel table
 *
 * @author Ajit
 */
public interface EmailModelDao  {
    
    /**
     * This method pass id as input and get the {@link EmailModel} from database.
     *
     *  * @return {@link EmailModel}
     * @return 
     * @throws ProcessFailed the process failed
     */
    public List<EmailModel> getAllEmailModel() throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link EmailModel} from DAO
     * layer.
     *
     * @param emailModelId the emailModelId
     * @return {@link EmailModel}
     * @throws ProcessFailed the process failed
     */
    public EmailModel getByEmailModelId(Integer emailModelId) throws ProcessFailed;
    
    /**
     * This method save {@link EmailModel} into the database.
     *
     * @param emailModel the EmailModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailModel emailModel) throws ProcessFailed;

    /**
     * This method update {@link EmailModel} updates existing data from the
     * database.
     *
     * @param emailModel the emailModel
     * @throws ProcessFailed the process failed
     */
    public void update(EmailModel emailModel) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailModel} based on the
     * EmailModel from the database.
     *
     * @param emailModel the emailModel
     * @throws ProcessFailed the process failed
     */
    public void delete(EmailModel emailModel) throws ProcessFailed;
    
    
}
