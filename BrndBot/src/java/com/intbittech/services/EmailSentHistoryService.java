/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailSentHistory;
import java.util.List;

/**
 *
 * @author ilyas
 */
public interface EmailSentHistoryService {
    
    /**
     * This method pass id as input and get the {@link EmailSentHistory} from DAO layer.
     *
     * @return {@link EmailSentHistory}
     * @throws ProcessFailed the process failed
     */
    public List<EmailSentHistory> getAllEmailSentHistoryByCompanyId(Integer companyId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link EmailSentHistory} from DAO layer.
     *
     * @return {@link EmailSentHistory}
     * @throws ProcessFailed the process failed
     */
    public EmailSentHistory getByEmailSentHistoryId(Integer emailSentHistoryId) throws ProcessFailed;
    
}
