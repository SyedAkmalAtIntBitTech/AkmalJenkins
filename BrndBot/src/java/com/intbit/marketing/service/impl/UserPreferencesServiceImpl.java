/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.impl;

import com.intbit.marketing.dao.UserPreferencesDao;
import com.intbit.marketing.model.TblUserPreferences;
import com.intbit.marketing.service.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ajit
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UserPreferencesServiceImpl implements UserPreferencesService{
    @Autowired 
    public UserPreferencesDao userPreferencesDao;

    @Override
    public TblUserPreferences getById(Integer userId) throws Throwable {
      return  userPreferencesDao.getById(userId);
    }
    
    
}
