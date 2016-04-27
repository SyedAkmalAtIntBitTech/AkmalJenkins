/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModel;
import java.util.ArrayList;
import java.util.List;

/**
 * <code> {@link EmailBlockModelDao} </code> Interface to get EmailBlockModelDao
 * details from email_block_model table
 *
 * @author ilyas
 */
public interface EmailBlockModelDao {

    /**
     * This method pass id as input and get the {@link EmailBlockModel} from
     * database.
     *
     * @param emailBlockModelId the emailBlockModelId
     * @return {@link EmailBlockModel}
     * @throws ProcessFailed the process failed
     */
    public EmailBlockModel getByEmailBlockModelId(Integer emailBlockModelId) throws ProcessFailed;

    /**
     * This method save {@link EmailBlockModel} into the database.
     *
     * @param emailBlockModel the emailBlockModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailBlockModel emailBlockModel) throws ProcessFailed;

    /**
     * This method update {@link EmailBlockModel} updates existing data from the
     * database.
     *
     * @param emailBlockModel the emailBlockModel
     * @throws ProcessFailed the process failed
     */
    public void update(EmailBlockModel emailBlockModel) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailBlockModel} based on the
     * EmailModel from the database.
     *
     * @param emailBlockModel the emailBlockModel
     * @throws ProcessFailed the process failed
     */
    public void delete(EmailBlockModel emailBlockModel) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailBlockModel} from
     * database.
     *
     * @return List of {@link EmailBlockModel}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockModel> getAllEmailBlockModel() throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailBlockModel} from
     * database.
     *
     * @param emailBlockModelIds the emailBlockModelIds
     * @return List of {@link EmailBlockModel}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockModel> getByEmailBlockModelsByIds(ArrayList<Integer> emailBlockModelIds) throws ProcessFailed;

}