/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ImageModel;
import com.intbittech.model.PrintModel;
import java.util.ArrayList;
import java.util.List;

/**
 * <code> {@link ImageModelDao} </code> Interface to get ImageModel details from
 * image_model table
 *
 * @author ilyas
 */
public interface ImageModelDao {

    /**
     * This method pass id as input and get the {@link ImageModel} from database
     *
     *  *  * @return {@link ImageModel}
     * @return 
     * @throws ProcessFailed the process failed
     */
    public List<ImageModel> getAllImageModel() throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link ImageModel} from DAO
     * layer.
     *
     * @param imageModelId the imageModelId
     * @return {@link ImageModel}
     * @throws ProcessFailed the process failed
     */
    public ImageModel getByImageModelId(Integer imageModelId) throws ProcessFailed;

    /**
     * This method save {@link ImageModel} into the database.
     *
     * @param imageModel the ImageModel
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(ImageModel imageModel) throws ProcessFailed;

    /**
     * This method update {@link ImageModel} updates existing data from the
     * database.
     *
     * @param imageModel the ImageModel
     * @throws ProcessFailed the process failed
     */
    public void update(ImageModel imageModel) throws ProcessFailed;

    /**
     * This method delete particular {@link ImageModel} based on the ImageModel
     * from the database.
     *
     * @param imageModel the imageModel
     * @throws ProcessFailed the process failed
     */
    public void delete(ImageModel imageModel) throws ProcessFailed;
    
    /**
     * This method delete particular {@link ImageModel} based on the ImageModel
     * from the database.
     *
     * @return list of {@link ImageModel}
     * @param imageModelIds the imageModelIds
     * @throws ProcessFailed the process failed
     */
    public List<ImageModel> getByImageModelsByIds(ArrayList<Integer> imageModelIds) throws ProcessFailed;

}
