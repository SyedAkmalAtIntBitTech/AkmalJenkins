/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalColors;
import java.util.List;

/**
 * <code> {@link GlobalColorsDao} </code> Interface to get GlobalColors details from
 * global_colors table
 *
 * @author ilyas
 */
public interface GlobalColorsDao {
    
    /**
     * This method pass id as input and get the {@link GlobalColors} from database.
     *
     * @param globalColorsId the globalColorsId
     * @return {@link GlobalColors}
     * @throws ProcessFailed the process failed
     */
    public GlobalColors getGlobalColorsById(Integer globalColorsId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link GlobalColors} from database.
     *
     * @return {@link GlobalColors}
     * @throws ProcessFailed the process failed
     */
    public List<GlobalColors> getAllGlobalColors() throws ProcessFailed;
    
    /**
     * This method save {@link GlobalColors} into the database.
     *
     * @param globalColors the globalColors
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(GlobalColors globalColors) throws ProcessFailed;

    /**
     * This method update {@link GlobalColors} updates existing data from the
     * database.
     *
     * @param globalColors the globalColors
     * @throws ProcessFailed the process failed
     */
    public void update(GlobalColors globalColors) throws ProcessFailed;

    /**
     * This method delete particular {@link GlobalColors} based on the
     * organization from the database.
     *
     * @param globalColors the globalColors
     * @throws ProcessFailed the process failed
     */
    public void delete(GlobalColors globalColors) throws ProcessFailed;
    
}
