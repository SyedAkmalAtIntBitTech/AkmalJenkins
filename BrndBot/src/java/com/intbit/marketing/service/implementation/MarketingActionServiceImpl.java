/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.MarketingActionDao;
import com.intbit.marketing.model.TblMarketingAction;
import com.intbit.marketing.service.MarketingActionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intbit-6
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class MarketingActionServiceImpl  implements MarketingActionService{
    
     @Autowired
     public MarketingActionDao marketingActionDao;

     /**
	 * {@inheritDoc}
     */
    public TblMarketingAction getById(Integer id) throws Throwable {
        return marketingActionDao.getById(id);
    }

     /**
	 * {@inheritDoc}
     */
    public List<TblMarketingAction> getAllMarketingAction() throws Throwable {
        return  marketingActionDao.getAllMarketingAction();
    }

     /**
	 * {@inheritDoc}
     */
    public Integer save(TblMarketingAction marketingAction) throws Throwable {
       return  marketingActionDao.save(marketingAction);
    }

     /**
	 * {@inheritDoc}
     */
    public void update(TblMarketingAction marketingAction) throws Throwable {
        marketingActionDao.update(marketingAction);
    }

    /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
       marketingActionDao.delete(id);
    }
    
    
}
