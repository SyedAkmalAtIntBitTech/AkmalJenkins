/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalFonts;
import java.util.List;

/**
 * <code> {@link GlobalFontsDao} </code> Interface to get GlobalFonts details from
 * global_fonts table
 *
 * @author ilyas
 */
public interface GlobalFontsDao {
    
    /**
     * This method pass id as input and get the {@link GlobalFonts} from database.
     *
     * @param globalFontsId the globalFontsId
     * @return {@link GlobalFonts}
     * @throws ProcessFailed the process failed
     */
    public GlobalFonts getGlobalFontsById(Integer globalFontsId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link GlobalFonts} from database.
     *
     * @return {@link GlobalFonts}
     * @throws ProcessFailed the process failed
     */
    public List<GlobalFonts> getAllGlobalColors() throws ProcessFailed;
    
    /**
     * This method save {@link GlobalFonts} into the database.
     *
     * @param globalFonts the globalFonts
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(GlobalFonts globalFonts) throws ProcessFailed;

    /**
     * This method update {@link GlobalFonts} updates existing data from the
     * database.
     *
     * @param globalFonts the globalFonts
     * @throws ProcessFailed the process failed
     */
    public void update(GlobalFonts globalFonts) throws ProcessFailed;

    /**
     * This method delete particular {@link GlobalFonts} based on the
     * organization from the database.
     *
     * @param globalFonts the globalFonts
     * @throws ProcessFailed the process failed
     */
    public void delete(GlobalFonts globalFonts) throws ProcessFailed;
    
}
