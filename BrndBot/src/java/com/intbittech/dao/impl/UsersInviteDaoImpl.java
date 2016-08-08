/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UsersInviteDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.Invite;
import com.intbittech.model.Users;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
@Repository
public class UsersInviteDaoImpl implements UsersInviteDao{

    private Logger logger = Logger.getLogger(UsersInviteDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    
    @Override
    public Integer save(Invite companyInvite) throws ProcessFailed {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(companyInvite);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(Invite companyInvite) {
        try {
            sessionFactory.getCurrentSession().update(companyInvite);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }
    
    @Override
    public void delete(Invite companyInvite) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(companyInvite);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public List<Invite> getAllInvitedUsers(Users userFrom) throws ProcessFailed{
    try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Invite.class)
                    .add(Restrictions.eq("inviteSentBy", userFrom));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }        
    }
    
    @Override
    public Invite getInvitedUserById(Integer Id) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Invite.class)
                .add(Restrictions.eq("id", Id));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (Invite) criteria.list().get(0);
    }

    @Override
    public Invite getUserByInviteCode(String inviteCode) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Invite.class)
                .add(Restrictions.eq("code", inviteCode));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (Invite) criteria.list().get(0);
    }

    @Override
    public Invite getInvitedUserByUserTo(Users userTo) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Invite.class)
                .add(Restrictions.eq("inviteSentTo", userTo));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (Invite) criteria.list().get(0);
    }

}
