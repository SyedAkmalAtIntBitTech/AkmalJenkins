/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ImageModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ImageModel;
import com.intbittech.model.PrintModel;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link ImageModelDaoImpl} </code> is implementation of
 * {@link ImageModelDao} and perform the database related operation for managing
 * {@link ImageModel}
 *
 * @author ilyas
 */
@Repository
public class ImageModelDaoImpl implements ImageModelDao {

    private static Logger logger = Logger.getLogger(ImageModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    
     public List<ImageModel> getAllImageModel() throws ProcessFailed{
          try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ImageModel.class);
            List<ImageModel> imageModelList = criteria.list();
            if (imageModelList.isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records.");
        }
         
         
     }
    public ImageModel getByImageModelId(Integer imageModelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ImageModel.class)
                    .add(Restrictions.eq("imageModelId", imageModelId));
            List<ImageModel> imageModelList = criteria.list();
            if (imageModelList.isEmpty()) {
                return null;
            }
            return (ImageModel) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ImageModel imageModel) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(imageModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(ImageModel imageModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(imageModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(ImageModel imageModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(imageModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<ImageModel> getByImageModelsByIds(Integer[] imageModelIds) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ImageModel.class);
            for(int i =0 ; i<imageModelIds.length;i++)
            criteria.add(Restrictions.ne("printModelId", imageModelIds[i]));
            List<ImageModel> imageModelList = criteria.list();
            if (imageModelList.isEmpty()) {
                return null;
            }
            return imageModelList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records");
        }
    }

   
}
