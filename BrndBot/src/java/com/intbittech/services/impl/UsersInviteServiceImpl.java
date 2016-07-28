/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.GenerateHashPassword;
import com.intbittech.controller.SignupController;
import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.dao.UsersInviteDao;
import com.intbittech.email.mandrill.Message;
import com.intbittech.email.mandrill.Recipient;
import com.intbittech.email.mandrill.RecipientMetadata;
import com.intbittech.email.mandrill.SendMail;
import static com.intbittech.email.mandrill.SendMail.MANDRILL_KEY;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyInvite;
import com.intbittech.model.ForgotPassword;
import com.intbittech.model.Organization;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.modelmappers.InviteDetails;
import com.intbittech.modelmappers.TaskDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.services.UsersInviteService;
import com.intbittech.services.UsersRoleLookUpService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.StringUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UsersInviteServiceImpl implements UsersInviteService{

    private final static Logger logger = Logger.getLogger(SignupController.class);

    @Autowired
    private UsersInviteDao usersInviteDao;
    
//    @Autowired
//    private UsersInviteService usersInviteService;
//    
//    @Autowired
//    private UsersRoleLookUpService usersRoleLookUpService;

    @Autowired
    private MessageSource messageSource;
    
    SendMail send_email = new SendMail();

    @Autowired
    private UsersService usersService;
    
    @Override
    public String save(CompanyInvite companyInvite) throws ProcessFailed {
        String returnMessage = "problem saving the record";
        try {
            usersInviteDao.save(companyInvite);

            returnMessage = "saved successfully";
        } catch(Throwable throwable) {
            returnMessage = "problem saving the record";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }  
        return returnMessage;    
    }

 /**
     * {@inheritDoc}
     */
    @Override
    public void update(CompanyInvite companyInvite) throws ProcessFailed {
        usersInviteDao.update(companyInvite);
    }
    
    @Override
    public void delete(Integer Id) throws ProcessFailed {
        CompanyInvite companyInvite = usersInviteDao.getInvitedUserById(Id);
        if(companyInvite == null){
            throw new ProcessFailed("No user found with id " + Id + ".");
        }
         usersInviteDao.delete(companyInvite); 
    }

    @Override
    public CompanyInvite getInvitedUserById(Integer Id) throws ProcessFailed {
        CompanyInvite companyInvite = usersInviteDao.getInvitedUserById(Id);
        if(companyInvite == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return companyInvite;
    }

    @Override
    public CompanyInvite getCompanyInviteByInviteCode(String inviteCode) throws ProcessFailed {
        CompanyInvite companyInvite = usersInviteDao.getUserByInviteCode(inviteCode);
        if(companyInvite == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return companyInvite;
    }

    @Override
    public void sendMail(String from_email_id, String imageContextPath, InviteDetails inviteDetails)throws ProcessFailed {
        try{
            
        Users user = usersService.getUserByEmailId(from_email_id);

        String randomVal = inviteDetails.getEmailaddress() + String.valueOf(user.getUserId()) + new Date().getTime();
        GenerateHashPassword generate_hash_password = new GenerateHashPassword();

        String hashURL = generate_hash_password.hashURL(randomVal);
        CompanyInvite companyInvite = new CompanyInvite();
        
        companyInvite.setCreatedDateTime(new Date());
        companyInvite.setCode(hashURL);
        companyInvite.setInviteSentBy(user);
        companyInvite.setInviteSentTo(null);
        companyInvite.setMessage(null);
        companyInvite.setIsUsed(Boolean.FALSE);
        TaskDetails taskdetails = new TaskDetails(inviteDetails.getTask(),inviteDetails.getRoles());
        companyInvite.setTask(StringUtility.objectListToJsonString(taskdetails));
        
        save(companyInvite);

        Message message = new Message();

        message.setKey(MANDRILL_KEY);
//                String url=request.getRequestURL().toString().replace("SendEmail","");  
//        TODO code to be modified
        message.setHtml("<html><body>" + imageContextPath + "changepassword?userid=" + hashURL + "</body></html>");
        message.setText("text");
        /** need to change the above link and below message**/
        message.setSubject("your password changing link for our account");
        message.setFrom_email("intbit@intbittech.com");
        message.setFrom_name("Intbit Tech");
        message.setAsync(true);

        ArrayList<Recipient> messageToList = new ArrayList<Recipient>();

        Recipient recipient = new Recipient();
        recipient.setEmail(inviteDetails.getEmailaddress());
        recipient.setType("to");

        messageToList.add(recipient);

        message.setMessageTo(messageToList);

        RecipientMetadata recipientMetadata = new RecipientMetadata();
        recipientMetadata.setRcpt(inviteDetails.getEmailaddress());

        ArrayList<RecipientMetadata> metadataList = new ArrayList<RecipientMetadata>();
        metadataList.add(recipientMetadata);
        metadataList.add(recipientMetadata);

        message.setRecipient_metadata(metadataList);
        send_email.sendMail(message);
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail send problem",new String[]{}, Locale.US));
       }

    }
}
