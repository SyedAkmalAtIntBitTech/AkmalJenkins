/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.SendGridSubUserDetailsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SendGridSubUserDetails;
import com.intbittech.services.SendGridSubUserDetailsService;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class SendGridSubUserDetailsServiceImpl implements SendGridSubUserDetailsService {

    private static Logger logger = Logger.getLogger(SendGridSubUserDetailsServiceImpl.class);
    @Autowired
    private SendGridSubUserDetailsDao gridSubUserDetailsDao;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getBySendGridSubUserDetailsId(Integer sendGridSubUserDetailsId) throws ProcessFailed {
        SendGridSubUserDetails sendGridSubUserDetails = gridSubUserDetailsDao.getBySendGridSubUserDetailsId(sendGridSubUserDetailsId);
        if (sendGridSubUserDetails == null) {
            throw new ProcessFailed(messageSource.getMessage("globalColors_list_not_found", new String[]{}, Locale.US));
        }
        return sendGridSubUserDetails;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed {
        SendGridSubUserDetails sendGridSubUserDetailsObject = gridSubUserDetailsDao.getSendGridSubUserDetailsBySendGridSubUserIdAndCompanyId(sendGridSubUserDetails.getSendGridUserId(), sendGridSubUserDetails.getFkCompanyId().getCompanyId());
        if (sendGridSubUserDetailsObject != null) {
            throw new ProcessFailed(messageSource.getMessage("globalColors_list_not_found", new String[]{}, Locale.US));
        }
        return gridSubUserDetailsDao.save(sendGridSubUserDetails);

    }

    /**
     * {@inheritDoc}
     */
    public void update(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed {
        gridSubUserDetailsDao.update(sendGridSubUserDetails);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer sendGridSubUserDetailsId) throws ProcessFailed {
        SendGridSubUserDetails sendGridSubUserDetails = gridSubUserDetailsDao.getBySendGridSubUserDetailsId(sendGridSubUserDetailsId);
        if (sendGridSubUserDetails == null) {
            throw new ProcessFailed(messageSource.getMessage("globalColors_list_not_found", new String[]{}, Locale.US));
        }
        gridSubUserDetailsDao.delete(sendGridSubUserDetails);
    }

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsByCompanyId(Integer companyId) throws ProcessFailed {

        SendGridSubUserDetails sendGridSubUserDetails = gridSubUserDetailsDao.getSendGridSubUserDetailsByCompanyId(companyId);
        if (sendGridSubUserDetails == null) {
            throw new ProcessFailed(messageSource.getMessage("globalColors_list_not_found", new String[]{}, Locale.US));
        }
        return sendGridSubUserDetails;

    }

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsBySendGridSubUserId(String sendGridSubUserId) throws ProcessFailed {
         SendGridSubUserDetails sendGridSubUserDetails = gridSubUserDetailsDao.getSendGridSubUserDetailsBySendGridSubUserId(sendGridSubUserId);
        if (sendGridSubUserDetails == null) {
            throw new ProcessFailed(messageSource.getMessage("globalColors_list_not_found", new String[]{}, Locale.US));
        }
         return sendGridSubUserDetails;
    }
}
