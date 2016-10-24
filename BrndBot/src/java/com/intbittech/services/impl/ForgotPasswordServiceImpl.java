/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.GenerateHashPassword;
import com.intbittech.dao.ForgotPasswordDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ForgotPassword;
import com.intbittech.model.Users;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.Utility;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author AR
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private Logger logger = Logger.getLogger(ForgotPasswordServiceImpl.class);

    @Autowired
    ForgotPasswordDao forgotPasswordDAO;

    @Autowired
    UsersService usersService;
    
    @Autowired
    EmailServiceProviderService emailServiceProviderService;
    
    @Autowired
    private MessageSource messageSource;

    @Override
    public Integer save(ForgotPassword forgotPassword) throws ProcessFailed {
        return forgotPasswordDAO.save(forgotPassword);
    }

    @Override
    public void update(ForgotPassword forgotPassword) throws ProcessFailed {
        forgotPasswordDAO.update(forgotPassword);
    }

    @Override
    public void delete(ForgotPassword forgotPassword) throws ProcessFailed {
        forgotPasswordDAO.delete(forgotPassword);
    }

    @Override
    public void sendMail(String email_id, String imageContextPath) {
        
        
        Users user = usersService.getUserByEmailId(email_id);

        String randomVal = email_id + String.valueOf(user.getUserId()) + new Date().getTime();
        GenerateHashPassword generate_hash_password = new GenerateHashPassword();

        String hashURL = generate_hash_password.hashURL(randomVal);
        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setExpiryDate(new Date());
        forgotPassword.setFkUserId(user);
        forgotPassword.setRandomLink(hashURL);
        save(forgotPassword);

        String companyName = messageSource.getMessage("companyName",new String[]{}, Locale.US);
        String body = messageSource.getMessage("forgotPasswordBody",new String[]{}, Locale.US);
        String formattedBody = String.format(body, companyName, imageContextPath, hashURL);
        Content content = new Content(IConstants.kContentHTML, formattedBody);
        Email emailTo = new Email(email_id, Utility.combineUserName(user));
        String subject = messageSource.getMessage("forgotPasswordSubject",new String[]{}, Locale.US);
        String formattedSubject = String.format(subject, companyName);
        Mail mail = new Mail(null, formattedSubject, emailTo, content);
        String preHeader = "something";
        
        emailServiceProviderService.sendEmail(mail, EmailType.BrndBot_NoReply, 0);
    }

    @Override
    public void updatePassword(Integer userId, String hashPassword) throws ProcessFailed {
        Users user = usersService.getUserById(userId);
        if(user != null) {
            user.setUserPassword(hashPassword);
            usersService.update(user);
        }
    }

    @Override
    public ForgotPassword getByRandomHash(String hashURL) throws ProcessFailed {
        return forgotPasswordDAO.getByRandomHash(hashURL);
    }

}
