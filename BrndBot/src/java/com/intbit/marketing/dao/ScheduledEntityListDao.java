/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao;

import com.intbit.marketing.model.TblScheduledEntityList;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface ScheduledEntityListDao {

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param id
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public TblScheduledEntityList getById(Integer id) throws Throwable;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param id
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public TblScheduledEntityList getEntityById(Integer id) throws Throwable;
    
    /**
     * This method retrieves all {@link scheduledEntityList}
     *
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public List<TblScheduledEntityList> getAllScheduledEmailList() throws Throwable;

    /**
     * This method save {@link scheduledEntityList} by passing
     * scheduledEntityList
     *
     * @param scheduledEntityList
     * @throws java.lang.Throwable
     */
    public Integer save(TblScheduledEntityList scheduledEntityList) throws Throwable;

    /**
     * This method save {@link scheduledEntityList} by passing
     * scheduledEntityList
     *
     * @param scheduledEntityList
     * @throws java.lang.Throwable
     */
    public void update(TblScheduledEntityList scheduledEntityList) throws Throwable;

    /**
     * This method delete {@link scheduledEntityList} by passing id
     *
     * @param id
     * @throws java.lang.Throwable
     */
    public void delete(Integer id) throws Throwable;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param userMarketingProgramId
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public List<TblScheduledEntityList> getScheduledEntityLisId(Integer userMarketingProgramId) throws Throwable;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public TblScheduledEntityList getLatestApprovedFacebookPost(String status, String entityType, String programStatus) throws Throwable;

    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @param isRecurring
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public TblScheduledEntityList getLatestApprovedSendEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws Throwable;
      /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public String getLatestApprovedPost(String status, String entityType, String programStatus) throws Throwable;
    /**
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param entityId
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public TblScheduledEntityList getScheduledEntityListByEntityId(Integer entityId) throws Throwable;
    
   
   /**
	 * This method retrieves all {@link scheduledEntityList}
         * @param program_id
         * @param status
	 * @return {@link scheduledEntityList}
	 * @throws java.lang.Throwable 
    */
   public Integer getCurrentRecords(Integer program_id)throws Throwable;
  
   /**
	 * This method retrieves {@link scheduledEntityList} by passing id
	 * @param userMarketingProgramId
         * @param isRecurring
	 * @return {@link scheduledEntityList}
        * @throws java.lang.Throwable 
    */
   public List<TblScheduledEntityList> getScheduledEntityListIdForEmailType(Integer userMarketingProgramId,Boolean isRecurring) throws Throwable;
    /**
	 * This method retrieves {@link scheduledEntityList} by passing id
	 * @param userMarketingProgramId
	 * @return {@link scheduledEntityList}
        * @throws java.lang.Throwable 
    */
   public List<TblScheduledEntityList> getScheduledEntityListIdForSocialPostType(Integer userMarketingProgramId) throws Throwable;
     /** This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @param isRecurring 
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public String getLatestApprovedEmail(String status, String entityType, String programStatus,Boolean isRecurring) throws Throwable;
}
