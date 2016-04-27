/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.service.impl;

import com.intbit.marketing.service.impl.*;
import com.intbittech.marketing.dao.ScheduledEmailListDao;
import com.intbittech.marketing.service.ScheduledEmailListService;
import com.intbittech.model.ScheduledEmailList;
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
public class ScheduledEmailListServiceImpl implements ScheduledEmailListService {

    @Autowired
    public ScheduledEmailListDao scheduledEmailListDao;

    /**
     * {@inheritDoc}
     */
    public ScheduledEmailList getById(Integer id) throws Throwable {
        return scheduledEmailListDao.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<ScheduledEmailList> getAllScheduledEmailList() throws Throwable {
        return scheduledEmailListDao.getAllScheduledEmailList();
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ScheduledEmailList scheduledEmailList) throws Throwable {
        return scheduledEmailListDao.save(scheduledEmailList);
    }

    /**
     * {@inheritDoc}
     */
    public void update(ScheduledEmailList scheduledEmailList) throws Throwable {
        scheduledEmailListDao.update(scheduledEmailList);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
        scheduledEmailListDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<ScheduledEmailList> getAllScheduledEmailListForCompanyMarketingProgram(Integer comapanyMarketingProgramId, Boolean isRecurring) throws Throwable {
        return scheduledEmailListDao.getAllScheduledEmailListForCompanyMarketingProgram(comapanyMarketingProgramId, isRecurring);
    }

}
