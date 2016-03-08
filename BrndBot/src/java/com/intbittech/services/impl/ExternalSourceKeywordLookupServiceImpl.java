/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ExternalSourceKeywordLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSourceKeywordLookup;
import com.intbittech.services.ExternalSourceKeywordLookupService;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class ExternalSourceKeywordLookupServiceImpl implements ExternalSourceKeywordLookupService {

    @Autowired
    private ExternalSourceKeywordLookupDao externalSourceKeywordLookupDao;
     @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public ExternalSourceKeywordLookup getByExternalSourceKeywordLookupId(Integer externalSourceKeywordLookupId) throws ProcessFailed {
         ExternalSourceKeywordLookup externalSourceKeywordLookup = externalSourceKeywordLookupDao.getByExternalSourceKeywordLookupId(externalSourceKeywordLookupId);
        if (externalSourceKeywordLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("externalSourceKeywordLookup_not_found", new String[]{}, Locale.US));
        }
        return externalSourceKeywordLookup;
    }

    /**
     * {@inheritDoc}
     */
    public List<ExternalSourceKeywordLookup> getALLExternalSourceKeywordLookups() throws ProcessFailed {
        List<ExternalSourceKeywordLookup> externalSourceKeywordLookupList = externalSourceKeywordLookupDao.getALLExternalSourceKeywordLookups();
        if (externalSourceKeywordLookupList == null) {
            throw new ProcessFailed(messageSource.getMessage("externalSourceKeywordLookup_not_found", new String[]{}, Locale.US));
        }
        return externalSourceKeywordLookupList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ExternalSourceKeywordLookup externalSourceKeywordLookup) throws ProcessFailed {
        return  externalSourceKeywordLookupDao.save(externalSourceKeywordLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void update(ExternalSourceKeywordLookup externalSourceKeywordLookup) throws ProcessFailed {
        externalSourceKeywordLookupDao.update(externalSourceKeywordLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer externalSourceKeywordLookupId) throws ProcessFailed {
         ExternalSourceKeywordLookup externalSourceKeywordLookup = externalSourceKeywordLookupDao.getByExternalSourceKeywordLookupId(externalSourceKeywordLookupId);
        if (externalSourceKeywordLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("externalSourceKeywordLookup_not_found", new String[]{}, Locale.US));
        }
       externalSourceKeywordLookupDao.delete(externalSourceKeywordLookup);
    }

}
