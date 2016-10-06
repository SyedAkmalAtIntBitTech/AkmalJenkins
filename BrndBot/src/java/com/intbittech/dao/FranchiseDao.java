/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Franchise;
import java.util.List;

/**
 * <code> {@link FranchiseDao} </code> Interface to get Franchise details from
 * Franchise table
 *
 * @author AbdulRaqeeb
 */
public interface FranchiseDao {
   
    
    /**
     * This method pass id as input and get the {@link Franchise} from DAO
     * layer.
     *
     * @param franchiseId the franchiseId
     * @return {@link Franchise}
     * @throws ProcessFailed the process failed
     */
    public Franchise getByFranchiseId(Integer franchiseId) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link Franchise} from DAO
     * layer.
     *
     * @param franchiseName the franchiseId
     * @return {@link Franchise}
     * @throws ProcessFailed the process failed
     */
    public Franchise getByFranchiseName(String franchiseName) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link Franchise} from DAO
     * layer.
     *
     * @return {@link Franchise}
     * @throws ProcessFailed the process failed
     */
    public List<Franchise> getAllFranchise() throws ProcessFailed;
    
    /**
     * This method save {@link Franchise} into the database.
     *
     * @param franchise the Franchise
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(Franchise franchise) throws ProcessFailed;

    /**
     * This method update {@link Franchise} updates existing data from the
     * database.
     *
     * @param franchise the franchise
     * @throws ProcessFailed the process failed
     */
    public void update(Franchise franchise) throws ProcessFailed;

    /**
     * This method delete particular {@link Franchise} based on the
     * organization from the database.
     *
     * @param franchise the franchise
     * @throws ProcessFailed the process failed
     */
    public void delete(Franchise franchise) throws ProcessFailed;
    
    
    
}
