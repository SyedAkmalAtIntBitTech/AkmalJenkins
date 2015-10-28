/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao;


import com.intbit.marketing.model.TblMarketingProgramUsersLookup;
import java.util.List;

/**
 *
 * @author development
 */
public interface MarketingProgramUsersDao {
    /**
	 * This method retrieves {@link TblMarketingCategory} by passing id
	 * @param id
	 * @return {@link TblMarketingCategory}
        * @throws java.lang.Throwable 
    */
   public TblMarketingProgramUsersLookup getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link MarketingCategory}
	 * @return {@link MarketingCategory}
	 * @throws java.lang.Throwable 
    */
   public List<TblMarketingProgramUsersLookup> getAllMarketingProgramUsers() throws Throwable;
   
   /**
	 * This method save {@link MarketingCategory} by passing marketingCategory
	 * @param marketingCategory
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblMarketingProgramUsersLookup marketingCategoryUser) throws Throwable;
   
   /**
	 * This method delete {@link MarketingCategory} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;    
}
