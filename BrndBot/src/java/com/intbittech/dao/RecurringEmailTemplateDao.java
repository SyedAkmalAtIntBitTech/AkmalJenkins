/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;
import java.util.List;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.RecuringEmailTemplate;
import com.intbittech.model.OrganizationRecuringEmailLookup;



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
     * @param template the RecuringEmailTemplate
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    
   
    
    public Integer save(RecuringEmailTemplate template) throws ProcessFailed; 
    
    
    
    
     /**
     * This method delete particular {@link RecuringEmailTemplate} based on the
     * RecuringEmailTemplate from the database.
     *
     * @param template the recuringEmailTemplate
     * @throws ProcessFailed the process failed
     */
    
    public void delete(RecuringEmailTemplate template) throws ProcessFailed;
    
    /**
     * This method update {@link RecuringEmailTemplate} updates existing data from the
     * database.
     *
     * @param template is  the RecuringEmailTemplate
     * @throws ProcessFailed the process failed
     */
    
    public void update(RecuringEmailTemplate template) throws ProcessFailed;
    
    
    
    
    
    /**
     * This method retrieves the list of {@link RecuringEmailTemplate} from Database.
     *
     * @param  template  is  the recuringEmailTemplate
     * @return {@link SubCategory}
     * @throws ProcessFailed the process failed
     */
    
    public RecuringEmailTemplate getRecuringEmailTemplateById(Integer recuringEmailTemplateId) throws ProcessFailed;
    
    
    /**
     * This method retrieves the list of {@link NonRecuringEmailTemplate} from Database.
     *
     * @param  Integer array as the nonRecuringEmailIds
     * @return {@link RecuringEmailTemplate}
     * @throws ProcessFailed the process failed
     */
    
    public List<RecuringEmailTemplate> getAllNonRecurringEmail(Integer[] nonRecuringEmailIds) throws ProcessFailed;
   
    
    
    
    
               
            
}
