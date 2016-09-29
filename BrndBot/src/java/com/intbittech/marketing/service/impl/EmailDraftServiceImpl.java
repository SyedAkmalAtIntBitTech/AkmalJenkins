/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.service.impl;

import com.intbittech.marketing.dao.EmailDraftDao;
import com.intbittech.marketing.service.EmailDraftService;
import com.intbittech.model.EmailDraft;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author development
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class EmailDraftServiceImpl implements EmailDraftService {
    
    @Autowired
    public EmailDraftDao emaildraftdao;
    
    @Override
    public EmailDraft getById(Integer id) throws Throwable {
        return emaildraftdao.getById(id);
    }

    @Override
    public List<EmailDraft> getAllEmailDrafts(Integer companyId,Boolean isPushed) throws Throwable {
        return emaildraftdao.getAllEmailDrafts(companyId,isPushed);
    }

    @Override
    public Integer save(EmailDraft emailDraft) throws Throwable {
        return emaildraftdao.save(emailDraft);
    }

    @Override
    public void update(EmailDraft emailDraft) throws Throwable {
        emaildraftdao.update(emailDraft);
    }

    @Override
    public void delete(Integer id) throws Throwable {
        emaildraftdao.delete(id);
    }
    
}
