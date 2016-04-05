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
    public TblScheduledEntityList getEntityById(Integer id) throws Throwable {
        return  scheduledEntityListDao.getEntityById(id);
    }
   /**
	 * {@inheritDoc}
    */
   public Integer getCurrentRecords(Integer program_id)throws Throwable{
       return scheduledEntityListDao.getCurrentRecords(program_id);
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

   /**
	 * {@inheritDoc}
     */
    public List<TblScheduledEntityList> getAllUserScheduledEmailList(Integer userMarketingId, Boolean isRecurring, String entityType) throws Throwable {
        return  scheduledEntityListDao.getAllScheduledEmailList();
    }

    @Override
    public List<TblScheduledEntityList> getScheduledEntityListIdForEmailType(Integer userMarketingProgramId, Boolean isRecurring) throws Throwable {
        return scheduledEntityListDao.getScheduledEntityListIdForEmailType(userMarketingProgramId, isRecurring);
    }

    @Override
    public List<TblScheduledEntityList> getScheduledEntityListIdForSocialPostType(Integer userMarketingProgramId) throws Throwable {
        return scheduledEntityListDao.getScheduledEntityListIdForSocialPostType(userMarketingProgramId);
    }

    /**
	 * {@inheritDoc}
     */
    public TblScheduledEntityList getLatestApprovedSocialPost(String status, String entityType,String programStatus) throws Throwable {
       return scheduledEntityListDao.getLatestApprovedFacebookPost(status, entityType,programStatus);
    }

    /**
	 * {@inheritDoc}
     */
    public TblScheduledEntityList getLatestApprovedSendEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws Throwable {
        return scheduledEntityListDao.getLatestApprovedSendEmail(status, entityType, programStatus, isRecurring);
          }

    /**
	 * {@inheritDoc}
     */
    public String getLatestApprovedPost(String status, String entityType, String programStatus) throws Throwable {
        return  scheduledEntityListDao.getLatestApprovedPost(status, entityType, programStatus);
    }

    /**
	 * {@inheritDoc}
     */
    public TblScheduledEntityList getScheduledEntityListByEntityId(Integer entityId) throws Throwable {
        return scheduledEntityListDao.getScheduledEntityListByEntityId(entityId);
    }

    @Override
    public String getLatestApprovedEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws Throwable {
        return scheduledEntityListDao.getLatestApprovedEmail(status, entityType, programStatus, isRecurring);
    }
    
}
