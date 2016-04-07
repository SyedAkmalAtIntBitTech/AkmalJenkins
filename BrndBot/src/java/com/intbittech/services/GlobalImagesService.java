/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalImages;
import java.util.List;

/**
 *
 * @author Haider Khan @ Intbit
 */
public interface GlobalImagesService {
    
     /**
     * This method pass id as input and get the {@link GlobalImages} from database via Dao.
     *
     * @param globalImagesId the globalImagesId
     * @return {@link GlobalImages}
     * @throws ProcessFailed the process failed
     */
    public GlobalImages getGlobalImagesById(Integer globalImagesId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link GlobalImages} from database via Dao.
     *
     * @return {@link GlobalImages}
     * @throws ProcessFailed the process failed
     */
    public List<GlobalImages> getAllGlobalImages() throws ProcessFailed;
    
    /**
     * This method save {@link GlobalImages} into the database via Dao.
     *
     * @param globalImages the globalImages
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(GlobalImages globalImages) throws ProcessFailed;

    /**
     * This method update {@link GlobalImages} updates existing data from the
     * database via Dao.
     *
     * @param globalImages the globalImages
     * @throws ProcessFailed the process failed
     */
    public void update(GlobalImages globalImages) throws ProcessFailed;

    /**
     * This method delete particular {@link GlobalImages} based on the
     * organization from the database via Dao.
     *
     * @param globalImagesId the globalImagesId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer globalImagesId) throws ProcessFailed;
    
}
