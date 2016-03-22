/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalColors;
import java.util.List;

/**
 * <code>{@link GlobalColorsService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface GlobalColorsService {
    
    /**
     * This method pass id as input and get the {@link GlobalColors} from database via Dao.
     *
     * @param globalColorsId the globalColorsId
     * @return {@link GlobalColors}
     * @throws ProcessFailed the process failed
     */
    public GlobalColors getGlobalColorsById(Integer globalColorsId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link GlobalColors} from database via Dao.
     *
     * @return {@link GlobalColors}
     * @throws ProcessFailed the process failed
     */
    public List<GlobalColors> getAllGlobalColors() throws ProcessFailed;
    
    /**
     * This method save {@link GlobalColors} into the database via Dao.
     *
     * @param globalColors the globalColors
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(GlobalColors globalColors) throws ProcessFailed;

    /**
     * This method update {@link GlobalColors} updates existing data from the
     * database via Dao.
     *
     * @param globalColors the globalColors
     * @throws ProcessFailed the process failed
     */
    public void update(GlobalColors globalColors) throws ProcessFailed;

    /**
     * This method delete particular {@link GlobalColors} based on the
     * organization from the database via Dao.
     *
     * @param globalColorsId the globalColorsId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer globalColorsId) throws ProcessFailed;
    
}
