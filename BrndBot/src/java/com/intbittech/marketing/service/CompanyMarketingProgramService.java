/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.service;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyMarketingProgram;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface CompanyMarketingProgramService {
    /**
	 * This method retrieves {@link companyMarketingProgram} by passing id
	 * @param id
	 * @return {@link companyMarketingProgram}
        * @throws ProcessFailed the process failed 
    */
   public CompanyMarketingProgram getById(Integer id) throws ProcessFailed; 
   
   /**
	 * This method retrieves all {@link companyMarketingProgram}
	 * @return {@link companyMarketingProgram}
	 * @throws ProcessFailed the process failed 
    */
   public List<CompanyMarketingProgram> getAllCompanyMarketingProgram() throws ProcessFailed;

   /**
	 * This method retrieves all {@link companyMarketingProgram}
         * @param status is the status
         * @param companyId is the companyId
	 * @return {@link companyMarketingProgram}
	 * @throws ProcessFailed the process failed
    */
   public List<CompanyMarketingProgram> getAllCompanyMarketingOpenPrograms(String status, Integer companyId) throws ProcessFailed;
   
    /**
	 * This method retrieves all {@link companyMarketingProgram}
         * @param companyId is the companyId
	 * @return {@link companyMarketingProgram}
	 * @throws ProcessFailed the process failed
    */
   public List<CompanyMarketingProgram> getAllCompanyMarketingProgramByCompanyId(Integer companyId) throws ProcessFailed;
   
   /**
	 * This method retrieves all {@link companyMarketingProgram}
         * @param companyId is the companyId
	 * @return {@link companyMarketingProgram}
	 * @throws ProcessFailed the process failed
    */
   public List<CompanyMarketingProgram> getAllCompanyMarketingProgramBySessionCompanyId(Integer companyId) throws ProcessFailed;
   
   
   /**
	 * This method retrieves all {@link companyMarketingProgram}
         * @param companyId is the companyId
         * @param programType is the programType
	 * @return {@link companyMarketingProgram}
	 * @throws ProcessFailed the process failed
    */
   public List<CompanyMarketingProgram> getAllCompanyMarketingProgramByType(Integer companyId, String programType) throws ProcessFailed;
   /**
	 * This method save {@link companyMarketingProgram} by passing companyMarketingProgram
	 * @param companyMarketingProgram
	 * @throws ProcessFailed the process failed
    */
   public Integer save(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed;
   /**
	 * This method save {@link companyMarketingProgram} by passing companyMarketingProgram
	 * @param companyMarketingProgram
	 * @throws ProcessFailed the process failed
    */
   public void update(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed;
   
   /**
	 * This method delete {@link companyMarketingProgram} by passing id
	 * @param id
	 * @throws ProcessFailed the process failed
    */
   public void delete(Integer id) throws ProcessFailed;  
   /**
	 * This method retrieves {@link companyMarketingProgram} by passing id
	 * @param id
         * @param marketingProgramId
	 * @return {@link companyMarketingProgram}
        * @throws ProcessFailed the process failed
    */
   public CompanyMarketingProgram  getByCompanyMarketingProgramIdAndMarketingProgramId(Integer id ,Integer marketingProgramId) throws ProcessFailed; 
    
}
