/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.MarketingCategoryProgramDao;
import com.intbittech.dao.MarketingProgramDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingCategoryProgram;
import com.intbittech.model.MarketingProgram;
import com.intbittech.services.MarketingCategoryProgramService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class MarketingCategoryProgramServiceImpl implements MarketingCategoryProgramService {

    @Autowired
    private MarketingCategoryProgramDao marketingCategoryProgramDao;
    
    @Autowired
    private MarketingProgramDao marketingProgramDao;
    
    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */
    public List<MarketingCategoryProgram> getAllMarketingCategoryPrograms() throws ProcessFailed {
        List<MarketingCategoryProgram> marketingCategoryProgramList = marketingCategoryProgramDao.getAllMarketingCategoryPrograms();
        if(marketingCategoryProgramList == null)
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_list_not_found",new String[]{}, Locale.US));
        return marketingCategoryProgramList;
    }

    /**
     * {@inheritDoc}
     */
    public MarketingCategoryProgram getByMarketingCategoryProgramId(Integer marketingCategoryProgramId) throws ProcessFailed {
        MarketingCategoryProgram marketingCategoryProgram = marketingCategoryProgramDao.getByMarketingCategoryProgramId(marketingCategoryProgramId);
        if(marketingCategoryProgram == null)
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_not_found",new String[]{}, Locale.US));
        return marketingCategoryProgram;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(MarketingCategoryProgram marketingCategoryProgram) throws ProcessFailed {
        return marketingCategoryProgramDao.save(marketingCategoryProgram);
    }

    /**
     * {@inheritDoc}
     */
    public void update(MarketingCategoryProgram marketingCategoryProgram) throws ProcessFailed {
        marketingCategoryProgramDao.update(marketingCategoryProgram);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer marketingCategoryProgramId) throws ProcessFailed {
        MarketingCategoryProgram marketingCategoryProgram = marketingCategoryProgramDao.getByMarketingCategoryProgramId(marketingCategoryProgramId);
        if(marketingCategoryProgram == null)
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_not_found_delete",new String[]{}, Locale.US));
        marketingCategoryProgramDao.delete(marketingCategoryProgram);
    }

    /**
     * {@inheritDoc}
     */
    public List<MarketingCategoryProgram> getMarketingProgramsByCategoryId(Integer marketingCategoryId) throws ProcessFailed {
        List<MarketingCategoryProgram> marketingCategoryProgramList = marketingCategoryProgramDao.getMarketingProgramsByCategoryId(marketingCategoryId);
        if(marketingCategoryProgramList == null)
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_list_not_found",new String[]{}, Locale.US));
        return marketingCategoryProgramList;
    }

    /**
     * {@inheritDoc}
     */
    public List<MarketingProgram> getAllNonAddedMarketingPrograms(Integer marketingCategoryId) throws ProcessFailed {
        List<MarketingCategoryProgram> marketingCategoryProgramList = marketingCategoryProgramDao.getMarketingProgramsByCategoryId(marketingCategoryId);
        ArrayList<Integer> marketingProgramIds = new ArrayList<>();
        marketingProgramIds.add(0);
        if (marketingCategoryProgramList != null)
        {
            for(MarketingCategoryProgram marketingCategoryProgram : marketingCategoryProgramList)
                marketingProgramIds.add(marketingCategoryProgram.getFkMarketingProgram().getMarketingProgramId());
        }
        List<MarketingProgram> marketingProgramList = marketingProgramDao.getMarketingProgramsByIds(marketingProgramIds);
        if(marketingProgramList == null)
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_list_not_found",new String[]{}, Locale.US));
        return marketingProgramList;
    }
    
}
