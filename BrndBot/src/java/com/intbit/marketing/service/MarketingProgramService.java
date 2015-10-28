/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblMarketingProgram;
import java.util.List;

/**
 *
 * @author development
 */
public interface MarketingProgramService {
/**
	 * This method retrieves {@link TblMarketingProgram} by passing id
	 * @param id
	 * @return {@link TblMarketingProgram}
        * @throws java.lang.Throwable 
    */
   public TblMarketingProgram getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link MarketingProgram}
	 * @return {@link MarketingProgram}
	 * @throws java.lang.Throwable 
    */
   public List<TblMarketingProgram> getAllTblMarketingProgram() throws Throwable;
   
   /**
	 * This method save {@link MarketingProgram} by passing marketingProgram
	 * @param marketingProgram
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblMarketingProgram marketingProgram) throws Throwable;
   /**
	 * This method save {@link MarketingProgram} by passing marketingProgram
	 * @param marketingProgram
	 * @throws java.lang.Throwable 
    */
   public void update(TblMarketingProgram marketingProgram) throws Throwable;
   
   /**
	 * This method delete {@link marketingProgram} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;    
}
