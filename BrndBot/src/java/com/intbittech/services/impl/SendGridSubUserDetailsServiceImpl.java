/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.SendGridSubUserDetailsDao;
import com.intbittech.dao.UserRoleCompanyLookUpDao;
import com.intbittech.enums.AdminStatus;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SendGridSubUserDetails;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.modelmappers.UserDetails;
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
    private UserRoleCompanyLookUpDao roleCompanyLookUpDao;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getBySendGridSubUserDetailsId(Integer sendGridSubUserDetailsId) throws ProcessFailed {
        SendGridSubUserDetails sendGridSubUserDetails = gridSubUserDetailsDao.getBySendGridSubUserDetailsId(sendGridSubUserDetailsId);
        if (sendGridSubUserDetails == null) {
            throw new ProcessFailed(messageSource.getMessage("send_grid_sub_user_details", new String[]{}, Locale.US));
        }
        return sendGridSubUserDetails;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed {
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
            throw new ProcessFailed(messageSource.getMessage("send_grid_sub_user_details", new String[]{}, Locale.US));
        }
        gridSubUserDetailsDao.delete(sendGridSubUserDetails);
    }

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsByCompanyId(Integer companyId) throws ProcessFailed {

        SendGridSubUserDetails sendGridSubUserDetails = gridSubUserDetailsDao.getSendGridSubUserDetailsByCompanyId(companyId);
        if (sendGridSubUserDetails == null) {
            throw new ProcessFailed(messageSource.getMessage("send_grid_sub_user_details", new String[]{}, Locale.US));
        }
        return sendGridSubUserDetails;

    }

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsBySendGridSubUserId(String sendGridSubUserId) throws ProcessFailed {
        SendGridSubUserDetails sendGridSubUserDetails = gridSubUserDetailsDao.getSendGridSubUserDetailsBySendGridSubUserId(sendGridSubUserId);
        if (sendGridSubUserDetails == null) {
            throw new ProcessFailed(messageSource.getMessage("send_grid_sub_user_details", new String[]{}, Locale.US));
        }
        return sendGridSubUserDetails;
    }

    /**
     * {@inheritDoc}
     */
    public UserDetails getUserDetailsOfAccountOwnerByCompanyId(Integer companyId) throws ProcessFailed {

        UsersRoleCompanyLookup roleCompanyLookup = roleCompanyLookUpDao.getUsersRoleCompanyLookupByUserRoleIdAndCompanyId(AdminStatus.ROLE_ACCOUNT_OWNER.toString(), companyId);
        if (roleCompanyLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("send_grid_sub_user_details", new String[]{}, Locale.US));
        }
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(roleCompanyLookup.getUserId().getUserId());
        userDetails.setFirstName(roleCompanyLookup.getUserId().getFirstName());
        userDetails.setLastName(roleCompanyLookup.getUserId().getLastName());
        userDetails.setUserName(roleCompanyLookup.getUserId().getUserName());
        return userDetails;
    }
}
