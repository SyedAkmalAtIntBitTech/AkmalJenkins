/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ScheduledEntityList;
import java.util.List;

/**
 *
 * @author ajit
 */
// To-do refactor all the method 
public interface ScheduledEntityListDao {

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param id
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public ScheduledEntityList getById(Integer id) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param id
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public ScheduledEntityList getEntityById(Integer id) throws ProcessFailed;

    /**
     * This method retrieves all {@link scheduledEntityList}
     *
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public List<ScheduledEntityList> getAllScheduledEmailList() throws ProcessFailed;

    /**
     * This method save {@link scheduledEntityList} by passing
     * scheduledEntityList
     *
     * @param scheduledEntityList
     * @throws ProcessFailed the process failed
     */
    public Integer save(ScheduledEntityList scheduledEntityList) throws ProcessFailed;

    /**
     * This method save {@link scheduledEntityList} by passing
     * scheduledEntityList
     *
     * @param scheduledEntityList
     * @throws ProcessFailed the process failed
     */
    public void update(ScheduledEntityList scheduledEntityList) throws ProcessFailed;

    /**
     * This method delete {@link scheduledEntityList} by passing id
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer id) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param userMarketingProgramId
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public List<ScheduledEntityList> getScheduledEntityLisId(Integer userMarketingProgramId) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public ScheduledEntityList getLatestApprovedFacebookPost(String status, String entityType, String programStatus) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @param isRecurring
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public ScheduledEntityList getLatestApprovedSendEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public String getLatestApprovedPost(String status, String entityType, String programStatus) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param entityId
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public ScheduledEntityList getScheduledEntityListByEntityId(Integer entityId) throws ProcessFailed;

    /**
     * This method retrieves all {@link scheduledEntityList}
     *
     * @param program_id
     * @param status
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public Integer getCurrentRecords(Integer program_id) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param userMarketingProgramId
     * @param isRecurring
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public List<ScheduledEntityList> getScheduledEntityListIdForEmailType(Integer userMarketingProgramId, Boolean isRecurring) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param userMarketingProgramId
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public List<ScheduledEntityList> getScheduledEntityListIdForSocialPostType(Integer userMarketingProgramId) throws ProcessFailed;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @param isRecurring
     * @return {@link scheduledEntityList}
     * @throws ProcessFailed the process failed
     */
    public String getLatestApprovedEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws ProcessFailed;
}
