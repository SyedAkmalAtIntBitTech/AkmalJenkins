/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.SubCategoryPrintModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryPrintModel;
import com.intbittech.services.SubCategoryPrintModelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class SubCategoryPrintModelServiceImpl implements SubCategoryPrintModelService {

    @Autowired
    private SubCategoryPrintModelDao subCategoryPrintModelDao;

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryPrintModel> getAllSubCategoryPrintModel(Integer subCategoryId) throws ProcessFailed {
        List<SubCategoryPrintModel> subCategoryPrintModelList = subCategoryPrintModelDao.getAllSubCategoryPrintModel(subCategoryId);
        if (subCategoryPrintModelList == null) {
            throw new ProcessFailed("No print templates exist.");
        }
        return subCategoryPrintModelList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed {
        return subCategoryPrintModelDao.save(subCategoryPrintModel);
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed {
        subCategoryPrintModelDao.update(subCategoryPrintModel);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer subCategoryPrintModelId) throws ProcessFailed {
        SubCategoryPrintModel subCategoryPrintModel = subCategoryPrintModelDao.getSubCategoryPrintModelById(subCategoryPrintModelId);
        if (subCategoryPrintModel == null) {
            throw new ProcessFailed("Print template could not be deleted.");
        }
        subCategoryPrintModelDao.delete(subCategoryPrintModel);
    }

}
