/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblScheduledSocialpostList;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface ScheduledSocialpostListService {
    /**
	 * This method retrieves all {@link SocialpostList}
         * @param  UserMarketingId
	 * @return {@link SocialpostList}
	 * @throws java.lang.Throwable 
    */
   public List<TblScheduledSocialpostList> getAllScheduledSocialpostListForUserMarketingProgram(Integer UserMarketingId,Boolean isRecurring , String entityType  )throws Throwable;
   
    /**
	 * This method retrieves {@link ScheduledSocialpostList} by passing id
	 * @param entityId
	 * @return {@link scheduledEntityList}
        * @throws java.lang.Throwable 
    */
    public TblScheduledSocialpostList getByEntityId(Integer entityId) throws Throwable;     
}