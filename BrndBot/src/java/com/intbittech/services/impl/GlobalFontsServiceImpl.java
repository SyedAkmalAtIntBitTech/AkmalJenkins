/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.GlobalFontsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalFonts;
import com.intbittech.services.GlobalFontsService;
import com.intbittech.utility.FileHandlerUtil;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class GlobalFontsServiceImpl implements GlobalFontsService {

    private static Logger logger = Logger.getLogger(GlobalFontsServiceImpl.class);
    
    @Autowired
    private GlobalFontsDao globalFontsDao;
    
    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */
    public GlobalFonts getGlobalFontsById(Integer globalFontsId) throws ProcessFailed {
        GlobalFonts globalFonts = globalFontsDao.getGlobalFontsById(globalFontsId);
        if(globalFonts == null)
            throw new ProcessFailed(messageSource.getMessage("globalFonts_not_found",new String[]{}, Locale.US));
        return globalFonts;
    }

    /**
     * {@inheritDoc}
     */
    public List<GlobalFonts> getAllGlobalFonts() throws ProcessFailed {
        List<GlobalFonts> globalFontsList = globalFontsDao.getAllGlobalFonts();
        if(globalFontsList == null)
            throw new ProcessFailed(messageSource.getMessage("globalFonts_list_not_found",new String[]{}, Locale.US));
        return globalFontsList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(GlobalFonts globalFonts) throws ProcessFailed {
        return globalFontsDao.save(globalFonts);
    }

    /**
     * {@inheritDoc}
     */
    public void update(GlobalFonts globalFonts) throws ProcessFailed {
        globalFontsDao.update(globalFonts);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer globalFontsId) throws ProcessFailed {
        GlobalFonts globalFonts = globalFontsDao.getGlobalFontsById(globalFontsId);
        if(globalFonts == null)
            throw new ProcessFailed(messageSource.getMessage("globalFonts_not_found_delete",new String[]{}, Locale.US));
        try {
            globalFontsDao.delete(globalFonts);
            FileHandlerUtil.deleteAdminGlobalFont(globalFonts.getFileName());
        } catch (Throwable ex) {
            throw new ProcessFailed(ex.getMessage());
        }
    }
    
}
