/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;


import com.intbit.marketing.dao.ScheduledSocialpostListDao;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import com.intbit.marketing.service.ScheduledSocialpostListService;
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
public class ScheduledSocialpostListServiceImpl  implements ScheduledSocialpostListService{
      @Autowired
   public ScheduledSocialpostListDao scheduledSocialpostListDao;

    @Override
    public List<TblScheduledSocialpostList> getAllScheduledSocialpostListForUserMarketingProgram(Integer UserMarketingId, Boolean isRecuring, String entityType) throws Throwable {
       return  scheduledSocialpostListDao.getAllScheduledSocialpostListForUserMarketingProgram(UserMarketingId, isRecuring, entityType);
    }

    @Override
    public TblScheduledSocialpostList getByEntityId(Integer entityId) throws Throwable {
       return scheduledSocialpostListDao.getByEntityId(entityId);
    }
    
}
