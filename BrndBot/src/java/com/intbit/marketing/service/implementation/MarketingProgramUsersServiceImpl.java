/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.MarketingProgramUsersDao;
import com.intbit.marketing.model.TblMarketingProgramUsersLookup;
import com.intbit.marketing.service.MarketingProgramUsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarketingProgramUsersServiceImpl implements MarketingProgramUsersService {

    @Autowired
    public MarketingProgramUsersDao marketingProgramDaoUsers;

    @Override
    public TblMarketingProgramUsersLookup getById(Integer id) throws Throwable {
        return marketingProgramDaoUsers.getById(id);
    }

    @Override
    public List<TblMarketingProgramUsersLookup> getAllMarketingProgramUsers() throws Throwable {
        return marketingProgramDaoUsers.getAllMarketingProgramUsers();
    }

    @Override
    public Integer save(TblMarketingProgramUsersLookup marketingProgramUser) throws Throwable {
        return marketingProgramDaoUsers.save(marketingProgramUser);
    }

    @Override
    public void delete(Integer id) throws Throwable {
        marketingProgramDaoUsers.delete(id);
    }
    
}
