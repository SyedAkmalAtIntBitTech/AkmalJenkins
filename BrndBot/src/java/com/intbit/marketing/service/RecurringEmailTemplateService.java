/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblRecurringEmailTemplate;
import java.util.List;

/**
 *
 * @author intbit-6
 */
public interface RecurringEmailTemplateService {
    
    /**
	 * This method retrieves {@link RecurringEmailTemplate} by passing id
	 * @param id
	 * @return {@link RecurringEmailTemplate}
        * @throws java.lang.Throwable 
    */
   public TblRecurringEmailTemplate getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link RecurringEmailTemplate}
	 * @return {@link RecurringEmailTemplate}
	 * @throws java.lang.Throwable 
    */
   public List<TblRecurringEmailTemplate> getAllRecurringEmailTemplate() throws Throwable;
   
   /**
	 * This method save {@link RecurringEmailTemplate} by passing recurringEmailTemplate
	 * @param recurringEmailTemplate
	 * @throws java.lang.Throwable 
    */
   public Integer save(TblRecurringEmailTemplate recurringEmailTemplate) throws Throwable;
   /**
	 * This method save {@link RecurringEmailTemplate} by passing recurringEmailTemplate
	 * @param recurringEmailTemplate
	 * @throws java.lang.Throwable 
    */
   public void update(TblRecurringEmailTemplate recurringEmailTemplate) throws Throwable;
   
   /**
	 * This method delete {@link recurringEmailTemplate} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;  
    
}
