/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.SubCategoryExternalSourceDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSource;
import com.intbittech.model.SubCategoryExternalSource;
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
 * <code> {@link SubCategoryExternalSourceDaoImpl} </code> is implementation of
 * {@link SubCategoryExternalSourceDao} and perform the database related
 * operation for managing {@link SubCategoryExternalSource}
 *
 * @author Ajit
 */
@Repository
public class SubCategoryExternalSourceDaoImpl implements SubCategoryExternalSourceDao {

    private static Logger logger = Logger.getLogger(SubCategoryExternalSourceDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public SubCategoryExternalSource getBySubCategoryExternalSourceId(Integer subCategoryExternalSourceId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryExternalSource.class)
                    .setFetchMode("fkSubCategoryId", FetchMode.JOIN)
                    .setFetchMode("fkExternalSourceKeywordLookupId", FetchMode.JOIN)
                    .add(Restrictions.eq("subCategoryExternalSourceId", subCategoryExternalSourceId));
            List<SubCategoryExternalSource> subCategoryExternalSourceList = criteria.list();
            if (subCategoryExternalSourceList.isEmpty()) {
                return null;
            }
            return (SubCategoryExternalSource) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryExternalSource> getAllSubCategoryExternalSources() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryExternalSource.class)
                    .setFetchMode("fkSubCategoryId", FetchMode.JOIN)
                    .setFetchMode("fkExternalSourceKeywordLookupId", FetchMode.JOIN);
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SubCategoryExternalSource subCategoryExternalSource) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(subCategoryExternalSource));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategoryExternalSource subCategoryExternalSource) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().update(subCategoryExternalSource);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(SubCategoryExternalSource subCategoryExternalSource) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(subCategoryExternalSource);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryExternalSource> getAllSubCategoriesByCategoryID(Integer categoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryExternalSource.class)
                    .setFetchMode("fkSubCategoryId", FetchMode.JOIN)
                    .setFetchMode("fkExternalSourceKeywordLookupId", FetchMode.JOIN)
                    .createAlias("fkSubCategoryId.fkCategoryId", "aliasSubCatCat")
                    .add(Restrictions.eq("aliasSubCatCat.categoryId", categoryId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

}
