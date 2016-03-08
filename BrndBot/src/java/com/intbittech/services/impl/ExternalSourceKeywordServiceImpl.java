/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ExternalSourceKeywordDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSourceKeyword;
import com.intbittech.services.ExternalSourceKeywordService;
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
public class ExternalSourceKeywordServiceImpl implements ExternalSourceKeywordService {

    @Autowired
    private ExternalSourceKeywordDao externalSourceKeywordDao;

    /**
     * {@inheritDoc}
     */
    public ExternalSourceKeyword getByExternalSourceKeywordId(Integer externalSourceKeywordId) throws ProcessFailed {
        ExternalSourceKeyword externalSourceKeyword = externalSourceKeywordDao.getByExternalSourceKeywordId(externalSourceKeywordId);
        if (externalSourceKeyword == null) {
            throw new ProcessFailed("No external source keyword with id" + externalSourceKeywordId + ".");
        }
        return externalSourceKeyword;
    }

    /**
     * {@inheritDoc}
     */
    public List<ExternalSourceKeyword> getALLExternalSourceKeywords() throws ProcessFailed {
        List<ExternalSourceKeyword> externalSourceKeywordList = externalSourceKeywordDao.getALLExternalSourceKeywords();
        if (externalSourceKeywordList == null) {
            throw new ProcessFailed("No external source keyword found.");
        }
        return externalSourceKeywordList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ExternalSourceKeyword externalSourceKeyword) throws ProcessFailed {
        return externalSourceKeywordDao.save(externalSourceKeyword);
    }

    /**
     * {@inheritDoc}
     */
    public void update(ExternalSourceKeyword externalSourceKeyword) throws ProcessFailed {
        externalSourceKeywordDao.update(externalSourceKeyword);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer externalSourceKeywordId) throws ProcessFailed {
        ExternalSourceKeyword externalSourceKeyword = externalSourceKeywordDao.getByExternalSourceKeywordId(externalSourceKeywordId);
        if (externalSourceKeyword == null) {
            throw new ProcessFailed("No external source keyword with id" + externalSourceKeywordId + ".");
        }
        externalSourceKeywordDao.delete(externalSourceKeyword);
    }

}
