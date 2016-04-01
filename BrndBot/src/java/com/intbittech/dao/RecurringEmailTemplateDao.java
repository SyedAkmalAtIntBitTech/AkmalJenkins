/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;
import java.util.List;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.RecurringEmailTemplate;
import com.intbittech.model.OrganizationRecurringEmailLookup;



/**
 *
 * **
 * <code> {@link RecurringEmailTemplateDao} </code> Interface to get RecurringEmailTemplate details
 * from RecurringEmailTemplate table
 *
 * @author Mohammed-Tameem
 *
 * 
 */
public interface RecurringEmailTemplateDao 
{
    /**
     * This method saves {@link RecurringEmailTemplate} into the database.
     *
     * @param template the RecurringEmailTemplate
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    
   
    
    public Integer save(RecurringEmailTemplate template) throws ProcessFailed; 
    
    
    
    
     /**
     * This method delete particular {@link RecurringEmailTemplate} based on the
 RecurringEmailTemplate from the database.
     *
     * @param template the recurringEmailTemplate
     * @throws ProcessFailed the process failed
     */
    
    public void delete(RecurringEmailTemplate template) throws ProcessFailed;
    
    /**
     * This method update {@link RecurringEmailTemplate} updates existing data from the
     * database.
     *
     * @param template is  the RecurringEmailTemplate
     * @throws ProcessFailed the process failed
     */
    
    public void update(RecurringEmailTemplate template) throws ProcessFailed;
    
    
    
    
    
    /**
     * This method retrieves the list of {@link RecurringEmailTemplate} from Database.
     *
     * @param  template  is  the recurringEmailTemplate
     * @return {@link SubCategory}
     * @throws ProcessFailed the process failed
     */
    
    public RecurringEmailTemplate getRecurringEmailTemplateById(Integer recurringEmailTemplateId) throws ProcessFailed;
    
    
    /**
     * This method retrieves the list of {@link NonRecurringEmailTemplate} from Database.
     *
     * @param  Integer array as the nonRecurringEmailIds
     * @return {@link RecurringEmailTemplate}
     * @throws ProcessFailed the process failed
     */
    
    public List<RecurringEmailTemplate> getAllNonRecurringEmail(Integer[] nonRecurringEmailIds) throws ProcessFailed;
   
    
    
    
    
               
            
}
