/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryPrintModel;
import java.util.List;

/**
 * <code>{@link SubCategoryPrintModelService}</code> is service layer interface
 * for communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface SubCategoryPrintModelService {

    /**
     * This method pass id as input and get the {@link SubCategoryPrintModel}
     * from DAO layer.
     *
     * @param subCategoryId is the subCategoryId
     * @return list of {@link SubCategoryPrintModel}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryPrintModel> getAllSubCategoryPrintModel(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method save {@link SubCategoryPrintModel} into the database via DAO
     * layer.
     *
     * @param subCategoryPrintModel the subCategoryPrintModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryPrintModel} updates existing data from the
     * database via DAO layer.
     *
     * @param subCategoryPrintModel the subCategoryPrintModel
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryPrintModel} deletes existing data from the
     * database via DAO layer.
     *
     * @param subCategoryPrintModelId the subCategoryPrintModelId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer subCategoryPrintModelId) throws ProcessFailed;

}
