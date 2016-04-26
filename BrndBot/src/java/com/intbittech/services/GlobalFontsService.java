/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalFonts;
import java.util.List;

/**
 * <code>{@link GlobalFontsService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface GlobalFontsService {
    
    /**
     * This method pass id as input and get the {@link GlobalFonts} from database via Dao.
     *
     * @param globalFontsId the globalFontsId
     * @return {@link GlobalFonts}
     * @throws ProcessFailed the process failed
     */
    public GlobalFonts getGlobalFontsById(Integer globalFontsId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link GlobalFonts} from database via Dao.
     *
     * @return {@link GlobalFonts}
     * @throws ProcessFailed the process failed
     */
    public List<GlobalFonts> getAllGlobalFonts() throws ProcessFailed;
    
    /**
     * This method save {@link GlobalFonts} into the database via Dao.
     *
     * @param globalFonts the globalFonts
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(GlobalFonts globalFonts) throws ProcessFailed;

    /**
     * This method update {@link GlobalFonts} updates existing data from the
     * database via Dao.
     *
     * @param globalFonts the globalFonts
     * @throws ProcessFailed the process failed
     */
    public void update(GlobalFonts globalFonts) throws ProcessFailed;

    /**
     * This method delete particular {@link GlobalFonts} based on the
     * organization from the database via Dao.
     *
     * @param globalFontsId the globalFontsId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer globalFontsId) throws ProcessFailed;
    
}
