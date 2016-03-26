/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingAction;
import java.util.List;

/**
 * <code> {@link MarketingActionDao} </code> Interface to get MarketingAction details from
 * marketing_action table
 *
 * @author ilyas
 */
public interface MarketingActionDao {
    
    /**
     * This method pass id as input and get the {@link MarketingAction} from database.
     *
     * @return {@link MarketingAction}
     * @throws ProcessFailed the process failed
     */
    public List<MarketingAction> getAllMarketingActions() throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link MarketingAction} from DAO
     * layer.
     *
     * @param marketingProgramId the marketingProgramId
     * @return {@link MarketingAction}
     * @throws ProcessFailed the process failed
     */
    public MarketingAction getByMarketingActionById(Integer marketingActionId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link MarketingAction} from DAO
     * layer.
     *
     * @param marketingProgramId the marketingProgramId
     * @return {@link MarketingAction}
     * @throws ProcessFailed the process failed
     */
    public MarketingAction getByMarketingActionByProgramId(Integer marketingProgramId) throws ProcessFailed;
    
    /**
     * This method save {@link MarketingAction} into the database.
     *
     * @param marketingAction the marketingAction
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(MarketingAction marketingAction) throws ProcessFailed;

    /**
     * This method update {@link MarketingAction} updates existing data from the
     * database.
     *
     * @param marketingAction the marketingAction
     * @throws ProcessFailed the process failed
     */
    public void update(MarketingAction marketingAction) throws ProcessFailed;

    /**
     * This method delete particular {@link MarketingAction} based on the
     * MarketingAction from the database.
     *
     * @param marketingAction the marketingAction
     * @throws ProcessFailed the process failed
     */
    public void delete(MarketingAction marketingAction) throws ProcessFailed;
    
}
