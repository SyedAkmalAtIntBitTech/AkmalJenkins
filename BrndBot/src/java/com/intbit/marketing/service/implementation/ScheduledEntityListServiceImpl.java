/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.ScheduledEntityListDao;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.service.ScheduledEntityListService;
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
public class ScheduledEntityListServiceImpl  implements ScheduledEntityListService{
    
   @Autowired
   public ScheduledEntityListDao scheduledEntityListDao;
   
    /**
	 * {@inheritDoc}
     */
    public TblScheduledEntityList getById(Integer id) throws Throwable {
        return  scheduledEntityListDao.getById(id);
    }

   /**
	 * {@inheritDoc}
     */
    public List<TblScheduledEntityList> getAllScheduledEmailList() throws Throwable {
        return  scheduledEntityListDao.getAllScheduledEmailList();
    }

    /**
	 * {@inheritDoc}
     */
    public Integer save(TblScheduledEntityList scheduledEntityList) throws Throwable {
        return  scheduledEntityListDao.save(scheduledEntityList);
    }

    /**
	 * {@inheritDoc}
     */
    public void update(TblScheduledEntityList scheduledEntityList) throws Throwable {
        scheduledEntityListDao.update(scheduledEntityList);
    }

    /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
        scheduledEntityListDao.delete(id);
    }

    @Override
    public List<TblScheduledEntityList> getAllUserScheduledEmailList(Integer userMarketingId, Boolean isRecuring, String entityType) throws Throwable {
        return  scheduledEntityListDao.getAllScheduledEmailList();
    }

    @Override
    public TblScheduledEntityList getLatestApprovedSocialPost(String status, String entityType,String programStatus) throws Throwable {
       return scheduledEntityListDao.getLatestApprovedFacebookPost(status, entityType,programStatus);
    }

   
    
}
