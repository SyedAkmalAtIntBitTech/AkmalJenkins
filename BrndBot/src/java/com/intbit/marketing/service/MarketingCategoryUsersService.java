/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblMarketingCategoryUsersLookup;
import java.util.List;

/**
 *
 * @author development
 */

public interface MarketingCategoryUsersService {
    /**
	 * This method retrieves {@link TblMarketingCategoryUsersLookup} by passing id
	 * @param id
	 * @return {@link TblMarketingCategoryUsersLookup}
        * @throws java.lang.Throwable 
    */
   public TblMarketingCategoryUsersLookup getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link MarketingCategoryUsers}
	 * @return {@link MarketingCategorUsers}
	 * @throws java.lang.Throwable 
    */
   public List<TblMarketingCategoryUsersLookup> getAllMarketingCategoryUsers(Integer user_id) throws Throwable;
   
   /**
	 * This method save {@link MarketingCategoryUsers} by passing marketingCategory
	 * @param marketingCategoryUsers
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblMarketingCategoryUsersLookup marketingCategoryUsers) throws Throwable;
   
   /**
	 * This method delete {@link MarketingCategoryUsers} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;
        
}
