/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblScheduledEmailList;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface ScheduledEmailListService {
    /**
	 * This method retrieves {@link ScheduledEmailList} by passing id
	 * @param id
	 * @return {@link ScheduledEmailList}
        * @throws java.lang.Throwable 
    */
   public TblScheduledEmailList getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link ScheduledEmailList}
	 * @return {@link scheduledEmailList}
	 * @throws java.lang.Throwable 
    */
   public List<TblScheduledEmailList> getAllScheduledEmailList() throws Throwable;
   
   /**
	 * This method save {@link scheduledEmailList} by passing scheduledEmailList
	 * @param scheduledEmailList
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblScheduledEmailList scheduledEmailList) throws Throwable;
   /**
	 * This method save {@link scheduledEmailList} by passing scheduledEmailList
	 * @param scheduledEmailList
	 * @throws java.lang.Throwable 
    */
   public void update(TblScheduledEmailList scheduledEmailList) throws Throwable;
   
   /**
	 * This method delete {@link scheduledEmailList} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;
   
   /**
	 * This method retrieves all {@link ScheduledEmailList}
         * @param  UserMarketingId
         * @param  isRecuring
         * @param  entityType
	 * @return {@link scheduledEmailList}
	 * @throws java.lang.Throwable 
    */
   public List<TblScheduledEmailList> getAllScheduledEmailListForUserMarketingProgram(Integer UserMarketingId,Boolean isRecuring , String entityType  ) throws Throwable;
    
}
