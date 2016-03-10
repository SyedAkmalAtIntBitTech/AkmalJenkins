/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.SubCategoryImageModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryImageModel;
import com.intbittech.services.SubCategoryImageModelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class SubCategoryImageModelServiceImpl implements SubCategoryImageModelService {

    @Autowired
    private SubCategoryImageModelDao subCategoryImageModelDao;

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryImageModel> getAllSubCategoryImageModel(Integer subCategoryId) throws ProcessFailed {
        List<SubCategoryImageModel> subCategoryImageModelList = subCategoryImageModelDao.getAllSubCategoryImageModel(subCategoryId);
        if (subCategoryImageModelList == null) {
            throw new ProcessFailed("No image templates exist.");
        }
        return subCategoryImageModelList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed {
        return subCategoryImageModelDao.save(subCategoryImageModel);
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed {
        subCategoryImageModelDao.update(subCategoryImageModel);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer subCategoryImageModelId) throws ProcessFailed {
        SubCategoryImageModel subCategoryImageModel = subCategoryImageModelDao.getSubCategoryImageModelById(subCategoryImageModelId);
        if (subCategoryImageModel == null) {
            throw new ProcessFailed("Image template could not be deleted.");
        }
        subCategoryImageModelDao.delete(subCategoryImageModel);
    }

}
