/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.MarketingProgramDao;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.service.MarketingProgramService;
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
public class MarketingProgramServiceImpl implements MarketingProgramService{
    @Autowired
    public MarketingProgramDao marketingProgramDao;

    /**
	 * {@inheritDoc}
     */
    public TblMarketingProgram getById(Integer id) throws Throwable {
       return  marketingProgramDao.getById(id);
    }

    /**
	 * {@inheritDoc}
     */
    public List<TblMarketingProgram> getAllTblMarketingProgram() throws Throwable {
        return  marketingProgramDao.getAllTblMarketingProgram();
    }

   /**
	 * {@inheritDoc}
     */
    public Integer save(TblMarketingProgram marketingProgram) throws Throwable {
        return  marketingProgramDao.save(marketingProgram);
    }

    /**
	 * {@inheritDoc}
     */
    public void update(TblMarketingProgram marketingProgram) throws Throwable {
         marketingProgramDao.update(marketingProgram);
    }

   /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
        marketingProgramDao.delete(id);
    }
    
}
