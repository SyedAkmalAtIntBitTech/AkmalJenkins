/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.SubCategoryEmailModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryEmailModel;
import com.intbittech.services.SubCategoryEmailModelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class SubCategoryEmailModelServiceImpl implements SubCategoryEmailModelService {

    @Autowired
    private SubCategoryEmailModelDao subCategoryEmailModelDao;

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryEmailModel> getAllSubCategoryEmailModel(Integer subCategoryId) throws ProcessFailed {
        List<SubCategoryEmailModel> subCategoryEmailModelList = subCategoryEmailModelDao.getAllSubCategoryEmailModel(subCategoryId);
        if (subCategoryEmailModelList == null) {
            throw new ProcessFailed("No email templates exist.");
        }
        return subCategoryEmailModelList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed {
        return subCategoryEmailModelDao.save(subCategoryEmailModel);
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed {
        subCategoryEmailModelDao.update(subCategoryEmailModel);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer subCategoryEmailModelId) throws ProcessFailed {
        SubCategoryEmailModel subCategoryEmailModel = subCategoryEmailModelDao.getSubCategoryEmailModelById(subCategoryEmailModelId);
        if (subCategoryEmailModel == null) {
            throw new ProcessFailed("Email template could not be deleted.");
        }
        subCategoryEmailModelDao.delete(subCategoryEmailModel);
    }

    @Override
    public SubCategoryEmailModel getBySubCategoryEmailModelId(Integer emailModelId) {
        return subCategoryEmailModelDao.getBySubCategoryEmailModelId(emailModelId);      
    }

}
