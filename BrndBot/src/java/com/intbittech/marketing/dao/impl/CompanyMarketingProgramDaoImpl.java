/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.marketing.dao.impl;

import com.intbittech.dao.impl.CategoryDaoImpl;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.marketing.dao.CompanyMarketingProgramDao;
import com.intbittech.model.CompanyMarketingProgram;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link CompanyMarketingProgramDaoImpl} </code> is implementation of
 * {@link CompanyMarketingProgramDao} and perform the database related operation for managing
 * {@link CompanyMarketingProgram}
 *
 * @author Ajit
 */
@Repository
public class CompanyMarketingProgramDaoImpl implements CompanyMarketingProgramDao{
    
    private static Logger logger = Logger.getLogger(CompanyMarketingProgramDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

     /**
     * {@inheritDoc}
     */
    public Integer save(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(companyMarketingProgram));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }
    
}
