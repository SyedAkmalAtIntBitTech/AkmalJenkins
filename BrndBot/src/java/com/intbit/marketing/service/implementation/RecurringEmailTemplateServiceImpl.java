/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.RecurringEmailTemplateDao;
import com.intbit.marketing.model.TblRecurringEmailTemplate;
import com.intbit.marketing.service.RecurringEmailTemplateService;
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
public class RecurringEmailTemplateServiceImpl implements RecurringEmailTemplateService{
    
    @Autowired
    public  RecurringEmailTemplateDao recurringEmailTemplateDao;

     /**
	 * {@inheritDoc}
     */
    public TblRecurringEmailTemplate getById(Integer id) throws Throwable {
        return  recurringEmailTemplateDao.getById(id);
    }

      /**
	 * {@inheritDoc}
     */
    public List<TblRecurringEmailTemplate> getAllRecurringEmailTemplate() throws Throwable {
        return  recurringEmailTemplateDao.getAllRecurringEmailTemplate();
        
    }

     /**
	 * {@inheritDoc}
     */
    public Integer save(TblRecurringEmailTemplate recurringEmailTemplate) throws Throwable {
       return  recurringEmailTemplateDao.save(recurringEmailTemplate);
    }

      /**
	 * {@inheritDoc}
     */
    public void update(TblRecurringEmailTemplate recurringEmailTemplate) throws Throwable {
       recurringEmailTemplateDao.update(recurringEmailTemplate);
    }

      /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
        recurringEmailTemplateDao.delete(id);
    }
    
}
