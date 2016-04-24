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
import email.mandrill.Message;
import email.mandrill.Recipient;
import email.mandrill.RecipientMetadata;
import email.mandrill.SendMail;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static social.controller.SendAnEmail.MANDRILL_KEY;

/**
 *
 * @author AR
 */
@Service
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
        message.setHtml("<html><body>" + imageContextPath + "changepassword.jsp?userid=" + hashURL + "</body></html>");
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

}
