/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.service.impl;

import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.marketing.dao.ScheduledEntityListDao;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.marketing.service.ScheduledEntityListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intbit-6
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class ScheduledEntityListServiceImpl  implements ScheduledEntityListService{
    
   @Autowired
   public ScheduledEntityListDao scheduledEntityListDao;
   
    /**
	 * {@inheritDoc}
     */
    public ScheduledEntityList getById(Integer id) throws ProcessFailed {
        return  scheduledEntityListDao.getById(id);
    }

    /**
	 * {@inheritDoc}
     */
    public ScheduledEntityList getEntityById(Integer id) throws ProcessFailed {
        return  scheduledEntityListDao.getEntityById(id);
    }
   /**
	 * {@inheritDoc}
    */
   public Integer getCurrentRecords(Integer program_id)throws ProcessFailed{
       return scheduledEntityListDao.getCurrentRecords(program_id);
   }
    
   /**
	 * {@inheritDoc}
     */
    public List<ScheduledEntityList> getAllScheduledEmailList() throws ProcessFailed {
        return  scheduledEntityListDao.getAllScheduledEmailList();
    }

    /**
	 * {@inheritDoc}
     */
    public Integer save(ScheduledEntityList scheduledEntityList) throws ProcessFailed {
        return  scheduledEntityListDao.save(scheduledEntityList);
    }

    /**
	 * {@inheritDoc}
     */
    public void update(ScheduledEntityList scheduledEntityList) throws ProcessFailed {
        scheduledEntityListDao.update(scheduledEntityList);
    }

    /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws ProcessFailed {
        scheduledEntityListDao.delete(id);
    }

   /**
	 * {@inheritDoc}
     */
    public List<ScheduledEntityList> getAllUserScheduledEmailList(Integer userMarketingId, Boolean isRecurring, String entityType) throws ProcessFailed {
        return  scheduledEntityListDao.getAllScheduledEmailList();
    }

    @Override
    public List<ScheduledEntityList> getScheduledEntityListIdForEmailType(Integer userMarketingProgramId, Boolean isRecurring) throws ProcessFailed {
        return scheduledEntityListDao.getScheduledEntityListIdForEmailType(userMarketingProgramId, isRecurring);
    }

    @Override
    public List<ScheduledEntityList> getScheduledEntityListIdForSocialPostType(Integer userMarketingProgramId) throws ProcessFailed {
        return scheduledEntityListDao.getScheduledEntityListIdForSocialPostType(userMarketingProgramId);
    }

    /**
	 * {@inheritDoc}
     */
    public ScheduledEntityList getLatestApprovedSocialPost(String status, String entityType,String programStatus) throws ProcessFailed {
       return scheduledEntityListDao.getLatestApprovedFacebookPost(status, entityType,programStatus);
    }

    /**
	 * {@inheritDoc}
     */
    public ScheduledEntityList getLatestApprovedSendEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws ProcessFailed {
        return scheduledEntityListDao.getLatestApprovedSendEmail(status, entityType, programStatus, isRecurring);
          }

    /**
	 * {@inheritDoc}
     */
    public String getLatestApprovedPost(String status, String entityType, String programStatus) throws ProcessFailed {
        return  scheduledEntityListDao.getLatestApprovedPost(status, entityType, programStatus);
    }

    /**
	 * {@inheritDoc}
     */
    public ScheduledEntityList getScheduledEntityListByEntityId(Integer entityId) throws ProcessFailed {
        return scheduledEntityListDao.getScheduledEntityListByEntityId(entityId);
    }

    @Override
    public String getLatestApprovedEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws ProcessFailed {
        return scheduledEntityListDao.getLatestApprovedEmail(status, entityType, programStatus, isRecurring);
    }

   
    
}
