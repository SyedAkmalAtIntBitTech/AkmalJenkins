/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbittech.model.MarketingAction;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface MarketingActionService {
    /**
	 * This method retrieves {@link MarketingAction} by passing id
	 * @param id
	 * @return {@link MarketingAction}
        * @throws java.lang.Throwable 
    */
   public MarketingAction getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link MarketingAction}
	 * @return {@link MarketingProgram}
	 * @throws java.lang.Throwable 
    */
   public List<MarketingAction> getAllMarketingAction() throws Throwable;
   
   /**
	 * This method save {@link MarketingAction} by passing marketingAction
	 * @param marketingAction
	 * @throws java.lang.Throwable 
    */
   public Integer save(MarketingAction marketingAction) throws Throwable;
   /**
	 * This method save {@link marketingAction} by passing marketingAction
	 * @param marketingAction
	 * @throws java.lang.Throwable 
    */
   public void update(MarketingAction marketingAction) throws Throwable;
   
   /**
	 * This method delete {@link marketingAction} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;
   /**
	 * This method retrieves {@link MarketingAction} by passing id
	 * @param mCategoryId
         * @param mProgramId
	 * @return {@link MarketingAction}
        * @throws java.lang.Throwable 
    */
   public MarketingAction getMarketingActionByMCategoryIdAndMProgramId(Integer mCategoryId,Integer mProgramId) throws Throwable; 
    
    
}
