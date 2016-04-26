/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSource;
import java.util.List;

/**
 * <code> {@link ExternalSourceDao} </code> Interface to get ExternalSource details from
 * External Source table
 *
 * @author Ajit
 */
public interface ExternalSourceDao {
    
    /**
     * This method pass id as input and get the {@link ExternalSource} from DAO
     * layer.
     *
     * @param externalSourceId the externalSourceId
     * @return {@link ExternalSource}
     * @throws ProcessFailed the process failed
     */
    public ExternalSource getByExternalSourceId(Integer externalSourceId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link ExternalSource} from DAO
     * layer.
     *
     * @return {@link ExternalSource}
     * @throws ProcessFailed the process failed
     */
    public List<ExternalSource> getAllExternalSources() throws ProcessFailed;
    
    /**
     * This method save {@link ExternalSource} into the database.
     *
     * @param externalSource the ExternalSource
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(ExternalSource externalSource) throws ProcessFailed;

    /**
     * This method update {@link ExternalSource} updates existing data from the
     * database.
     *
     * @param externalSource the externalSource
     * @throws ProcessFailed the process failed
     */
    public void update(ExternalSource externalSource) throws ProcessFailed;

    /**
     * This method delete particular {@link ExternalSource} based on the
     * externalSource from the database.
     *
     * @param externalSource the externalSource
     * @throws ProcessFailed the process failed
     */
    public void delete(ExternalSource externalSource) throws ProcessFailed;
    
}
