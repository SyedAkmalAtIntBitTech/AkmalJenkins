/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao;

import com.intbit.marketing.model.TblEmailDraft;
import com.intbit.marketing.model.TblMarketingAction;
import java.util.List;

/**
 *
 * @author development
 */
public interface EmailDraftDao {
    
   /**
	 * This method retrieves {@link TblMarketingAction} by passing id
	 * @param id
	 * @return {@link TblMarketingAction}
        * @throws java.lang.Throwable 
    */
   public TblEmailDraft getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link MarketingAction}
	 * @return {@link MarketingProgram}
	 * @throws java.lang.Throwable 
    */
   public List<TblEmailDraft> getAllEmailDrafts() throws Throwable;
   
   /**
	 * This method save {@link MarketingAction} by passing marketingAction
	 * @param emailDraft
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblEmailDraft emailDraft) throws Throwable;
   /**
	 * This method save {@link marketingAction} by passing marketingAction
	 * @param emailDraft
	 * @throws java.lang.Throwable 
    */
   public void update(TblEmailDraft emailDraft) throws Throwable;
   
   /**
	 * This method delete {@link marketingAction} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;
}
