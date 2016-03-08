/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryPrintModel;
import java.util.List;

/**
 * <code> {@link SubCategoryPrintModelDao} </code> Interface to get
 * SubCategoryPrintModel details from sub_caterogy_print_model table
 *
 * @author ilyas
 */
public interface SubCategoryPrintModelDao {

    /**
     * This method pass id as input and get the {@link SubCategoryPrintModel}
     * from database.
     *
     * @param subCategoryPrintModelId is the subCategoryPrintModelId
     * @return {@link SubCategoryPrintModel}
     * @throws ProcessFailed the process failed
     */
    public SubCategoryPrintModel getSubCategoryPrintModelById(Integer subCategoryPrintModelId) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link SubCategoryPrintModel}
     * from database.
     *
     * @param subCategoryId is the subCategoryId
     * @return list of {@link SubCategoryPrintModel}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryPrintModel> getAllSubCategoryPrintModel(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method save {@link SubCategoryPrintModel} into the database.
     *
     * @param subCategoryPrintModel the subCategoryPrintModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryPrintModel} updates existing data from the
     * database.
     *
     * @param subCategoryPrintModel the subCategoryPrintModel
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed;

    /**
     * This method {@link SubCategoryPrintModel} deletes existing data from the
     * database.
     *
     * @param subCategoryPrintModel the subCategoryPrintModel
     * @throws ProcessFailed the process failed
     */
    public void delete(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed;

}
