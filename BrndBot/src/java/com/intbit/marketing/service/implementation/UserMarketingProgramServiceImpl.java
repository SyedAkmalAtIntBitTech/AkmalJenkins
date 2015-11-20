/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.UserMarketingProgramDao;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.UserMarketingProgramService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intbit-6
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UserMarketingProgramServiceImpl implements UserMarketingProgramService{
    @Autowired
    public UserMarketingProgramDao userMarketingProgramDao;

   /**
	 * {@inheritDoc}
     */
    public TblUserMarketingProgram getById(Integer id) throws Throwable {
        return userMarketingProgramDao.getById(id);
    }

   /**
	 * {@inheritDoc}
     */
    public List<TblUserMarketingProgram> getAllUserMarketingProgram() throws Throwable {
        return  userMarketingProgramDao.getAllUserMarketingProgram();
    }

    /**
	 * {@inheritDoc}
     */
    public Integer save(TblUserMarketingProgram userMarketingProgram) throws Throwable {
       return userMarketingProgramDao.save(userMarketingProgram);
    }

   /**
	 * {@inheritDoc}
     */
    public void update(TblUserMarketingProgram userMarketingProgram) throws Throwable {
       userMarketingProgramDao.update(userMarketingProgram);
    }

    /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
       userMarketingProgramDao.delete(id);
    }

    @Override
    public TblUserMarketingProgram getByUserMarketingProgramIdAndMarketingProgramId(Integer id, Integer marketingProgramId) throws Throwable {
        return  userMarketingProgramDao.getByUserMarketingProgramIdAndMarketingProgramId(id, marketingProgramId);
    }

    @Override
    public List<TblUserMarketingProgram> getAllUserMarketingProgramByType(Integer user_id, String programType) throws Throwable {
        return  userMarketingProgramDao.getAllUserMarketingProgramByType(user_id, programType);
    }

    @Override
    public List<TblUserMarketingProgram> getAllUserMarketingProgramByUserId(Integer user_id) throws Throwable {
        return userMarketingProgramDao.getAllUserMarketingProgramByUserId(user_id);
        }

    @Override
    public List<TblUserMarketingProgram> getAllUserMarketingProgramBySessionUserId(Integer user_id) throws Throwable {
        return userMarketingProgramDao.getAllUserMarketingProgramBySessionUserId(user_id);
    }

   
    
}
