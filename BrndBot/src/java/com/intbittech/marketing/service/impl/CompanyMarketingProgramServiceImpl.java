/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.marketing.service.impl;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.marketing.dao.CompanyMarketingProgramDao;
import com.intbittech.marketing.service.CompanyMarketingProgramService;
import com.intbittech.model.CompanyMarketingProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class CompanyMarketingProgramServiceImpl implements  CompanyMarketingProgramService{
    
    @Autowired
    private CompanyMarketingProgramDao companyMarketingProgramDao;

    /**
     * {@inheritDoc}
     */
    public Integer save(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed {
       return companyMarketingProgramDao.save(companyMarketingProgram);
    }
    
}
