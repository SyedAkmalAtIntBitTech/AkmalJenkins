/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao;

import com.intbit.marketing.model.TblUserMarketingProgram;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface UserMarketingProgramDao {
  /**
	 * This method retrieves {@link userMarketingProgram} by passing id
	 * @param id
	 * @return {@link userMarketingProgram}
        * @throws java.lang.Throwable 
    */
   public TblUserMarketingProgram getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link userMarketingProgram}
	 * @return {@link userMarketingProgram}
	 * @throws java.lang.Throwable 
    */
   public List<TblUserMarketingProgram> getAllUserMarketingProgram() throws Throwable;
   
   public List<TblUserMarketingProgram> getAllUserMarketingProgramByType(Integer user_id, String programType) throws Throwable;
   /**
	 * This method save {@link userMarketingProgram} by passing userMarketingProgram
	 * @param userMarketingProgram
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblUserMarketingProgram userMarketingProgram) throws Throwable;
   /**
	 * This method save {@link userMarketingProgram} by passing userMarketingProgram
	 * @param userMarketingProgram
	 * @throws java.lang.Throwable 
    */
   public void update(TblUserMarketingProgram userMarketingProgram) throws Throwable;
   
   /**
	 * This method delete {@link userMarketingProgram} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;  
   /**
	 * This method retrieves {@link userMarketingProgram} by passing id
	 * @param id
         * @param marketingProgramId
	 * @return {@link userMarketingProgram}
        * @throws java.lang.Throwable 
    */
   public TblUserMarketingProgram  getByUserMarketingProgramIdAndMarketingProgramId(Integer id ,Integer marketingProgramId) throws Throwable; 
}
