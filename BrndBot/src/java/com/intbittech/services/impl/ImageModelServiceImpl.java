/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ImageModelDao;
import com.intbittech.dao.SubCategoryImageModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailModel;
import com.intbittech.model.ImageModel;
import com.intbittech.model.SubCategoryImageModel;
import com.intbittech.services.ImageModelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ilyas
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class ImageModelServiceImpl implements ImageModelService {

    @Autowired
    private ImageModelDao imageModelDao;
    
    @Autowired
    private SubCategoryImageModelDao subCategoryImageModelDao;
    
    /**
     * {@inheritDoc}
     */
    public ImageModel getByImageModelId(Integer imageModelId) throws ProcessFailed {
        ImageModel imageModel = imageModelDao.getByImageModelId(imageModelId);
        if (imageModel == null) {
            throw new ProcessFailed("No image template found.");
        }
        return imageModel;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ImageModel imageModel) throws ProcessFailed {
        return imageModelDao.save(imageModel);
    }

    /**
     * {@inheritDoc}
     */
    public void update(ImageModel imageModel) throws ProcessFailed {
        imageModelDao.update(imageModel);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer imageModelId) throws ProcessFailed {
        ImageModel imageModel = imageModelDao.getByImageModelId(imageModelId);
        if (imageModel == null)
            throw new ProcessFailed("No image template found.");
        imageModelDao.delete(imageModel);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<ImageModel> getAllImageModel() throws ProcessFailed {
        List<ImageModel> imageModel = imageModelDao.getAllImageModel();
        if (imageModel == null) {
            throw new ProcessFailed("No image template found.");
        }
        return imageModel;
    }

    /**
     * {@inheritDoc}
     */
    public List<ImageModel> getAllNonAddedImageModels(Integer subCategoryId) throws ProcessFailed {
        List<SubCategoryImageModel> subCategoryImageModelList = subCategoryImageModelDao.getAllSubCategoryImageModel(subCategoryId);
        Integer[] imageModelIds = new Integer[subCategoryImageModelList.size()];
        Integer i =0;
        for(SubCategoryImageModel subCategoryImageModelObject : subCategoryImageModelList)
        {
            imageModelIds[i++] = subCategoryImageModelObject.getFkImageModelId().getImageModelId();
        }
        List<ImageModel> imageModelList = imageModelDao.getByImageModelsByIds(imageModelIds);
        return imageModelList;
    }
    
}
