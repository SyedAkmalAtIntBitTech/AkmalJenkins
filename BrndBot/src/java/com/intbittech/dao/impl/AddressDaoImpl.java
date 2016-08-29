/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.AddressDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Address;
import com.intbittech.model.Category;
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
 * <code> {@link AddressDaoImpl} </code> is implementation of
 * {@link AddressDao} and perform the database related operation for managing
 * {@link Address}
 *
 * @author ilyas
 */
@Repository
public class AddressDaoImpl implements AddressDao{
    private static Logger logger = Logger.getLogger(CategoryDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public Address getByAddressId(Integer addressId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Address.class)
                    .add(Restrictions.eq("addressId", addressId));
            List<Address> address = criteria.list();
            if (address.isEmpty()) {
                return null;
            }
            return (Address) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(Address address) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(address));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(Address address) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(address);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Address address) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(address);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }
}
