/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalImages;
import java.util.List;

/**
 *<code> {@link GlobalImagesDao} </code> Interface to get GlobalImages details from
 * global_images table
 * @author Haider Khan @ Intbit
 */
public interface GlobalImagesDao {
      /**
     * This method pass id as input and get the {@link GlobalImages} from database.
     *
     * @param globalImagesId the globalImagesId
     * @return {@link GlobalImages}
     * @throws ProcessFailed the process failed
     */
    public GlobalImages getGlobalImagesById(Integer globalImagesId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link GlobalImages} from database.
     *
     * @return {@link GlobalImages}
     * @throws ProcessFailed the process failed
     */
    public List<GlobalImages> getAllGlobalImages() throws ProcessFailed;
    
    /**
     * This method save {@link GlobalImages} into the database.
     *
     * @param globalImages the globalImages
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(GlobalImages globalImages) throws ProcessFailed;

    /**
     * This method update {@link GlobalImages} updates existing data from the
     * database.
     *
     * @param globalImages the globalImages
     * @throws ProcessFailed the process failed
     */
    public void update(GlobalImages globalImages) throws ProcessFailed;

    /**
     * This method delete particular {@link GlobalImages} based on the
     * organization from the database.
     *
     * @param globalImages the globalImages
     * @throws ProcessFailed the process failed
     */
    public void delete(GlobalImages globalImages) throws ProcessFailed;
    
    /**
     * 
     * @param globalImageName
     * @throws ProcessFailed 
     */
    
    public Boolean checkForUniqueness(String globalImageName) throws ProcessFailed;
}
