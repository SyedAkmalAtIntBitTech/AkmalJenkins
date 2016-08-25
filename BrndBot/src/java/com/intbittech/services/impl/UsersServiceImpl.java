/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.ApplicationContextListener;
import com.intbittech.AppConstants;
import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UserRoleLookUpDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.dao.UsersInviteDao;
import com.intbittech.email.mandrill.Message;
import com.intbittech.email.mandrill.Recipient;
import com.intbittech.email.mandrill.RecipientMetadata;
import com.intbittech.email.mandrill.SendMail;
import static com.intbittech.email.mandrill.SendMail.MANDRILL_KEY;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.Invite;
import com.intbittech.model.UserCompanyLookup;
import com.intbittech.model.UserProfile;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.modelmappers.InviteDetails;
import com.intbittech.modelmappers.TaskDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.services.UserCompanyLookupService;
import com.intbittech.services.UsersInviteService;
import com.intbittech.services.UserRoleCompanyLookUpService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.StringUtility;
import com.intbittech.utility.UserSessionUtil;
import com.intbittech.utility.Utility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UsersServiceImpl implements UsersService {

    private final static Logger logger = Logger.getLogger(UsersServiceImpl.class);
    
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UsersInviteService usersInviteService;
    
    @Autowired
    private UsersInviteDao usersInviteDao;
    
    @Autowired
    private UserRoleLookUpDao usersRoleLookUpDao;    
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleCompanyLookUpService usersRoleLookUpService;
    
    @Autowired
    private UserCompanyLookupService userCompanyLookUpService;
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
    public Users isUserExist(String userName) throws ProcessFailed {
          Users users = usersDao.findByUserName(userName);
        if (users == null) {
            return null;
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
    public Boolean isUserExistInCompany(InviteDetails inviteDetails, Company company) throws ProcessFailed {
        return usersDao.isUserExistInCompany(inviteDetails, company);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer save(UserDetails usersDetails) throws ProcessFailed {
        String returnMessage = "false";
        Integer returnUserId = 0;
        Company company = null;Users user = null;UsersRoleCompanyLookup usersRoleLookUp = null;
        UserCompanyLookup userCompanyLookup = null;
        try {
            //Save temporary company
//            company = new Company();
//            company.setCompanyName("none");
//            Integer companyId = companyDao.save(company);

            user = new Users();
            user.setUserName(usersDetails.getUserName());
            user.setUserPassword(passwordEncoder.encode(usersDetails.getUserPassword()));
            user.setFirstName(usersDetails.getFirstName());
            user.setLastName(usersDetails.getLastName());

            user.setCreatedDate(new Date());
            user.setSignupStatus(AppConstants.SignUpStatusIncomplete);
            Integer userId = usersDao.save(user);

            usersRoleLookUp = new UsersRoleCompanyLookup();

            UserRole userRole = new UserRole();
            userRole.setUserRoleId(AppConstants.UserRoleAccountOwnerValue);

            usersRoleLookUp.setUserId(user);
            usersRoleLookUp.setRoleId(userRole);
            
            usersRoleLookUpDao.save(usersRoleLookUp);
            
            returnUserId = userId;
        } catch(Throwable throwable) {
            returnMessage = "false";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        } finally {
            company = null;user = null;
        } 
        return returnUserId;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer saveUser(UserDetails usersDetails) throws ProcessFailed {
        Integer returnMessage = 0;
        Invite companyInvite = null;TaskDetails taskdetails = null;
        List roles = null;UsersRoleCompanyLookup usersRoleLookUp = null;
        UserCompanyLookup userCompanyLookup = null;Users existingUser = null;
        Integer userId =0;
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
                user.setSignupStatus(AppConstants.SignUpStatuscomplete);
                Users sentUser = companyInvite.getInviteSentBy();
                if ((usersDao.checkUniqueUser(user))){
                    userId = usersDao.save(user);
                }else {
                    user = usersDao.getUserByEmailId(usersDetails.getUserName());
                }
                userCompanyLookup = userCompanyLookUpService.getUserCompanyLookupByUser(sentUser);
                Company company = userCompanyLookup.getCompanyid();

                companyInvite.setInviteSentTo(user);
                companyInvite.setIsUsed(true);
                usersInviteService.update(companyInvite);
                taskdetails = (TaskDetails)StringUtility.stringToTaskDetails(companyInvite.getTask());

                roles = taskdetails.getRoles();
                for (int i = 0; i< roles.size(); i++){

                    usersRoleLookUp = new UsersRoleCompanyLookup();

                    UserRole userRole = new UserRole();
                    userRole.setUserRoleId((Integer)roles.get(i));

                    usersRoleLookUp.setUserId(user);
                    usersRoleLookUp.setRoleId(userRole);
                    usersRoleLookUp.setCompanyId(company);
                    usersRoleLookUp.setAccountStatus(AppConstants.Account_Activated);
                    boolean isRole = usersRoleLookUpService.isRoleExist(usersRoleLookUp);
                    if (isRole){
                        usersRoleLookUpDao.save(usersRoleLookUp);
                        if (userId != 0){
                            returnMessage = userId;
                        }else {
                            returnMessage = user.getUserId();
                        }
                    }else {
                        throw new ProcessFailed(messageSource.getMessage("role_exist", new String[]{}, Locale.US));
                    }
                }
                sendAcknowledgementEmail(usersDetails.getUserName());
                
            }else {
                throw new ProcessFailed(messageSource.getMessage("validity_expired", new String[]{}, Locale.US));
            }
        
        } catch(Throwable throwable) {
            returnMessage = 0;
            logger.log(Priority.ERROR, throwable);
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
    public boolean saveNonExistingUser(InviteDetails inviteDetails)throws ProcessFailed{
        boolean returnMessage = false;
        boolean userExist = false;UserCompanyLookup userCompanyLookup = null;
        try{
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            String fromEmailId = userProfile.getUser().getUserName();
            
            Users user = isUserExist(inviteDetails.getEmailaddress());
            if (user != null){
                userExist = checkUniqueUser(user);
            }
            
            if (userExist){
                userCompanyLookup = userCompanyLookUpService.getUserCompanyLookupByUser(userProfile.getUser());
                boolean userExistInCompany = isUserExistInCompany(inviteDetails,userCompanyLookup.getCompanyid());
                if (userExistInCompany){
                    setRole(inviteDetails);
                    throw new ProcessFailed(messageSource.getMessage("user_exist_role_assigned", new String[]{}, Locale.US));
                }else {
                    throw new ProcessFailed(messageSource.getMessage("user_does_not_exist_in_company", new String[]{}, Locale.US));
                }    
            }else if(!userExist) {
                ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
                String contextRealPath = servletContext.getRealPath("");

                String contextPath = Utility.getServerName(contextRealPath);
                
                usersInviteService.sendMail(fromEmailId, contextPath, inviteDetails);
                returnMessage = true;
            }
        }catch (Throwable throwable){
            throw new ProcessFailed(messageSource.getMessage("user_exist", new String[]{}, Locale.US));
        }
        return returnMessage;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(InviteDetails inviteDetails) throws ProcessFailed{
        TaskDetails taskdetails = null;
        List<Integer> roles = null;UsersRoleCompanyLookup usersRoleLookUp = null;
        Users user = null;
        try{
            
            taskdetails = new TaskDetails(inviteDetails.getTask(),inviteDetails.getRoles());
            
            user = findByUserName(inviteDetails.getEmailaddress());
                roles = taskdetails.getRoles();
                for (int i = 0; i< roles.size(); i++){
                    
                    usersRoleLookUp = new UsersRoleCompanyLookup();
                    Company company = companyDao.getCompanyById(inviteDetails.getCompanyId());
                    UserRole userRole = new UserRole();
                    userRole.setUserRoleId((Integer)roles.get(i));

                    usersRoleLookUp.setUserId(user);
                    usersRoleLookUp.setRoleId(userRole);
                    usersRoleLookUp.setCompanyId(company);
                    usersRoleLookUp.setAccountStatus(AppConstants.Account_Activated);
                   usersRoleLookUpService.save(usersRoleLookUp);
                    
                }
            
        }catch (Exception exception){
            logger.error(exception);
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }finally{
            roles = null;taskdetails =null;usersRoleLookUp = null;user = null;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateRole(InviteDetails inviteDetails) throws ProcessFailed{
        TaskDetails taskDetails = null, taskDetailsToSave = null;
        List<Integer> roles = null;UsersRoleCompanyLookup usersRoleLookUp = null;
        boolean flag = false;Users user = null;
        try{
            
            taskDetails = new TaskDetails(inviteDetails.getTask(),inviteDetails.getRoles());
            
            user = findByUserName(inviteDetails.getEmailaddress());
                roles = taskDetails.getRoles();
                String UserRoleLookUpIds[] = inviteDetails.getUserRoleLookUpId().split(",");
                for (int i = 0; i< roles.size(); i++){
                    
                    usersRoleLookUp = new UsersRoleCompanyLookup();

                    UserRole userRole = new UserRole();
                    Company company = companyDao.getCompanyById(inviteDetails.getCompanyId());
                    userRole.setUserRoleId((Integer)roles.get(i));
                    
                    usersRoleLookUp.setId(Integer.parseInt(UserRoleLookUpIds[i]));
                    usersRoleLookUp.setUserId(user);
                    usersRoleLookUp.setRoleId(userRole);
                    usersRoleLookUp.setCompanyId(company);
                    usersRoleLookUp.setAccountStatus(AppConstants.Account_Activated);
                    usersRoleLookUpService.update(usersRoleLookUp);
                    
                }
            Users userTo = findByUserName(inviteDetails.emailaddress);
            Invite invite = usersInviteService.getInvitedUserById(inviteDetails.getInviteId());
            
            invite.setTask(StringUtility.objectListToJsonString(taskDetails));
            usersInviteService.update(invite);
            flag = true;
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage(throwable.getMessage(), new String[]{}, Locale.US));
        }
        return flag;
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

            Long Datedifference = todayDate - createdDate ;
            
            boolean isUsed = companyInvite.getIsUsed();
            if ((Datedifference <= AppConstants.Datedifference) && (isUsed == false)){
                status = true;
            }else {
                status = false;
            }
            
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("problem_checking",new String[]{}, Locale.US));
        }    
            return status;
    }

    @Override
    public void sendAcknowledgementEmail(String toEmailId)throws ProcessFailed {
        try{
            
        Message message = new Message();

        message.setKey(MANDRILL_KEY);
//        TODO code to be modified
            
        message.setText(messageSource.getMessage("acknowledgement_message",new String[]{}, Locale.US));
        message.setSubject(messageSource.getMessage("acknowledgement_subject",new String[]{}, Locale.US));
        message.setFrom_email("mail@brndbot.com");
        message.setFrom_name("BrndBot");
        message.setAsync(true);

        ArrayList<Recipient> messageToList = new ArrayList<Recipient>();

        Recipient recipient = new Recipient();
        recipient.setEmail(toEmailId);
        recipient.setType("to");

        messageToList.add(recipient);

        message.setMessageTo(messageToList);

        RecipientMetadata recipientMetadata = new RecipientMetadata();
        recipientMetadata.setRcpt(toEmailId);

        ArrayList<RecipientMetadata> metadataList = new ArrayList<RecipientMetadata>();
        metadataList.add(recipientMetadata);
        metadataList.add(recipientMetadata);

        message.setRecipient_metadata(metadataList);
        send_email.sendMail(message);
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem",new String[]{}, Locale.US));
       }

    }
    
    
}
