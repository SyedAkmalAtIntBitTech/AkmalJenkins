/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblRecuringEmailTemplate;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface RecuringEmailTemplateService {
    
    /**
	 * This method retrieves {@link RecuringEmailTemplate} by passing id
	 * @param id
	 * @return {@link RecuringEmailTemplate}
        * @throws java.lang.Throwable 
    */
   public TblRecuringEmailTemplate getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link RecuringEmailTemplate}
	 * @return {@link RecuringEmailTemplate}
	 * @throws java.lang.Throwable 
    */
   public List<TblRecuringEmailTemplate> getAllRecuringEmailTemplate() throws Throwable;
   
   /**
	 * This method save {@link RecuringEmailTemplate} by passing recuringEmailTemplate
	 * @param recuringEmailTemplate
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblRecuringEmailTemplate recuringEmailTemplate) throws Throwable;
   /**
	 * This method save {@link RecuringEmailTemplate} by passing recuringEmailTemplate
	 * @param recuringEmailTemplate
	 * @throws java.lang.Throwable 
    */
   public void update(TblRecuringEmailTemplate recuringEmailTemplate) throws Throwable;
   
   /**
	 * This method delete {@link recuringEmailTemplate} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;  
    
}
