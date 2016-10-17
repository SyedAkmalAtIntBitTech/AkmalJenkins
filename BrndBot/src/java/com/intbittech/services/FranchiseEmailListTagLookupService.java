/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.FranchiseEmailListTagLookup;
import com.intbittech.modelmappers.TagAndEmailListDetails;
import java.util.List;

/**
 * <code>{@link FranchiseEmailListTagLookupService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface FranchiseEmailListTagLookupService {
    
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
     * @param franchiseEmailListTagLookupId the franchiseEmailListTagLookupId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer franchiseEmailListTagLookupId) throws ProcessFailed;
    
    
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
    
     /**
     * This method retrieves the list of {@link FranchiseEmailListTagLookup} from DAO layer.
     *
     * @param franchiseId the franchiseId
     * @return {@link FranchiseEmailListTagLookup}
     * @throws ProcessFailed the process failed
     */
    public List<FranchiseEmailListTagLookup> getAllEmailListTagForFranchise(Integer franchiseId) throws ProcessFailed;
    
    /**
     * This method retrieves the list of {@link TagAndEmailListDetails} from DAO layer.
     *
     * @param franchiseId the franchiseId
     * @param companyId the companyId
     * @return {@link FranchiseEmailListTagLookup}
     * @throws ProcessFailed the process failed
     */
    public List<TagAndEmailListDetails> getAllEmailListsAndTagsForFranchise(Integer franchiseId, Integer companyId) throws ProcessFailed;
    
    
}
