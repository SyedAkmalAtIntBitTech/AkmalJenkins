/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ChannelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Channel;
import com.intbittech.model.Company;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link ChannelDaoImpl} </code> is implementation of
 * {@link ChannelDao} and perform the database related operation for
 * managing {@link Channel}
 *
 * @author Ajit
 */
@Repository
public class ChannelDaoImpl implements ChannelDao {
    
         private static Logger logger = Logger.getLogger(ChannelDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Channel> getAllChannels() throws ProcessFailed {
          try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Channel.class);
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }
    
}
