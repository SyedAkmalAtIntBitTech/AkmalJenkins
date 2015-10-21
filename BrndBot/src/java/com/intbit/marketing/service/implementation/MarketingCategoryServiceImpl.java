/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.MarketingCategoryDao;
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.service.MarketingCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intbit-6
 */
@Service
@Transactional
public class MarketingCategoryServiceImpl implements MarketingCategoryService{
    
     @Autowired
     public MarketingCategoryDao marketingCategoryDao;
     

    /**
	 * {@inheritDoc}
     */
    public TblMarketingCategory getById(Integer id) throws Throwable {
        return marketingCategoryDao.getById(id);
    }

     /**
	 * {@inheritDoc}
     */
    public List<TblMarketingCategory> getAllMarketingCategory() throws Throwable {
       return  marketingCategoryDao.getAllMarketingCategory();
    }

     /**
	 * {@inheritDoc}
     */
    public Integer save(TblMarketingCategory marketingCategory) throws Throwable {
       return marketingCategoryDao.save(marketingCategory);
    }

     /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
        marketingCategoryDao.delete(id);
    }
    
}
