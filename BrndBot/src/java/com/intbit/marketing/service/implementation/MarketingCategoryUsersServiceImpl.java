/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.MarketingCategoryDao;
import com.intbit.marketing.dao.MarketingCategoryUsersDao;
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.model.TblMarketingCategoryUsersLookup;
import com.intbit.marketing.service.MarketingCategoryUsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author development
 */
@Service
@Transactional
public class MarketingCategoryUsersServiceImpl implements MarketingCategoryUsersService{

   @Autowired
     public MarketingCategoryUsersDao marketingCategoryDaoUsers;
     

    /**
	 * {@inheritDoc}
     */
    public TblMarketingCategoryUsersLookup getById(Integer id) throws Throwable {
        return marketingCategoryDaoUsers.getById(id);
    }

     /**
	 * {@inheritDoc}
     */
    public List<TblMarketingCategoryUsersLookup> getAllMarketingCategoryUsers(Integer user_id) throws Throwable {
       return  marketingCategoryDaoUsers.getAllMarketingCategoryUsers(user_id);
    }

     /**
	 * {@inheritDoc}
     */
    public Integer save(TblMarketingCategoryUsersLookup marketingCategory) throws Throwable {
       return marketingCategoryDaoUsers.save(marketingCategory);
    }

     /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
        marketingCategoryDaoUsers.delete(id);
    }
    
    
}
