/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.SubCategoryExternalSourceDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryExternalSource;
import com.intbittech.services.SubCategoryExternalSourceService;
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
public class SubCategoryExternalSourceServiceImpl implements SubCategoryExternalSourceService {

    @Autowired
    private SubCategoryExternalSourceDao subCategoryExternalSourceDao;
     @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public SubCategoryExternalSource getBySubCategoryExternalSourceId(Integer subCategoryExternalSourceId) throws ProcessFailed {
        SubCategoryExternalSource subCategoryExternalSource = subCategoryExternalSourceDao.getBySubCategoryExternalSourceId(subCategoryExternalSourceId);
        if (subCategoryExternalSource == null) {
            throw new ProcessFailed(messageSource.getMessage("subCategoryExternalSource_not_found", new String[]{}, Locale.US));
        }
        return subCategoryExternalSource;
    }

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryExternalSource> getALLSubCategoryExternalSources() throws ProcessFailed {
         List<SubCategoryExternalSource> subCategoryExternalSourceList = subCategoryExternalSourceDao.getALLSubCategoryExternalSources();
        if (subCategoryExternalSourceList == null) {
            throw new ProcessFailed(messageSource.getMessage("subCategoryExternalSource_not_found", new String[]{}, Locale.US));
        }
        return subCategoryExternalSourceList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SubCategoryExternalSource subCategoryExternalSource) throws ProcessFailed {
        return  subCategoryExternalSourceDao.save(subCategoryExternalSource);
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategoryExternalSource subCategoryExternalSource) throws ProcessFailed {
        subCategoryExternalSourceDao.update(subCategoryExternalSource);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer subCategoryExternalSourceId) throws ProcessFailed {
         SubCategoryExternalSource subCategoryExternalSource = subCategoryExternalSourceDao.getBySubCategoryExternalSourceId(subCategoryExternalSourceId);
        if (subCategoryExternalSource == null) {
            throw new ProcessFailed(messageSource.getMessage("subCategoryExternalSource_not_found", new String[]{}, Locale.US));
        }
       subCategoryExternalSourceDao.delete(subCategoryExternalSource);
    }

}
