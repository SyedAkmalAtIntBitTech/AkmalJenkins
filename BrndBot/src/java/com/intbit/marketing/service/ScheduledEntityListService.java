/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblScheduledEntityList;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface ScheduledEntityListService {
    
    /**
	 * This method retrieves {@link scheduledEntityList} by passing id
	 * @param id
	 * @return {@link scheduledEntityList}
        * @throws java.lang.Throwable 
    */
   public TblScheduledEntityList getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link scheduledEntityList}
	 * @return {@link scheduledEntityList}
	 * @throws java.lang.Throwable 
    */
   public List<TblScheduledEntityList> getAllScheduledEmailList() throws Throwable;
   
   /**
	 * This method save {@link scheduledEntityList} by passing scheduledEntityList
	 * @param scheduledEntityList
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblScheduledEntityList scheduledEntityList) throws Throwable;
   /**
	 * This method save {@link scheduledEntityList} by passing scheduledEntityList
	 * @param scheduledEntityList
	 * @throws java.lang.Throwable 
    */
   public void update(TblScheduledEntityList scheduledEntityList) throws Throwable;
   
   /**
	 * This method delete {@link scheduledEntityList} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;
   
   public List<TblScheduledEntityList> getAllUserScheduledEmailList(Integer userMarketingId,Boolean isRecuring, String entityType) throws Throwable;
    /**
	 * This method retrieves {@link scheduledEntityList} by passing id
	 * @param status
         * @param entityType 
         * @param programStatus
	 * @return {@link scheduledEntityList}
        * @throws java.lang.Throwable 
    */
    public TblScheduledEntityList getLatestApprovedSocialPost(String status,String entityType,String programStatus) throws Throwable;
      /**
	 * This method retrieves {@link scheduledEntityList} by passing id
	 * @param status
         * @param entityType 
         * @param programStatus
         * @param isRecuring
	 * @return {@link scheduledEntityList}
        * @throws java.lang.Throwable 
    */
    public TblScheduledEntityList getLatestApprovedSendEmail(String status,String entityType,String programStatus,Boolean isRecuring) throws Throwable;

//    public TblScheduledEntityList getLatestApprovedFacebookPost(String template_saved, String facebook);
//  
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
     * This method retrieves {@link scheduledEntityList} by passing id
     *
     * @param status
     * @param entityType
     * @param programStatus
     * @param isRecuring 
     * @return {@link scheduledEntityList}
     * @throws java.lang.Throwable
     */
    public String getLatestApprovedEmail(String status, String entityType, String programStatus,Boolean isRecuring) throws Throwable;
}
