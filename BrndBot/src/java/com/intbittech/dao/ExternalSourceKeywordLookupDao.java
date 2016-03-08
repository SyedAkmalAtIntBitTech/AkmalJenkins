/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSourceKeywordLookup;
import java.util.List;

/**
 * <code> {@link ExternalSourceKeywordLookupLookupDao} </code> Interface to get ExternalSourceKeywordLookupLookup details from
 * External Source Keyword lookup  table
 *
 * @author Ajit
 */
public interface ExternalSourceKeywordLookupDao {
    
     /**
     * This method pass id as input and get the {@link ExternalSourceKeywordLookup} from DAO
     * layer.
     *
     * @param externalSourceKeywordLookupId the externalSourceKeywordLookupId
     * @return {@link ExternalSourceKeywordLookup}
     * @throws ProcessFailed the process failed
     */
    public ExternalSourceKeywordLookup getByExternalSourceKeywordLookupId(Integer externalSourceKeywordLookupId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link ExternalSourceKeywordLookup} from DAO
     * layer.
     *
     * @return {@link ExternalSourceKeywordLookup}
     * @throws ProcessFailed the process failed
     */
    public List<ExternalSourceKeywordLookup> getALLExternalSourceKeywordLookups() throws ProcessFailed;
    
    /**
     * This method save {@link ExternalSourceKeywordLookup} into the database.
     *
     * @param externalSourceKeywordLookup the ExternalSourceKeywordLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(ExternalSourceKeywordLookup externalSourceKeywordLookup) throws ProcessFailed;

    /**
     * This method update {@link ExternalSourceKeywordLookup} updates existing data from the
     * database.
     *
     * @param externalSourceKeywordLookup the externalSourceKeywordLookup
     * @throws ProcessFailed the process failed
     */
    public void update(ExternalSourceKeywordLookup externalSourceKeywordLookup) throws ProcessFailed;

    /**
     * This method delete particular {@link ExternalSourceKeywordLookup} based on the
     * externalSourceKeyword from the database.
     *
     * @param externalSourceKeywordLookup the externalSourceKeywordLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(ExternalSourceKeywordLookup externalSourceKeywordLookup) throws ProcessFailed;
    
    
}
