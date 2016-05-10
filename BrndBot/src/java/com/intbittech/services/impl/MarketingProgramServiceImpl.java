/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intbittech.dao.MarketingActionDao;
import com.intbittech.dao.MarketingProgramDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingAction;
import com.intbittech.model.MarketingProgram;
import com.intbittech.modelmappers.MarketingActionsObjectDetails;
import com.intbittech.modelmappers.MarketingProgramActionsDetails;
import com.intbittech.services.MarketingProgramService;
import com.intbittech.utility.StringUtility;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class MarketingProgramServiceImpl implements MarketingProgramService {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SubCategoryServiceImpl.class);

    @Autowired
    private MarketingProgramDao marketingProgramDao;

    @Autowired
    private MarketingActionDao marketingActionDao;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<MarketingProgram> getAllMarketingPrograms() throws ProcessFailed {
        List<MarketingProgram> marketingProgramList = marketingProgramDao.getAllMarketingPrograms();
        if (marketingProgramList == null) {
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_list_not_found", new String[]{}, Locale.US));
        }
        return marketingProgramList;
    }

    /**
     * {@inheritDoc}
     */
    public MarketingProgram getByMarketingProgramId(Integer marketingProgramId) throws ProcessFailed {
        MarketingProgram marketingProgram = marketingProgramDao.getByMarketingProgramId(marketingProgramId);
        if (marketingProgram == null) {
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_not_found", new String[]{}, Locale.US));
        }
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
        if (marketingProgram == null) {
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_not_found_delete", new String[]{}, Locale.US));
        }
        marketingProgramDao.delete(marketingProgram);
    }

    /**
     * {@inheritDoc}
     */
    public void saveMarketingProgramActions(MarketingProgramActionsDetails marketingProgramActionsDetails) throws ProcessFailed {
        try {
            MarketingProgram marketingProgram = new MarketingProgram();
            marketingProgram.setMarketingProgramName(marketingProgramActionsDetails.getMarketingProgramName());
            marketingProgram.setHtmlData(marketingProgramActionsDetails.getHtmlData());
            Integer marketingProgramId = marketingProgramDao.save(marketingProgram);

            MarketingAction marketingAction = new MarketingAction();
            MarketingProgram marketingProgramObject = new MarketingProgram();
            marketingProgramObject.setMarketingProgramId(marketingProgramId);
            marketingAction.setFkMarketingProgramId(marketingProgramObject);
//            List<MarketingActionsObjectDetails> marketingActionsObjectDetailses = new ArrayList<>();
            MarketingActionsObjectDetails marketingActionsObjectDetails = new MarketingActionsObjectDetails();
            marketingActionsObjectDetails.setActions(marketingProgramActionsDetails.getMarketingActions());
//            marketingActionsObjectDetailses.add(marketingActionsObjectDetails);
            String jsonString = StringUtility.objectListToJsonString(marketingActionsObjectDetails);
            
            marketingAction.setJsonTemplate(jsonString);
            marketingActionDao.save(marketingAction);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_save_error", new String[]{}, Locale.US));
        }

    }
    
    /**
     * {@inheritDoc}
     */
    public void updateMarketingProgramActions(MarketingProgramActionsDetails marketingProgramActionsDetails) throws ProcessFailed {
        try {
            MarketingProgram marketingProgram = new MarketingProgram();
            marketingProgram.setMarketingProgramId(marketingProgramActionsDetails.getMarketingProgramId());
            marketingProgram.setMarketingProgramName(marketingProgramActionsDetails.getMarketingProgramName());
            marketingProgram.setHtmlData(marketingProgramActionsDetails.getHtmlData());
            marketingProgramDao.update(marketingProgram);

            MarketingAction marketingAction = new MarketingAction();
            marketingAction.setMarketingActionId(marketingProgramActionsDetails.getMarketingActionId());
            MarketingProgram marketingProgramObject = new MarketingProgram();
            marketingProgramObject.setMarketingProgramId(marketingProgramActionsDetails.getMarketingProgramId());
            marketingAction.setFkMarketingProgramId(marketingProgramObject);
            
//            List<MarketingActionsObjectDetails> marketingActionsObjectDetailses = new ArrayList<>();
            MarketingActionsObjectDetails marketingActionsObjectDetails = new MarketingActionsObjectDetails();
            marketingActionsObjectDetails.setActions(marketingProgramActionsDetails.getMarketingActions());
//            marketingActionsObjectDetailses.add(marketingActionsObjectDetails);
            
            String jsonString = StringUtility.objectListToJsonString(marketingActionsObjectDetails);
            marketingAction.setJsonTemplate(jsonString);
            marketingActionDao.update(marketingAction);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("marketingProgram_save_error", new String[]{}, Locale.US));
        }

    }

}
