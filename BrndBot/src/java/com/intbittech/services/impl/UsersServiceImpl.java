/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.AppConstants;
import com.intbittech.controller.SignupController;
import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.email.mandrill.Message;
import com.intbittech.email.mandrill.Recipient;
import com.intbittech.email.mandrill.RecipientMetadata;
import com.intbittech.email.mandrill.SendMail;
import static com.intbittech.email.mandrill.SendMail.MANDRILL_KEY;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.Invite;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UserRoleLookup;
import com.intbittech.modelmappers.InviteDetails;
import com.intbittech.modelmappers.TaskDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.services.UsersInviteService;
import com.intbittech.services.UserRoleLookUpService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.StringUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UsersServiceImpl implements UsersService {

    private final static Logger logger = Logger.getLogger(SignupController.class);
    
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UsersInviteService usersInviteService;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleLookUpService usersRoleLookUpService;
    
    SendMail send_email = new SendMail();

    /**
     * {@inheritDoc}
     */
    @Override
    public Users findByUserName(String userName) throws ProcessFailed {
          Users users = usersDao.findByUserName(userName);
        if (users == null) {
            throw new ProcessFailed("No user found.");
        }
        return users;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean checkUniqueUser(Users user) throws ProcessFailed {
        return usersDao.checkUniqueUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isUserExist(InviteDetails inviteDetails, Company company) throws ProcessFailed {
        return usersDao.isUserExist(inviteDetails, company);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String save(UserDetails usersDetails) throws ProcessFailed {
        String returnMessage = "false";
        Company company = null;Users user = null;
        try {
            //Save temporary company
            company = new Company();
            company.setCompanyName("none");
            Integer companyId = companyDao.save(company);

            user = new Users();
            user.setUserName(usersDetails.getUserName());
            user.setUserPassword(passwordEncoder.encode(usersDetails.getUserPassword()));
            user.setFirstName(usersDetails.getFirstName());
            user.setLastName(usersDetails.getLastName());

            user.setCreatedDate(new Date());
            Company companyObject = new Company();
            companyObject.setCompanyId(companyId);
            user.setFkCompanyId(companyObject);
            usersDao.save(user);

            returnMessage = "true";
        } catch(Throwable throwable) {
            returnMessage = "false";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        } finally {
            company = null;user = null;
        } 
        return returnMessage;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveUser(UserDetails usersDetails) throws ProcessFailed {
        boolean returnMessage = false;
        Invite companyInvite = null;TaskDetails taskdetails = null;
        ArrayList roles = null;UserRoleLookup usersRoleLookUp = null;
        
        try {
            companyInvite = usersInviteService.getInvitedUserByInviteCode(usersDetails.getInvitationCode());
        /** this step checks the validity of the invitation code **/
            boolean validity = isInviteCodeValid(usersDetails.getInvitationCode());
            if (validity){
                
                Users user = new Users();
                user.setUserName(usersDetails.getUserName());
                user.setUserPassword(passwordEncoder.encode(usersDetails.getUserPassword()));
                user.setFirstName(usersDetails.getFirstName());
                user.setLastName(usersDetails.getLastName());
                user.setCreatedDate(new Date());
                Company companyObject = new Company();
                companyObject.setCompanyId(usersDetails.getCompanyId());
                user.setFkCompanyId(companyObject);
                if (usersDao.checkUniqueUser(user)){
                    usersDao.save(user);
                }else {
                    throw new ProcessFailed(messageSource.getMessage("user already exist", new String[]{}, Locale.US));
                }
                companyInvite.setInviteSentTo(user);
                companyInvite.setIsUsed(true);
                usersInviteService.update(companyInvite);
                
                taskdetails = (TaskDetails)StringUtility.stringToObject(companyInvite.getTask());
                
                roles = taskdetails.getRoles();
                for (int i = 0; i< roles.size(); i++){
                    
                    usersRoleLookUp = new UserRoleLookup();

                    UserRole userRole = new UserRole();
                    userRole.setUserRoleId((Integer)roles.get(i));

                    usersRoleLookUp.setUserId(user);
                    usersRoleLookUp.setRoleId(userRole);
                    usersRoleLookUpService.save(usersRoleLookUp);
                    
                }
                sendMail(usersDetails.getUserName());
                returnMessage = true;
                
            }else {
                throw new ProcessFailed(messageSource.getMessage("validity failed", new String[]{}, Locale.US));
            }
        
        } catch(Throwable throwable) {
            returnMessage = false;
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }finally {
            companyInvite = null;taskdetails = null;roles = null;usersRoleLookUp = null;
        }  
        return returnMessage;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Users user) throws ProcessFailed {
        usersDao.update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Users getUserById(Integer userId) throws ProcessFailed {
        Users user = usersDao.getUserById(userId);
        if(user == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return user;
    }

    @Override
    public Users getUserByEmailId(String emailId) throws ProcessFailed {
        Users user = usersDao.getUserByEmailId(emailId);
        if(user == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return user;
    }
    
    @Override
    public boolean isInviteCodeValid(String inviteCode)throws ProcessFailed {
            boolean status = false;
        try{
            Invite companyInvite = usersInviteService.getInvitedUserByInviteCode(inviteCode);

            Long createdDate = companyInvite.getCreatedDateTime().getTime();
            Long todayDate = new Date().getTime();

            Long Datedifference = createdDate - todayDate;
            
            boolean isUsed = companyInvite.getIsUsed();
            if ((Datedifference <= AppConstants.Datedifference) && (!isUsed)){
                status = true;
            }else {
                status = false;
            }
            
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("problem checking the data",new String[]{}, Locale.US));
        }    
            return status;
    }

    @Override
    public void sendMail(String from_email_id)throws ProcessFailed {
        try{
            
        Message message = new Message();

        message.setKey(MANDRILL_KEY);
//                String url=request.getRequestURL().toString().replace("SendEmail","");  
//        TODO code to be modified
        message.setText("you have been assigned a new role");
        /** need to change the above link and below message**/
        message.setSubject("new role assigned");
        message.setFrom_email("intbit@intbittech.com");
        message.setFrom_name("Intbit Tech");
        message.setAsync(true);

        ArrayList<Recipient> messageToList = new ArrayList<Recipient>();

        Recipient recipient = new Recipient();
        recipient.setEmail(from_email_id);
        recipient.setType("to");

        messageToList.add(recipient);

        message.setMessageTo(messageToList);

        RecipientMetadata recipientMetadata = new RecipientMetadata();
        recipientMetadata.setRcpt(from_email_id);

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
