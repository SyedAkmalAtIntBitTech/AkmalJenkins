/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingCategoryProgram;
import java.util.List;

/**
 * <code>{@link MarketingCategoryProgramService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface MarketingCategoryProgramService {
    
    /**
     * This method pass id as input and get the {@link MarketingCategoryProgram} from database.
     *
     * @return {@link MarketingCategoryProgram}
     * @throws ProcessFailed the process failed
     */
    public List<MarketingCategoryProgram> getAllMarketingCategoryPrograms() throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link MarketingAction} from DAO
     * layer.
     *
     * @param marketingCategoryProgramId the marketingCategoryProgramId
     * @return {@link MarketingAction}
     * @throws ProcessFailed the process failed
     */
    public MarketingCategoryProgram getByMarketingCategoryProgramId(Integer marketingCategoryProgramId) throws ProcessFailed;
    
    /**
     * This method save {@link MarketingCategoryProgram} into the database.
     *
     * @param marketingCategoryProgram the marketingCategoryProgram
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(MarketingCategoryProgram marketingCategoryProgram) throws ProcessFailed;

    /**
     * This method update {@link MarketingCategoryProgram} updates existing data from the
     * database.
     *
     * @param marketingCategoryProgram the marketingCategoryProgram
     * @throws ProcessFailed the process failed
     */
    public void update(MarketingCategoryProgram marketingCategoryProgram) throws ProcessFailed;

    /**
     * This method delete particular {@link MarketingCategoryProgram} based on the
     * MarketingCategoryProgram from the database.
     *
     * @param marketingCategoryProgramId the marketingCategoryProgramId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer marketingCategoryProgramId) throws ProcessFailed;
    
}
