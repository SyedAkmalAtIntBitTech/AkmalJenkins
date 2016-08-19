/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.PushedScheduledEntityListDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PushedScheduledEntityList;
import com.intbittech.services.PushedScheduledEntityListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class PushedScheduledEntityListServiceImpl implements PushedScheduledEntityListService {

    private static Logger logger = Logger.getLogger(PushedScheduledEntityListServiceImpl.class);
    @Autowired
    PushedScheduledEntityListDao pushedScheduledEntityListDao;

    /**
     * {@inheritDoc}
     */
    public PushedScheduledEntityList getByPushedScheduledEntityListId(Integer pushedScheduledEntityListId) throws ProcessFailed {

        PushedScheduledEntityList pushedScheduledEntityList = pushedScheduledEntityListDao.getByPushedScheduledEntityListId(pushedScheduledEntityListId);
        if (pushedScheduledEntityList == null) {
            throw new ProcessFailed("No pushed scheduled entity with id" + pushedScheduledEntityListId + ".");
        }
        return pushedScheduledEntityList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(PushedScheduledEntityList pushedScheduledEntityList) throws ProcessFailed {
        return pushedScheduledEntityListDao.save(pushedScheduledEntityList);
    }

    /**
     * {@inheritDoc}
     */
    public void update(PushedScheduledEntityList pushedScheduledEntityList) throws ProcessFailed {
        pushedScheduledEntityListDao.update(pushedScheduledEntityList);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer pushedScheduledEntityListId) throws ProcessFailed {
        
        PushedScheduledEntityList pushedScheduledEntityList = pushedScheduledEntityListDao.getByPushedScheduledEntityListId(pushedScheduledEntityListId);
        if (pushedScheduledEntityList == null) {
            throw new ProcessFailed("No pushed scheduled entity with id" + pushedScheduledEntityListId + ".");
        }
        pushedScheduledEntityListDao.delete(pushedScheduledEntityList);
    }

    /**
     * {@inheritDoc}
     */
    public PushedScheduledEntityList getByPushedScheduledEntityListIdByFranchiseId(Integer franchiseId) throws ProcessFailed {

        PushedScheduledEntityList pushedScheduledEntityList = pushedScheduledEntityListDao.getByPushedScheduledEntityListIdByFranchiseId(franchiseId);
        if (pushedScheduledEntityList == null) {
            throw new ProcessFailed("No pushed scheduled entity with id" + franchiseId + ".");
        }
        return pushedScheduledEntityList;
    }

    /**
     * {@inheritDoc}
     */
    public PushedScheduledEntityList getByPushedScheduledEntityListIdByScheduledEntityListId(Integer scheduledEntityListId) throws ProcessFailed {
        
        PushedScheduledEntityList pushedScheduledEntityList = pushedScheduledEntityListDao.getByPushedScheduledEntityListIdByScheduledEntityListId(scheduledEntityListId);
        if (pushedScheduledEntityList == null) {
            throw new ProcessFailed("No pushed scheduled entity with id" + scheduledEntityListId + ".");
        }
        return pushedScheduledEntityList;
    }
}
