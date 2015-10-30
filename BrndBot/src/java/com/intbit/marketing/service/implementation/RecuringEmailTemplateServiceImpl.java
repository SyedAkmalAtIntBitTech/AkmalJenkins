/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.RecuringEmailTemplateDao;
import com.intbit.marketing.model.TblRecuringEmailTemplate;
import com.intbit.marketing.service.RecuringEmailTemplateService;
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
public class RecuringEmailTemplateServiceImpl implements RecuringEmailTemplateService{
    
    @Autowired
    public  RecuringEmailTemplateDao recuringEmailTemplateDao;

     /**
	 * {@inheritDoc}
     */
    public TblRecuringEmailTemplate getById(Integer id) throws Throwable {
        return  recuringEmailTemplateDao.getById(id);
    }

      /**
	 * {@inheritDoc}
     */
    public List<TblRecuringEmailTemplate> getAllRecuringEmailTemplate() throws Throwable {
        return  recuringEmailTemplateDao.getAllRecuringEmailTemplate();
        
    }

     /**
	 * {@inheritDoc}
     */
    public Integer save(TblRecuringEmailTemplate recuringEmailTemplate) throws Throwable {
       return  recuringEmailTemplateDao.save(recuringEmailTemplate);
    }

      /**
	 * {@inheritDoc}
     */
    public void update(TblRecuringEmailTemplate recuringEmailTemplate) throws Throwable {
       recuringEmailTemplateDao.update(recuringEmailTemplate);
    }

      /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
        recuringEmailTemplateDao.delete(id);
    }
    
}
