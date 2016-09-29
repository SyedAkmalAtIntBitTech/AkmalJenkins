/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.dao;

import com.intbittech.model.EmailDraft;
import java.util.List;

/**
 *
 * @author development
 */
public interface EmailDraftDao {
    
   /**
	 * This method retrieves {@link EmailDraft} by passing id
	 * @param id
	 * @return {@link EmailDraftDao}
        * @throws java.lang.Throwable 
    */
   public EmailDraft getById(Integer id) throws Throwable; 
   
   /**
	 * This method retrieves all {@link EmailDraft}
         * @param companyId the companyId
         * @param isPushed the isPushed
	 * @return {@link EmailDraft}
	 * @throws java.lang.Throwable 
    */
   public List<EmailDraft> getAllEmailDrafts(Integer companyId,Boolean isPushed) throws Throwable;

   /**
	 * This method save {@link emailDraft} by passing emailDraft
	 * @param emailDraft
	 * @throws java.lang.Throwable 
    */
   public Integer save(EmailDraft emailDraft) throws Throwable;
   /**
	 * This method save {@link emailDraft} by passing emailDraft
	 * @param emailDraft
	 * @throws java.lang.Throwable 
    */
   public void update(EmailDraft emailDraft) throws Throwable;
   
   /**
	 * This method delete {@link emailDraft} by passing id
	 * @param id
	 * @throws java.lang.Throwable 
    */
   public void delete(Integer id) throws Throwable;
}
