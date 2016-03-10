/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryImageModel;
import java.util.List;

/**
 * <code>{@link SubCategoryImageModelService}</code> is service layer interface
 * for communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface SubCategoryImageModelService {

    /**
     * This method pass id as input and get the {@link SubCategoryImageModel}
     * from DAO layer.
     *
     * @param subCategoryId is the subCategoryId
     * @return list of {@link SubCategoryImageModel}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryImageModel> getAllSubCategoryImageModel(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method save {@link SubCategoryImageModel} into the database via DAO
     * layer.
     *
     * @param subCategoryImageModel the subCategoryImageModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryImageModel} updates existing data from the
     * database via DAO layer.
     *
     * @param subCategoryImageModel the subCategoryImageModel
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryImageModel} deletes existing data from the
     * database via DAO layer.
     *
     * @param subCategoryImageModelId the subCategoryImageModelId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer subCategoryImageModelId) throws ProcessFailed;

}
