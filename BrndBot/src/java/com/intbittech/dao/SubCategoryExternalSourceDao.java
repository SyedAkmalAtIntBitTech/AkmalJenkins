/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryExternalSource;
import java.util.List;

/**
 * <code> {@link SubCategoryExternalSourceDao} </code> Interface to get Sub Category External Source details from
 * Sub Category External Source   table
 *
 * @author Ajit
 */
public interface SubCategoryExternalSourceDao {
    
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
    public List<SubCategoryExternalSource> getALLSubCategoryExternalSources() throws ProcessFailed;
    
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
     * @param subCategoryExternalSource the subCategoryExternalSource
     * @throws ProcessFailed the process failed
     */
    public void delete(SubCategoryExternalSource subCategoryExternalSource) throws ProcessFailed;
    
}
