/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.OrganizationCompanyLookup;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link CompanyDaoImpl} </code> is implementation of
 * {@link CompanyDao} and perform the database related operation for
 * managing {@link Company}
 *
 * @author Ajit
 */
@Repository
public class CompanyDaoImpl implements CompanyDao{
    
      private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public List<Company> getAllCompanies() throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationCompanyLookup.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkOrganizationTypeId.organizationTypeId", 1)); //Group is 1
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
