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
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.services.UsersService;
import com.intbittech.email.mandrill.Message;
import com.intbittech.email.mandrill.Recipient;
import com.intbittech.email.mandrill.RecipientMetadata;
import com.intbittech.email.mandrill.SendMail;
import static com.intbittech.email.mandrill.SendMail.MANDRILL_KEY;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    SendMail send_email = new SendMail();

    @Autowired
    ForgotPasswordDao forgotPasswordDAO;

    @Autowired
    UsersService usersService;
    

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

        Message message = new Message();

        message.setKey(MANDRILL_KEY);
//                String url=request.getRequestURL().toString().replace("SendEmail","");  
        message.setHtml("<html><body>" + imageContextPath + "changepassword?userid=" + hashURL + "</body></html>");
        message.setText("text");
        message.setSubject("your password changing link for our account");
        message.setFrom_email("intbit@intbittech.com");
        message.setFrom_name("Intbit Tech");
        message.setAsync(true);

        ArrayList<Recipient> messageToList = new ArrayList<Recipient>();

        Recipient recipient = new Recipient();
        recipient.setEmail(email_id);
        recipient.setType("to");

        messageToList.add(recipient);

        message.setMessageTo(messageToList);

        RecipientMetadata recipientMetadata = new RecipientMetadata();
        recipientMetadata.setRcpt(email_id);

        ArrayList<RecipientMetadata> metadataList = new ArrayList<RecipientMetadata>();
        metadataList.add(recipientMetadata);
        metadataList.add(recipientMetadata);

        message.setRecipient_metadata(metadataList);
        send_email.sendMail(message);
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
