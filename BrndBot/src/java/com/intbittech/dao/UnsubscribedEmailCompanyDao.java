/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UnsubscribedCompanyLookup;

/**
 *
 * @author ilyas
 */
public interface UnsubscribedEmailCompanyDao {
    
    /**
     * This method save {@link UnsubscribedEmailCompany} into the database.
     *
     * @param unsubscribedCompanyLookup the unsubscribedCompanyLookup
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(UnsubscribedCompanyLookup unsubscribedCompanyLookup) throws ProcessFailed;
    
    /**
     * This method checks {@link UnsubscribedEmailCompany} the database.
     *
     * @param companyId
     * @param emailAddress
     * @return Boolean
     * @throws ProcessFailed the process failed
     */
    public Boolean isEmailUnsubscribed(Integer companyId, String emailAddress) throws ProcessFailed;
    
}
