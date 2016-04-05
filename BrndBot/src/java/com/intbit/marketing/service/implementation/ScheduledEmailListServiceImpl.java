/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.ScheduledEmailListDao;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.service.ScheduledEmailListService;
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
public class ScheduledEmailListServiceImpl  implements ScheduledEmailListService{
    @Autowired
    public ScheduledEmailListDao scheduledEmailListDao;

   /**
	 * {@inheritDoc}
     */
    public TblScheduledEmailList getById(Integer id) throws Throwable {
       return  scheduledEmailListDao.getById(id);
    }

   /**
	 * {@inheritDoc}
     */
    public List<TblScheduledEmailList> getAllScheduledEmailList() throws Throwable {
        return  scheduledEmailListDao.getAllScheduledEmailList();
    }

   /**
	 * {@inheritDoc}
     */
    public Integer save(TblScheduledEmailList scheduledEmailList) throws Throwable {
       return  scheduledEmailListDao.save(scheduledEmailList);
    }

    /**
	 * {@inheritDoc}
     */
    public void update(TblScheduledEmailList scheduledEmailList) throws Throwable {
         scheduledEmailListDao.update(scheduledEmailList);
    }

    /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
        scheduledEmailListDao.delete(id);
    }

    @Override
    public List<TblScheduledEmailList> getAllScheduledEmailListForUserMarketingProgram(Integer UserMarketingId, Boolean isRecurring) throws Throwable {
        return  scheduledEmailListDao.getAllScheduledEmailListForUserMarketingProgram(UserMarketingId, isRecurring);
    }
    
}