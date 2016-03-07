/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryEmailModel;
import java.util.List;

/**
 * <code>{@link SubCategoryEmailModelService}</code> is service layer interface
 * for communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface SubCategoryEmailModelService {

    /**
     * This method pass id as input and get the {@link SubCategoryEmailModel}
     * from DAO layer.
     *
     * @param subCategoryId is the subCategoryId
     * @return list of {@link SubCategoryEmailModel}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryEmailModel> getAllSubCategoryEmailModel(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method save {@link SubCategoryEmailModel} into the database via DAO
     * layer.
     *
     * @param subCategoryEmailModel the subCategoryEmailModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryEmailModel} updates existing data from the
     * database via DAO layer.
     *
     * @param subCategoryEmailModel the subCategoryEmailModel
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryEmailModel} deletes existing data from the
     * database via DAO layer.
     *
     * @param subCategoryEmailModelId the subCategoryEmailModelId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer subCategoryEmailModelId) throws ProcessFailed;

}
