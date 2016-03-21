/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModel;
import java.util.List;

/**
 * <code>{@link EmailBlockModelService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface EmailBlockModelService {
    
    /**
     * This method pass id as input and get the {@link EmailBlockModel} from
     * database via Dao.
     *
     * @param emailBlockModelId the emailBlockModelId
     * @return {@link EmailBlockModel}
     * @throws ProcessFailed the process failed
     */
    public EmailBlockModel getByEmailBlockModelId(Integer emailBlockModelId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailBlockModel} from
     * database via Dao.
     *
     * @return List of {@link EmailBlockModel}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockModel> getAllEmailBlockModel() throws ProcessFailed;

    /**
     * This method save {@link EmailBlockModel} into the database via Dao.
     *
     * @param emailBlockModel the emailBlockModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(EmailBlockModel emailBlockModel) throws ProcessFailed;

    /**
     * This method update {@link EmailBlockModel} updates existing data from the
     * database via Dao.
     *
     * @param emailBlockModel the emailBlockModel
     * @throws ProcessFailed the process failed
     */
    public void update(EmailBlockModel emailBlockModel) throws ProcessFailed;

    /**
     * This method delete particular {@link EmailBlockModel} based on the EmailBlock
     * from the database via Dao.
     *
     * @param emailBlockModelId the emailBlockModelId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer emailBlockModelId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailBlockModel} from
     * database via Dao.
     *
     * @param emailBlockId
     * @return List of {@link EmailBlockModel}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockModel> getAllNonAddedEmailBlockModels(Integer emailBlockId) throws ProcessFailed;
    
}
