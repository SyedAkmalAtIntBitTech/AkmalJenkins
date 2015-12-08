/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.service.implementation;

import com.intbit.marketing.dao.EmailDraftDao;
import com.intbit.marketing.model.TblEmailDraft;
import com.intbit.marketing.service.EmailDraftService;
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
    public TblEmailDraft getById(Integer id) throws Throwable {
        return emaildraftdao.getById(id);
    }

    @Override
    public List<TblEmailDraft> getAllEmailDrafts(Integer user_id) throws Throwable {
        return emaildraftdao.getAllEmailDrafts(user_id);
    }

    @Override
    public Integer save(TblEmailDraft emailDraft) throws Throwable {
        return emaildraftdao.save(emailDraft);
    }

    @Override
    public void update(TblEmailDraft emailDraft) throws Throwable {
        emaildraftdao.update(emailDraft);
    }

    @Override
    public void delete(Integer id) throws Throwable {
        emaildraftdao.delete(id);
    }
    
}
