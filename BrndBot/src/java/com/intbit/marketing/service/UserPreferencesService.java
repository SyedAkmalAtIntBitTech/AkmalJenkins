/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service;

import com.intbit.marketing.model.TblUserPreferences;

/**
 *
 * @author Ajit
 */
public interface UserPreferencesService {
     /**
	 * This method retrieves {@link TblUserPreferences} by passing id
	 * @param userId
	 * @return {@link TblUserPreferences}
        * @throws java.lang.Throwable 
    */
   public TblUserPreferences getById(Integer userId) throws Throwable; 
    
}
