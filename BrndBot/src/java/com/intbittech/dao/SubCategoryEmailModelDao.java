/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryEmailModel;
import java.util.List;

/**
 * <code> {@link SubCategoryEmailModelDao} </code> Interface to get
 * SubCategoryEmailModel details from sub_caterogy_email_model table
 *
 * @author ilyas
 */
public interface SubCategoryEmailModelDao {

    /**
     * This method pass id as input and get the {@link SubCategoryEmailModel}
     * from database.
     *
     * @param subCategoryEmailModelId is the subCategoryEmailModelId
     * @return {@link SubCategoryEmailModel}
     * @throws ProcessFailed the process failed
     */
    public SubCategoryEmailModel getSubCategoryEmailModelById(Integer subCategoryEmailModelId) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link SubCategoryEmailModel}
     * from database.
     *
     * @param subCategoryId is the subCategoryId
     * @return list of {@link SubCategoryEmailModel}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryEmailModel> getAllSubCategoryEmailModel(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method save {@link SubCategoryEmailModel} into the database.
     *
     * @param subCategoryEmailModel the subCategoryEmailModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryEmailModel} updates existing data from the
     * database.
     *
     * @param subCategoryEmailModel the subCategoryEmailModel
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryEmailModel} deletes existing data from the
     * database.
     *
     * @param subCategoryEmailModel the subCategoryEmailModel
     * @throws ProcessFailed the process failed
     */
    public void delete(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed;

    public SubCategoryEmailModel getBySubCategoryEmailModelId(Integer emailModelId);

}
