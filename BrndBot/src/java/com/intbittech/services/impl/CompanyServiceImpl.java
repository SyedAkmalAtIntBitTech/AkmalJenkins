/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.services.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class CompanyServiceImpl implements CompanyService{
    
    @Autowired
    private CompanyDao companyDao;

    /**
     * {@inheritDoc}
     */
    public List<Company> getAllCompanies() throws ProcessFailed {
        List<Company> companyList = companyDao.getAllCompanies();
        if(companyList == null)
        {
             throw new ProcessFailed("No company found.");
        }
              return  companyList;
    }
    
}
