/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.service;

import com.intbittech.model.ScheduledEmailList;
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
   public ScheduledEmailList getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link ScheduledEmailList}
	 * @return {@link scheduledEmailList}
	 * @throws java.lang.Throwable 
    */
   public List<ScheduledEmailList> getAllScheduledEmailList() throws Throwable;
   
   /**
	 * This method save {@link scheduledEmailList} by passing scheduledEmailList
	 * @param scheduledEmailList
	 * @throws java.lang.Throwable 
    */
   public Integer save(ScheduledEmailList scheduledEmailList) throws Throwable;
   /**
	 * This method save {@link scheduledEmailList} by passing scheduledEmailList
	 * @param scheduledEmailList
	 * @throws java.lang.Throwable 
    */
   public void update(ScheduledEmailList scheduledEmailList) throws Throwable;
   
   /**
	 * This method delete {@link scheduledEmailList} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;
    /**
	 * This method retrieves all {@link ScheduledEmailList}
         * @param  comapanyMarketingProgramId
         * @param  isRecurring
	 * @return {@link scheduledEmailList}
	 * @throws java.lang.Throwable 
    */
   public List<ScheduledEmailList> getAllScheduledEmailListForCompanyMarketingProgram(Integer comapanyMarketingProgramId,Boolean isRecurring) throws Throwable;
    
}