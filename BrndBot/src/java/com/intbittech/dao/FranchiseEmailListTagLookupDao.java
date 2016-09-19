/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.FranchiseEmailListTagLookup;
import java.util.List;

/**
 * <code> {@link FranchiseEmailListTagLookupDao} </code> Interface to get FranchiseEmailListTagLookup details from
 * FranchiseEmailListTagLookup table
 *
 * @author Ajit
 */
public interface FranchiseEmailListTagLookupDao {
    
     /**
     * This method save {@link FranchiseEmailListTagLookup} into the database.
     *
     * @param franchiseEmailListTagLookup the franchiseEmailListTagLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed;
    
    /**
     * This method update {@link FranchiseEmailListTagLookup} updates existing data from the
     * database.
     *
     * @param franchiseEmailListTagLookup the franchiseEmailListTagLookup
     * @throws ProcessFailed the process failed
     */
    public void update(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed;

    /**
     * This method delete particular {@link FranchiseEmailListTagLookup} based on the
     * franchiseEmailListTagLookup from the database.
     *
     * @param franchiseEmailListTagLookup the franchiseEmailListTagLookup
     * @throws ProcessFailed the process failed
     */
    public void delete(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed;
    
    
     /**
     * This method retrieves the list of {@link FranchiseEmailListTagLookup} from DAO layer.
     *
     * @param franchiseId the franchiseId
     * @return {@link FranchiseEmailListTagLookup}
     * @throws ProcessFailed the process failed
     */
    public List<FranchiseEmailListTagLookup> getAllFranchiseEmailListTagLookup(Integer franchiseId) throws ProcessFailed;
    
    /**
     * This method retrieves the list of {@link FranchiseEmailListTagLookup} from DAO layer.
     *
     * @param franchiseEmailListTagLookupId the franchiseEmailListTagLookupId
     * @return {@link FranchiseEmailListTagLookup}
     * @throws ProcessFailed the process failed
     */
    public FranchiseEmailListTagLookup getFranchiseEmailListTagLookupByFranchiseEmailListTagLookupId(Integer franchiseEmailListTagLookupId) throws ProcessFailed;
    
}
