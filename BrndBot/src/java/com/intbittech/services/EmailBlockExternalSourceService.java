/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.model.EmailBlockExternalSource;

/**
 *
 * @author AR
 */
public interface EmailBlockExternalSourceService {

    public EmailBlockExternalSource getByEmailBlockId(Integer emailBlockId);
    
}
