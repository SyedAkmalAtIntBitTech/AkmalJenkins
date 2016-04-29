/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.AppConstants;
import com.intbittech.dao.GlobalImagesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalImages;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intbittech.services.GlobalImagesService;
import java.io.File;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class GlobalImagesServiceImpl implements GlobalImagesService {

    private static org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(GlobalImagesServiceImpl.class);

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

    @Override
    public Boolean checkForUniqueness(String globalImageName) throws ProcessFailed {
        return globalImagesDao.checkForUniqueness(globalImageName);
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
    
    @Override
    public String getLink(String fileName, String imageURL) {
        return ""+imageURL+"downloadImage?imageType=GLOBAL_IMAGE&companyId=0&imageName=" + fileName;   
    }
    @Override
    public String getPath() {
        return AppConstants.BASE_ADMIN_GLOBAL_IMAGE_UPLOAD_PATH + File.separator;
    }

}
