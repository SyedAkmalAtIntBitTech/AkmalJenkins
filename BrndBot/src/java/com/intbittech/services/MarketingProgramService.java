/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingProgram;
import java.util.List;

/**
 * <code>{@link MarketingProgramService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface MarketingProgramService {
    
    /**
     * This method pass id as input and get the {@link MarketingProgram} from database.
     *
     * @return {@link MarketingProgram}
     * @throws ProcessFailed the process failed
     */
    public List<MarketingProgram> getAllMarketingPrograms() throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link MarketingProgram} from DAO
     * layer.
     *
     * @param marketingProgramId the marketingProgramId
     * @return {@link MarketingProgram}
     * @throws ProcessFailed the process failed
     */
    public MarketingProgram getByMarketingProgramId(Integer marketingProgramId) throws ProcessFailed;
    
    /**
     * This method save {@link MarketingProgram} into the database.
     *
     * @param marketingProgram the marketingProgram
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(MarketingProgram marketingProgram) throws ProcessFailed;

    /**
     * This method update {@link MarketingProgram} updates existing data from the
     * database.
     *
     * @param marketingProgram the marketingProgram
     * @throws ProcessFailed the process failed
     */
    public void update(MarketingProgram marketingProgram) throws ProcessFailed;

    /**
     * This method delete particular {@link MarketingProgram} based on the
     * MarketingProgram from the database.
     *
     * @param marketingProgramId the marketingProgramId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer marketingProgramId) throws ProcessFailed;
    
}
