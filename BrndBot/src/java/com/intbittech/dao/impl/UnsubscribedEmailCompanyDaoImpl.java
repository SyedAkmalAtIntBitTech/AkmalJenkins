/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UnsubscribedEmailCompanyDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UnsubscribedCompanyLookup;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 *
 * @author ilyas
 */
public class UnsubscribedEmailCompanyDaoImpl implements UnsubscribedEmailCompanyDao{
    
    private static Logger logger = Logger.getLogger(EmailBlockDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    @Override
    public Integer save(UnsubscribedCompanyLookup unsubscribedCompanyLookup) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(unsubscribedCompanyLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    @Override
    public Boolean isEmailUnsubscribed(Integer companyId, String emailAddress) throws ProcessFailed {
        try {
            Boolean isUnsubscribed = true;
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UnsubscribedCompanyLookup.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkUnsubscribedEmailId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .add(Restrictions.eq("fkUnsubscribedEmailId.emailAddress",emailAddress));
            List<UnsubscribedCompanyLookup> unsubscribedCompanyLookup = criteria.list();
            if(unsubscribedCompanyLookup.isEmpty())
                isUnsubscribed = false;
            return isUnsubscribed;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
        
    }
    
}
