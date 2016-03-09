/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailBlockDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlock;
import com.intbittech.services.EmailBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailBlockServiceImpl implements EmailBlockService {
    
    @Autowired
    private EmailBlockDao emailBlockDao;

    /**
     * {@inheritDoc}
     */
    public EmailBlock getByEmailBlockId(Integer emailBlockId) throws ProcessFailed {
        EmailBlock emailBlock = emailBlockDao.getByEmailBlockId(emailBlockId);
        if(emailBlock == null)
            throw new ProcessFailed("No email block found.");
        return emailBlock;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlock emailBlock) throws ProcessFailed {
        return emailBlockDao.save(emailBlock);
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlock emailBlock) throws ProcessFailed {
        emailBlockDao.update(emailBlock);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer emailBlockId) throws ProcessFailed {
        EmailBlock emailBlock = emailBlockDao.getByEmailBlockId(emailBlockId);
        if(emailBlock == null)
            throw new ProcessFailed("No email block found.");
        emailBlockDao.delete(emailBlock);
    }
    
}
