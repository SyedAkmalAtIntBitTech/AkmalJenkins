/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.GlobalImagesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalImages;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intbittech.services.GlobalImagesService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class GloblaImagesServiceImpl implements GlobalImagesService {

    private static org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(GloblaImagesServiceImpl.class);

    @Autowired
    private GlobalImagesDao globalImagesDao;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public GlobalImages getGlobalImagesById(Integer globalImagesId) throws ProcessFailed {
        GlobalImages globalImages = globalImagesDao.getGlobalImagesById(globalImagesId);
        if (globalImages == null) {
            throw new ProcessFailed(messageSource.getMessage("globalColors_not_found", new String[]{}, Locale.US));
        }
        return globalImages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GlobalImages> getAllGlobalImages() throws ProcessFailed {
        List<GlobalImages> globalImagesList = globalImagesDao.getAllGlobalImages();
        if (globalImagesList == null) {
            throw new ProcessFailed(messageSource.getMessage("globalColors_list_not_found", new String[]{}, Locale.US));
        }
        return globalImagesList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer save(GlobalImages globalImages) throws ProcessFailed {
        return globalImagesDao.save(globalImages);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(GlobalImages globalImages) throws ProcessFailed {
         globalImagesDao.update(globalImages);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer globalImagesId) throws ProcessFailed {
        GlobalImages globalImages = globalImagesDao.getGlobalImagesById(globalImagesId);
        if(globalImages == null)
            throw new ProcessFailed(messageSource.getMessage("globalColors_not_found_delete",null, Locale.US));
        globalImagesDao.delete(globalImages);
    }

}
