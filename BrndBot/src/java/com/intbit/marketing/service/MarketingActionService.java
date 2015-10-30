/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblMarketingAction;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface MarketingActionService {
    /**
	 * This method retrieves {@link TblMarketingAction} by passing id
	 * @param id
	 * @return {@link TblMarketingAction}
        * @throws java.lang.Throwable 
    */
   public TblMarketingAction getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link MarketingAction}
	 * @return {@link MarketingProgram}
	 * @throws java.lang.Throwable 
    */
   public List<TblMarketingAction> getAllMarketingAction() throws Throwable;
   
   /**
	 * This method save {@link MarketingAction} by passing marketingAction
	 * @param marketingAction
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblMarketingAction marketingAction) throws Throwable;
   /**
	 * This method save {@link marketingAction} by passing marketingAction
	 * @param marketingAction
	 * @throws java.lang.Throwable 
    */
   public void update(TblMarketingAction marketingAction) throws Throwable;
   
   /**
	 * This method delete {@link marketingAction} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;
   /**
	 * This method retrieves {@link TblMarketingAction} by passing id
	 * @param mCategoryId
         * @param mProgramId
	 * @return {@link TblMarketingAction}
        * @throws java.lang.Throwable 
    */
   public TblMarketingAction getMarketingActionByMCategoryIdAndMProgramId(Integer mCategoryId,Integer mProgramId) throws Throwable; 
    
    
}
