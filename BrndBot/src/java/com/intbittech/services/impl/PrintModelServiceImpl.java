/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.PrintModelDao;
import com.intbittech.dao.SubCategoryPrintModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PrintModel;
import com.intbittech.model.SubCategoryPrintModel;
import com.intbittech.services.PrintModelService;
import java.util.ArrayList;
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
public class PrintModelServiceImpl implements PrintModelService {

    @Autowired
    private PrintModelDao printModelDao;
    
    @Autowired
    private SubCategoryPrintModelDao subCategoryPrintModelDao;
    
    /**
     * {@inheritDoc}
     */
    public PrintModel getByPrintModelId(Integer printModelId) throws ProcessFailed {
        PrintModel printModel = printModelDao.getByPrintModelId(printModelId);
        if (printModel == null) {
            throw new ProcessFailed("No print template found.");
        }
        return printModel;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(PrintModel printModel) throws ProcessFailed {
        return printModelDao.save(printModel);
    }

    /**
     * {@inheritDoc}
     */
    public void update(PrintModel printModel) throws ProcessFailed {
        printModelDao.update(printModel);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer printModelId) throws ProcessFailed {
        PrintModel printModel = printModelDao.getByPrintModelId(printModelId);
        if (printModel == null)
            throw new ProcessFailed("No print template found.");
        printModelDao.delete(printModel);
    }

    /**
     * {@inheritDoc}
     * @return 
     */
    public List<PrintModel> getAllPrintModel() throws ProcessFailed {
       List<PrintModel> printModel = printModelDao.getByAllPrintModel();
        if (printModel == null) {
            throw new ProcessFailed("No print template found.");
        }
        return printModel;
    }

    /**
     * {@inheritDoc}
     */
    public List<PrintModel> getAllNonAddedPrintModels(Integer subCategoryId) throws ProcessFailed {
        List<SubCategoryPrintModel> subCategoryPrintModelList = subCategoryPrintModelDao.getAllSubCategoryPrintModel(subCategoryId);
        ArrayList<Integer> printModelIds = new ArrayList<>();
        printModelIds.add(0);
        if(subCategoryPrintModelList != null)
        {
            for(SubCategoryPrintModel subCategoryPrintModelObject : subCategoryPrintModelList)
            {
                printModelIds.add(subCategoryPrintModelObject.getFkPrintModelId().getPrintModelId());
            }
        }
        List<PrintModel> emailModelList = printModelDao.getByPrintModelsByIds(printModelIds);
        if (emailModelList == null) {
            throw new ProcessFailed("No print templates found.");
        }
        return emailModelList;
    }

    
}
