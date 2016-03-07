/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryImageModel;
import java.util.List;

/**
 * <code> {@link SubCategoryImageModelDao} </code> Interface to get
 * SubCategoryImageModel details from sub_caterogy_image_model table
 *
 * @author ilyas
 */
public interface SubCategoryImageModelDao {

    /**
     * This method pass id as input and get the {@link SubCategoryImageModel}
     * from database.
     *
     * @param subCategoryImageModelId is the subCategoryImageModelId
     * @return {@link SubCategoryImageModel}
     * @throws ProcessFailed the process failed
     */
    public SubCategoryImageModel getSubCategoryImageModelById(Integer subCategoryImageModelId) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link SubCategoryImageModel}
     * from database.
     *
     * @param subCategoryId is the subCategoryId
     * @return list of {@link SubCategoryImageModel}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryImageModel> getAllSubCategoryImageModel(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method save {@link SubCategoryImageModel} into the database.
     *
     * @param subCategoryImageModel the subCategoryImageModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryImageModel} updates existing data from the
     * database.
     *
     * @param subCategoryImageModel the subCategoryImageModel
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryImageModel} deletes existing data from the
     * database.
     *
     * @param subCategoryImageModel the subCategoryImageModel
     * @throws ProcessFailed the process failed
     */
    public void delete(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed;

}
