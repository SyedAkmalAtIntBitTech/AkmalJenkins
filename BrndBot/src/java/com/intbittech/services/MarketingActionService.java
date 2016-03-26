/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingAction;
import java.util.List;

/**
 * <code>{@link MarketingActionService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface MarketingActionService {
    
    /**
     * This method pass id as input and get the {@link MarketingAction} from Dao Layer.
     *
     * @return {@link MarketingAction}
     * @throws ProcessFailed the process failed
     */
    public List<MarketingAction> getAllMarketingActions() throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link MarketingAction} from DAO
     * layer.
     *
     * @param marketingActionId the marketingActionId
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
     * This method save {@link MarketingAction} into the Dao Layer.
     *
     * @param marketingAction the marketingAction
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(MarketingAction marketingAction) throws ProcessFailed;

    /**
     * This method update {@link MarketingAction} updates existing data from the
     * Dao Layer.
     *
     * @param marketingAction the marketingAction
     * @throws ProcessFailed the process failed
     */
    public void update(MarketingAction marketingAction) throws ProcessFailed;

    /**
     * This method delete particular {@link MarketingAction} based on the
     * MarketingAction from the Dao Layer.
     *
     * @param marketingActionId the marketingActionId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer marketingActionId) throws ProcessFailed;
    
}
