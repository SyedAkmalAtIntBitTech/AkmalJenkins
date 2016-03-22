/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.GlobalColorsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalColors;
import com.intbittech.services.GlobalColorsService;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class GlobalColorsServiceImpl implements GlobalColorsService {

    private static Logger logger = Logger.getLogger(GlobalColorsServiceImpl.class);
    
    @Autowired
    private GlobalColorsDao globalColorsDao;
    
    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */
    public GlobalColors getGlobalColorsById(Integer globalColorsId) throws ProcessFailed {
        GlobalColors globalColors = globalColorsDao.getGlobalColorsById(globalColorsId);
        if(globalColors == null)
            throw new ProcessFailed(messageSource.getMessage("globalColors_not_found",new String[]{}, Locale.US));
        return globalColors;
    }

    /**
     * {@inheritDoc}
     */
    public List<GlobalColors> getAllGlobalColors() throws ProcessFailed {
        List<GlobalColors> globalColorsList = globalColorsDao.getAllGlobalColors();
        if(globalColorsList == null)
            throw new ProcessFailed(messageSource.getMessage("globalColors_list_not_found",new String[]{}, Locale.US));
        return globalColorsList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(GlobalColors globalColors) throws ProcessFailed {
        return globalColorsDao.save(globalColors);
    }

    /**
     * {@inheritDoc}
     */
    public void update(GlobalColors globalColors) throws ProcessFailed {
        globalColorsDao.update(globalColors);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer globalColorsId) throws ProcessFailed {
        GlobalColors globalColors = globalColorsDao.getGlobalColorsById(globalColorsId);
        if(globalColors == null)
            throw new ProcessFailed(messageSource.getMessage("globalColors_not_found_delete",new String[]{}, Locale.US));
        globalColorsDao.delete(globalColors);
    }
    
}
