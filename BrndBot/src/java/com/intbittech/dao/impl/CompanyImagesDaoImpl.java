/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.CompanyImagesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyImages;
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
 * <code> {@link CompanyDaoImpl} </code> is implementation of {@link CompanyDao}
 * and perform the database related operation for managing {@link Company}
 *
 * @author Ajit
 */
@Repository
public class CompanyImagesDaoImpl implements CompanyImagesDao {

    private final static Logger logger = Logger.getLogger(CompanyImagesDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<CompanyImages> getAllCompanyImages(Integer companyId) {
        try {
            CompanyImages company = new CompanyImages(companyId);
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyImages.class).add(Restrictions.eq("fkCompanyId", company));

            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public Integer save(CompanyImages companyImage) {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(companyImage);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public void delete(CompanyImages companyImages) {
        try {
            sessionFactory.getCurrentSession().delete(companyImages);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public CompanyImages getById(Integer companyImagesId) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyImages.class).add(Restrictions.eq("company_images_id", companyImagesId));

            if (criteria.list().isEmpty()) {
                return null;
            }
            return (CompanyImages) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

}
