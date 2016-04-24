/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;
import java.util.ArrayList;
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
 * @author ilyas
 */
@Repository
public class UsersDaoImpl implements UsersDao {

    private Logger logger = Logger.getLogger(UsersDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public Users findByUserName(String userName) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Users.class)
                    .add(Restrictions.eq("userName", userName));
            List<Users> userList = criteria.list();
            if (userList.isEmpty()) {
                return null;
            }
            return userList.get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Users getUserById(Integer userId) {
        Users user = new Users();
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Users.class)
                .add(Restrictions.eq("userId", userId));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (Users) criteria.list().get(0);
    }

    /**
     * {@inheritDoc}
     */
    public Boolean checkUniqueUser(Users user) {
        Boolean isUserUnique = true;
        List<Users> usersList = new ArrayList<>();
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Users.class)
                .add(Restrictions.eq("userName", user.getUserName()));
        usersList = criteria.list();
        if (criteria.list().isEmpty()) {
            isUserUnique = true;
        } else {
            isUserUnique = false;
        }
        return isUserUnique;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(Users user) {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(user);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }

    }

    /**
     * {@inheritDoc}
     */
    public void update(Users user) {
        try {
            sessionFactory.getCurrentSession().update(user);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public Users getUserByEmailId(String emailId) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Users.class)
                .add(Restrictions.eq("email", emailId));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (Users) criteria.list().get(0);
    }

}
