/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Haider Khan @ Intbit
 */

@Repository
public class UsersDaoImpl implements UsersDao {

    private static Logger logger = Logger.getLogger(UsersDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 
     * @param userName
     * @return
     * @throws ProcessFailed 
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
            return  userList.get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }
    
    
}
