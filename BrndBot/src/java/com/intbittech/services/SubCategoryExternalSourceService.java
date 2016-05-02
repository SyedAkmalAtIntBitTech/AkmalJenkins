/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockExternalSource;
import com.intbittech.model.SubCategoryExternalSource;
import java.util.List;

/**
 * <code>{@link SubCategoryExternalSourceService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface SubCategoryExternalSourceService {
    
     /**
     * This method pass id as input and get the {@link SubCategoryExternalSource} from DAO
     * layer.
     *
     * @param subCategoryExternalSourceId the subCategoryExternalSourceId
     * @return {@link SubCategoryExternalSource}
     * @throws ProcessFailed the process failed
     */
    public SubCategoryExternalSource getBySubCategoryExternalSourceId(Integer subCategoryExternalSourceId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link SubCategoryExternalSource} from DAO
     * layer.
     *
     * @return {@link SubCategoryExternalSource}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryExternalSource> getAllSubCategoryExternalSources() throws ProcessFailed;
    
    /**
     * This method save {@link SubCategoryExternalSource} into the database.
     *
     * @param subCategoryExternalSource the subCategoryExternalSource
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(SubCategoryExternalSource subCategoryExternalSource) throws ProcessFailed;

    /**
     * This method update {@link SubCategoryExternalSource} updates existing data from the
     * database.
     *
     * @param subCategoryExternalSource the subCategoryExternalSource
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategoryExternalSource subCategoryExternalSource) throws ProcessFailed;

    /**
     * This method delete particular {@link SubCategoryExternalSource} based on the
     * externalSourceKeyword from the database.
     *
     * @param subCategoryExternalSourceId the subCategoryExternalSourceId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer subCategoryExternalSourceId) throws ProcessFailed;

    public SubCategoryExternalSource getBySubCategoryId(Integer subCategoryId);
    
    
    
}
