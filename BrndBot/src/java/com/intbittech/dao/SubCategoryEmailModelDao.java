/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationCategoryLookup;
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
     * This method pass id as input and get the
     * {@link SubCategoryEmailModel} from DB.
     *
     * @param subCategoryId is the subCategoryId
     * @return list of {@link SubCategoryEmailModel}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryEmailModel> getAllSubCategoryEmailModel(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method save {@link OrganizationCategoryLookup} into the database.
     *
     * @param organizationCategoryLookup the organizationCategoryLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(OrganizationCategoryLookup organizationCategoryLookup) throws ProcessFailed;

    /**
     * This method update {@link organizationCategoryLookup} updates existing
     * data from the database.
     *
     * @param organizationCategoryLookup the organizationCategoryLookup
     * @throws ProcessFailed the process failed
     */
    public void update(OrganizationCategoryLookup organizationCategoryLookup) throws ProcessFailed;


}
