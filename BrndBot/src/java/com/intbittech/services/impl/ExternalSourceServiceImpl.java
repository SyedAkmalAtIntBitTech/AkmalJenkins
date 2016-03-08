/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ExternalSourceDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSource;
import com.intbittech.services.ExternalSourceService;
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
public class ExternalSourceServiceImpl implements ExternalSourceService {
    
    @Autowired
    private ExternalSourceDao externalSourceDao;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public ExternalSource getByExternalSourceId(Integer externalSourceId) throws ProcessFailed {
         ExternalSource externalSource = externalSourceDao.getByExternalSourceId(externalSourceId);
        if (externalSource == null) {
            throw new ProcessFailed(messageSource.getMessage("externalSource_not_found",new String[]{}, Locale.US));
        }
        return externalSource;
    }

    /**
     * {@inheritDoc}
     */
    public List<ExternalSource> getALLExternalSources() throws ProcessFailed {
         List<ExternalSource> externalSourceList = externalSourceDao.getALLExternalSources();
        if (externalSourceList == null) {
            throw new ProcessFailed(messageSource.getMessage("externalSource_not_found",new String[]{}, Locale.US));
        }
        return externalSourceList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ExternalSource externalSource) throws ProcessFailed {
        return externalSourceDao.save(externalSource);
    }

    /**
     * {@inheritDoc}
     */
    public void update(ExternalSource externalSource) throws ProcessFailed {
        externalSourceDao.update(externalSource);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer externalSourceId) throws ProcessFailed {
         ExternalSource externalSource = externalSourceDao.getByExternalSourceId(externalSourceId);
        if (externalSource == null) {
            throw new ProcessFailed(messageSource.getMessage("externalSource_not_found",new String[]{}, Locale.US));
        }
        externalSourceDao.delete(externalSource);
    }

}
