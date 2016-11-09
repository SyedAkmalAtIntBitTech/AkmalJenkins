/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailSentHistoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailSentHistory;
import com.intbittech.utility.DateTimeUtil;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ilyas
 */
@Repository
public class EmailSentHistoryDaoImpl implements EmailSentHistoryDao{
    
    private static Logger logger = Logger.getLogger(EmailListTagDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmailSentHistory> getAllEmailSentHistoryByCompanyId(Integer companyId) throws ProcessFailed {
        try {
            
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailSentHistory.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .add(Restrictions.gt("timeSent", DateTimeUtil.daysAgo(-30)));
            List<EmailSentHistory> emailSentHistoryList = criteria.list();
            if (emailSentHistoryList.isEmpty()) {
                return null;
            }
            return emailSentHistoryList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmailSentHistory getByEmailSentHistoryId(Integer emailSentHistoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailSentHistory.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("emailSentHistoryId", emailSentHistoryId));
            List<EmailSentHistory> emailSentHistoryList = criteria.list();
            if (emailSentHistoryList.isEmpty()) {
                return null;
            }
            return (EmailSentHistory) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
}
