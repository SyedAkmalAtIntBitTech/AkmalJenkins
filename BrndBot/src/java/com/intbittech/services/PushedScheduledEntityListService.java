/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PushedScheduledEntityList;
import java.util.List;

/**
 * <code>{@link PushedScheduledEntityListService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface PushedScheduledEntityListService {
    
     /**
     * This method pass id as input and get the {@link PushedScheduledEntityList} from DAO
     * layer.
     * @param pushedScheduledEntityListId the pushedScheduledEntityListId
     * @return {@link PushedScheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public PushedScheduledEntityList getByPushedScheduledEntityListId(Integer pushedScheduledEntityListId) throws ProcessFailed;
    
    /**
     * This method save {@link PushedScheduledEntityList} into the database.
     *
     * @param pushedScheduledEntityList the pushedScheduledEntityList
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(PushedScheduledEntityList pushedScheduledEntityList) throws ProcessFailed;

    /**
     * This method update {@link PushedScheduledEntityList} updates existing data from the
     * database.
     *
     * @param pushedScheduledEntityList the pushedScheduledEntityList
     * @throws ProcessFailed the process failed
     */
    public void update(PushedScheduledEntityList pushedScheduledEntityList) throws ProcessFailed;

    /**
     * This method delete particular {@link pushedScheduledEntityList} based on the
     * Pushed Scheduled Entity List from the database.
     *
     * @param pushedScheduledEntityListId the pushedScheduledEntityListId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer pushedScheduledEntityListId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link PushedScheduledEntityList} from DAO
     * layer.
     * @param franchiseId the franchiseId
     * @return {@link PushedScheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public List<PushedScheduledEntityList> getAllPushedScheduledEntityListIdByFranchiseId(Integer franchiseId) throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link PushedScheduledEntityList} from DAO
     * layer.
     * @param scheduledEntityListId the scheduledEntityListId
     * @return {@link PushedScheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public List<PushedScheduledEntityList> getAllPushedScheduledEntityListIdByScheduledEntityListId(Integer scheduledEntityListId) throws ProcessFailed;
    
}