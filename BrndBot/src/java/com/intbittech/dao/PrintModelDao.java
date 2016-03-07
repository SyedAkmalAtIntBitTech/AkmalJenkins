/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PrintModel;

/**
 * <code> {@link PrintModelDao} </code> Interface to get PrintModel details from
 * print_model table
 *
 * @author ilyas
 */
public interface PrintModelDao {
    
    /**
     * This method pass id as input and get the {@link PrintModel} from database
     *
     * @param printModelId the printModelId
     * @return {@link PrintModel}
     * @throws ProcessFailed the process failed
     */
    public PrintModel getByPrintModelId(Integer printModelId) throws ProcessFailed;
    
    /**
     * This method save {@link PrintModel} into the database.
     *
     * @param printModel the PrintModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(PrintModel printModel) throws ProcessFailed;

    /**
     * This method update {@link PrintModel} updates existing data from the
     * database.
     *
     * @param printModel the PrintModel
     * @throws ProcessFailed the process failed
     */
    public void update(PrintModel printModel) throws ProcessFailed;

    /**
     * This method delete particular {@link PrintModel} based on the
     * PrintModel from the database.
     *
     * @param PrintModel the printModel
     * @throws ProcessFailed the process failed
     */
    public void delete(PrintModel printModel) throws ProcessFailed;
    
}
