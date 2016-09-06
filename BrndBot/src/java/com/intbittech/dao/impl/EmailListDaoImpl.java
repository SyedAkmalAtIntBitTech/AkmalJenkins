/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailListDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailList;
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
 * <code> {@link EmailListDaoImpl} </code> is implementation of
 * {@link EmailListDao} and perform the database related operation for managing
 * {@link EmailList}
 *
 * @author Ajit
 */
@Repository
public class EmailListDaoImpl implements EmailListDao {

    private static Logger logger = Logger.getLogger(EmailListDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailList getByEmailListId(Integer emailListId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailList.class)
                    .setFetchMode("fkTypeId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("emailListId", emailListId));
            List<EmailList> emailList = criteria.list();
            if (emailList.isEmpty()) {
                return null;
            }
            return (EmailList) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailList> getEmailListByCompanyId(Integer companyId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailList.class)
                    .setFetchMode("fkTypeId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId));
            List<EmailList> emailLists = criteria.list();
            if (emailLists.isEmpty()) {
                return null;
            }
            return emailLists;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailList> getEmailListByCompanyIdAndType(Integer companyId, Integer typeId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailList.class)
                    .setFetchMode("fkTypeId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .add(Restrictions.eq("fkTypeId.fkTypeId", typeId));
            List<EmailList> emailLists = criteria.list();
            if (emailLists.isEmpty()) {
                return null;
            }
            return emailLists;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

   /**
     * {@inheritDoc}
     */
    public Integer save(EmailList emailList) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailList));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

   /**
     * {@inheritDoc}
     */
    public void update(EmailList emailList) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().update(emailList);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(EmailList emailList) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().delete(emailList);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
}
