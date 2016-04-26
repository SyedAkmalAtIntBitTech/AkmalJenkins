/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.service.impl;

import com.intbittech.model.CompanyMarketingProgram;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intbittech.marketing.dao.CompanyMarketingProgramDao;
import com.intbittech.marketing.service.CompanyMarketingProgramService;
import com.intbittech.exception.ProcessFailed;

/**
 *
 * @author intbit-@jit
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CompanyMarketingProgramServiceImpl implements CompanyMarketingProgramService {

    @Autowired
    public CompanyMarketingProgramDao companyMarketingProgramDao;

    /**
     * {@inheritDoc}
     */
    public CompanyMarketingProgram getById(Integer id) throws ProcessFailed {
        return  companyMarketingProgramDao.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<CompanyMarketingProgram> getAllCompanyMarketingProgram() throws ProcessFailed {
        return  companyMarketingProgramDao.getAllCompanyMarketingProgram();
    }

    /**
     * {@inheritDoc}
     */
    public List<CompanyMarketingProgram> getAllCompanyMarketingOpenPrograms(String status, Integer companyId) throws ProcessFailed {
        return  companyMarketingProgramDao.getAllCompanyMarketingOpenPrograms(status, companyId);
    }

    /**
     * {@inheritDoc}
     */
    public List<CompanyMarketingProgram> getAllCompanyMarketingProgramByCompanyId(Integer companyId) throws ProcessFailed {
        return  companyMarketingProgramDao.getAllCompanyMarketingProgramByCompanyId(companyId);
    }

    /**
     * {@inheritDoc}
     */
    public List<CompanyMarketingProgram> getAllCompanyMarketingProgramBySessionCompanyId(Integer companyId) throws ProcessFailed {
        return  companyMarketingProgramDao.getAllCompanyMarketingProgramBySessionCompanyId(companyId);
    }

    /**
     * {@inheritDoc}
     */
    public List<CompanyMarketingProgram> getAllCompanyMarketingProgramByType(Integer companyId, String programType) throws ProcessFailed {
        return  companyMarketingProgramDao.getAllCompanyMarketingProgramByType(companyId, programType);
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed {
        return  companyMarketingProgramDao.save(companyMarketingProgram);
    }

    /**
     * {@inheritDoc}
     */
    public void update(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed {
        companyMarketingProgramDao.update(companyMarketingProgram);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer id) throws ProcessFailed {
        companyMarketingProgramDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public CompanyMarketingProgram getByCompanyMarketingProgramIdAndMarketingProgramId(Integer id, Integer marketingProgramId) throws ProcessFailed {
        return  companyMarketingProgramDao.getByCompanyMarketingProgramIdAndMarketingProgramId(id, marketingProgramId);
    }

}
