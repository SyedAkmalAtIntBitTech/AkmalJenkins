/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockExternalSource;
import java.util.List;

/**
 *
 * @author AR
 */
public interface EmailBlockExternalSourceService {

    public EmailBlockExternalSource getByEmailBlockId(Integer emailBlockId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailBlockExternalSource}
     * from database.
     *
     * @param emailBlockId is the emailBlockId
     * @param externalSourceId is the externalSourceId
     * @return list of {@link EmailBlockExternalSource}
     * @throws ProcessFailed the process failed
     */
    public List<EmailBlockExternalSource> getAllEmailBlockExternalSourceByEmailBlockIdAndExternalSourceId(Integer emailBlockId,Integer externalSourceId) throws ProcessFailed;
}
