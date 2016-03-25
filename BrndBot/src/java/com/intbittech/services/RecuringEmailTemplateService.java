/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationRecuringEmailLookup;
import com.intbittech.model.RecuringEmailTemplate;
import java.util.List;

/**
 * <code>{@link RecuringEmailTemplateService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Mohammed-Tameem
 */
public interface RecuringEmailTemplateService 
{
      
    /**
     * This method pass id as input and get the {@link OrganizationEmaiLookup}
     * from DAO layer.
     *
     * @param organizationRecuringEmailLookup is the OrganizationRecuringEmailLookup
     * @return list of {@link OrganizationRecuringEmailLookup}
     * @throws ProcessFailed the process failed
     */
     public List<OrganizationRecuringEmailLookup>  getAllRecuringByOrganizationId(Integer organizationRecuringEmailLookup)throws ProcessFailed;
     
     
    /**
     * This method save {@link RecuringEmailTemlate} into the database via DAO
     * layer.
     *
     * @param template is  the RecuringEmailTemplate
     * @return the Integer
     * @throws ProcessFailed the process failed
     */ 
    
    public Integer save(RecuringEmailTemplate template) throws ProcessFailed; 
    
    
    
    /**
     * This method {@link RecuringEmailTemplate} deletes existing data from the
     * database via DAO layer.
     *
     * @param template is  the RecuringEmailTemplate
     * @throws ProcessFailed the process failed
     */
    
    
    public void delete(Integer id) throws ProcessFailed;
    
    
    /**
     * This method {@link RecuringEmailTemplate} updates existing data from the
     * database via DAO layer.
     *
     * @param id is  the RecuringEmailTemplate
     * @throws ProcessFailed the process failed
     */
    
    public void update(RecuringEmailTemplate template) throws ProcessFailed;
      
    
    
    
    /**
     * This method pass id as input and get the {@link RecuringEmailTemplate} from DAO
     * layer.
     *
     * @return {@link RecuringEmailTemplate}
     * @throws ProcessFailed the process failed
     */ 
    
    
    public RecuringEmailTemplate getRecuringEmailTemplateById(Integer recuringEmailTemplateId) throws ProcessFailed;
    
    
    
    /**
     * This method save {@link OrganizationRecuringEmailLookup} into the database via DAO
     * layer.
     *
     * @param organizationRecuringEmailLookup is  the OrganizationRecuringEmailLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */ 
    
    
    
    
    public Integer saveRecuringEmailOrg(OrganizationRecuringEmailLookup organizationRecuringEmailLookup) throws ProcessFailed;
    
    
    
    /**
     * This method deletes {@link OrganizationRecuringEmailLookup} into the database via DAO
     * layer.
     *
     * @param organizationRecuringEmailLookup is  the OrganizationRecuringEmailLookup
     * 
     * @throws ProcessFailed the process failed
     */ 
    
    
    public void deleteRecuringEmailOrg(Integer organizationRecuringEmailLookup)throws ProcessFailed;
    
    
    /**
     * This method returns {@link RecuringEmailTemplate} into the database via DAO
     * layer.
     *
     * @param Integer 
     * @return list of recuringEmailTemplate
     * 
     * @throws ProcessFailed the process failed
     */ 
    
    
     public List<RecuringEmailTemplate> getAllNonRecuringEmail(Integer nonRecuringEmailId) throws ProcessFailed;
    
}
