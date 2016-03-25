/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.MarketingProgramDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingProgram;
import com.intbittech.services.MarketingProgramService;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class MarketingProgramServiceImpl implements MarketingProgramService {
    
    @Autowired
    private MarketingProgramDao marketingProgramDao;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<MarketingProgram> getAllMarketingPrograms() throws ProcessFailed {
        List<MarketingProgram> marketingProgramList = marketingProgramDao.getAllMarketingPrograms();
        if(marketingProgramList == null)
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_list_not_found",new String[]{}, Locale.US));
        return marketingProgramList;
    }

    /**
     * {@inheritDoc}
     */
    public MarketingProgram getByMarketingProgramId(Integer marketingProgramId) throws ProcessFailed {
        MarketingProgram marketingProgram = marketingProgramDao.getByMarketingProgramId(marketingProgramId);
        if(marketingProgram == null)
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_not_found",new String[]{}, Locale.US));
        return marketingProgram;
            
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(MarketingProgram marketingProgram) throws ProcessFailed {
        return marketingProgramDao.save(marketingProgram);
    }

    /**
     * {@inheritDoc}
     */
    public void update(MarketingProgram marketingProgram) throws ProcessFailed {
        marketingProgramDao.update(marketingProgram);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer marketingProgramId) throws ProcessFailed {
        MarketingProgram marketingProgram = marketingProgramDao.getByMarketingProgramId(marketingProgramId);
        if(marketingProgram == null)
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_not_found_delete",new String[]{}, Locale.US));
        marketingProgramDao.delete(marketingProgram);
    }
    
}
