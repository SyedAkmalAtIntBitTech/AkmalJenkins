/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.marketing.service;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyMarketingProgram;

/**
 *
 * @author ajit
 */
public interface CompanyMarketingProgramService {
  
      /**
     * This method save {@link companyMarketingProgram} into the database.
     *
     * @param companyMarketingProgram the companyMarketingProgram
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed;
    
}
