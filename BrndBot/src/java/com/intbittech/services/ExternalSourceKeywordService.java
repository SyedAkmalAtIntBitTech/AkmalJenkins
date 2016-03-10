/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSourceKeyword;
import java.util.List;

/**
 * <code>{@link ExternalSourceKeywordService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface ExternalSourceKeywordService {
    
    /**
     * This method pass id as input and get the {@link ExternalSourceKeyword} from DAO
     * layer.
     *
     * @param externalSourceKeywordId the externalSourceKeywordId
     * @return {@link ExternalSourceKeyword}
     * @throws ProcessFailed the process failed
     */
    public ExternalSourceKeyword getByExternalSourceKeywordId(Integer externalSourceKeywordId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link ExternalSourceKeyword} from DAO
     * layer.
     *
     * @return {@link ExternalSourceKeyword}
     * @throws ProcessFailed the process failed
     */
    public List<ExternalSourceKeyword> getAllExternalSourceKeywords() throws ProcessFailed;
    
    /**
     * This method save {@link ExternalSourceKeyword} into the database.
     *
     * @param externalSourceKeyword the ExternalSourceKeyword
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(ExternalSourceKeyword externalSourceKeyword) throws ProcessFailed;

    /**
     * This method update {@link ExternalSourceKeyword} updates existing data from the
     * database.
     *
     * @param externalSourceKeyword the externalSourceKeyword
     * @throws ProcessFailed the process failed
     */
    public void update(ExternalSourceKeyword externalSourceKeyword) throws ProcessFailed;

    /**
     * This method delete particular {@link ExternalSourceKeyword} based on the
     * externalSourceKeyword from the database.
     *
     * @param externalSourceKeywordId the externalSourceKeywordId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer externalSourceKeywordId) throws ProcessFailed;
    
    
}
