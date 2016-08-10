/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.ApplicationContextListener;
import com.controller.GenerateHashPassword;
import com.intbittech.controller.SignupController;
import com.intbittech.dao.UsersInviteDao;
import com.intbittech.email.mandrill.Message;
import com.intbittech.email.mandrill.Recipient;
import com.intbittech.email.mandrill.RecipientMetadata;
import com.intbittech.email.mandrill.SendMail;
import static com.intbittech.email.mandrill.SendMail.MANDRILL_KEY;
import com.intbittech.enums.AdminStatus;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Invite;
import com.intbittech.model.InvitedUsers;
import com.intbittech.model.UserCompanyLookup;
import com.intbittech.model.UserProfile;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.modelmappers.InviteDetails;
import com.intbittech.modelmappers.TaskDetails;
import com.intbittech.services.UserCompanyLookupService;
import com.intbittech.services.UserRoleLookUpService;
import com.intbittech.services.UserRoleService;
import com.intbittech.services.UsersInviteService;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UsersInviteServiceImpl implements UsersInviteService{

    private final static Logger logger = Logger.getLogger(UsersInviteServiceImpl.class);

    @Autowired
    private UsersInviteDao usersInviteDao;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserRoleLookUpService userRoleLookUpService;
    @Autowired
    private UserCompanyLookupService userCompanyLookupService;
    @Autowired
    private MessageSource messageSource;
    
    SendMail send_email = new SendMail();

    @Autowired
    private UsersService usersService;
    
    @Override
    public String save(Invite companyInvite) throws ProcessFailed {
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
    public void update(Invite companyInvite) throws ProcessFailed {
        usersInviteDao.update(companyInvite);
    }
    
    @Override
    public void delete(Integer Id) throws ProcessFailed {
        Invite companyInvite = usersInviteDao.getInvitedUserById(Id);
        if(companyInvite == null){
            throw new ProcessFailed("No user found with id " + Id + ".");
        }
         usersInviteDao.delete(companyInvite); 
    }

    @Override
    public List<Invite> getAllInvitedUsers(Users userFrom) throws ProcessFailed{
        List<Invite> invites = usersInviteDao.getAllInvitedUsers(userFrom);
        if(invites == null)
        {
            throw new ProcessFailed(messageSource.getMessage("user_list_not_found",new String[]{}, Locale.US));
        }
            return invites;

    }

    @Override
    public Invite getInvitedUserByUserTo(Users userTo) throws ProcessFailed{
        Invite invites = usersInviteDao.getInvitedUserByUserTo(userTo);
        if(invites == null)
        {
            throw new ProcessFailed(messageSource.getMessage("user_list_not_found",new String[]{}, Locale.US));
        }
            return invites;

    }
    
    @Override
    public Invite getInvitedUserById(Integer Id) throws ProcessFailed {
        Invite companyInvite = usersInviteDao.getInvitedUserById(Id);
        if(companyInvite == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return companyInvite;
    }

    @Override
    public Invite getInvitedUserByInviteCode(String inviteCode) throws ProcessFailed {
        Invite companyInvite = usersInviteDao.getUserByInviteCode(inviteCode);
        if(companyInvite == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return companyInvite;
    }
    
    @Override
    public boolean removeUsers(Integer inviteId)throws ProcessFailed{
        boolean returnMessage = false;
        try{
            Invite companyInvite = usersInviteDao.getInvitedUserById(inviteId);
            boolean isUsed = companyInvite.getIsUsed();

            if (isUsed){
                Users user = companyInvite.getInviteSentTo();
                UserCompanyLookup userCompanyLookup =  userCompanyLookupService.getUserCompanyLookupByUserAndCompanyId(user, null);
                userCompanyLookup.setAccountStatus(AdminStatus.valueOf("Account_Deactivated").getDisplayName());
                userCompanyLookupService.update(userCompanyLookup);
            }else {
                delete(inviteId);
            }
            returnMessage = true;
        }catch (Throwable throwable){
            throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return returnMessage;
    }

    @Override
    public List<InvitedUsers> getInvitedUsers(Users userFrom)throws ProcessFailed {
        String invitationStatus = null;InvitedUsers inviteduser = null;
        UsersRoleLookup userRoleLookUp = null; String userName = "";       
        try{
        List<Invite> invites = getAllInvitedUsers(userFrom);
        
        List<InvitedUsers> invitedUsers = new ArrayList<InvitedUsers>();
        if (invites != null){
            List<Invite> invitesList = new ArrayList<Invite>();
            invitesList = invites;
            
            for (int i = 0; i<invitesList.size(); i++){
                Invite invite = invitesList.get(i);
                Users user = invite.getInviteSentTo();
                if (user != null){
                    userName = user.getUserName();
                }else {
                    userName = invite.getInviteSentToEmailId();
                }
                JSONObject task = (JSONObject)StringUtility.stringToJSONObject(invite.getTask());
                ArrayList roles = (ArrayList)task.get("roles");
                String userRoleName = "", userRoleLookUpIds = "";
                for (int j = 0; j< roles.size(); j++){
                    Double role_id = (Double)roles.get(j);
                    UserRole userRole = userRoleService.getUserRoleById(role_id.intValue());
                    userRoleLookUp = userRoleLookUpService.getUsersRoleLookupByUserAndRoleId(user, userRole);
                    if (userRoleLookUp != null){
                        if (userRoleLookUpIds == ""){
                            userRoleLookUpIds = userRoleLookUp.getId().toString();
                        }else {
                            userRoleLookUpIds = userRoleLookUpIds + "," + userRoleLookUp.getId().toString();
                        }
                    }
                    if (userRoleName == ""){
                        userRoleName = AdminStatus.valueOf(userRole.getRoleName()).getDisplayName();
                    }else {
                        userRoleName = userRoleName + "," + AdminStatus.valueOf(userRole.getRoleName()).getDisplayName();
                    }
                    boolean isUsed = invite.getIsUsed();
                    if (isUsed){
                        invitationStatus = AdminStatus.valueOf("Invite_Success").getDisplayName();
                    }else {
                        invitationStatus = AdminStatus.valueOf("Invite_Sent").getDisplayName();
                    }
                }
                    inviteduser = new InvitedUsers(invite.getId(), userRoleLookUpIds, userName, userRoleName, invitationStatus);
                    invitedUsers.add(inviteduser);

            }
            return invitedUsers;
        }else {
            throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
            
        
    }
    
    @Override
    public boolean reSendInvitation(Integer inviteId)throws ProcessFailed{
        boolean returnMessage = false;
        try{
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            String fromEmailId = userProfile.getUser().getUserName();

            ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
            String contextRealPath = servletContext.getRealPath("");

            String contextPath = Utility.getServerName(contextRealPath);

            reSendMail(fromEmailId, contextPath, inviteId);
            returnMessage = true;
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return returnMessage;
    }
    
    @Override
    public void reSendMail(String fromEmailId, String imageContextPath, Integer inviteId)throws ProcessFailed {
        try{
            
        Users user = usersService.getUserByEmailId(fromEmailId);
        Invite companyInvite = getInvitedUserById(inviteId);

        String randomVal = companyInvite.getInviteSentToEmailId() + String.valueOf(user.getUserId()) + new Date().getTime();
        GenerateHashPassword generate_hash_password = new GenerateHashPassword();

        String hashURL = generate_hash_password.hashURL(randomVal);
        
        companyInvite.setCreatedDateTime(new Date());
        companyInvite.setCode(hashURL);
        companyInvite.setIsUsed(Boolean.FALSE);
        update(companyInvite);

        Message message = new Message();

        message.setKey(MANDRILL_KEY);
//        TODO code to be modified
         message.setHtml("<html><body><p><h2>User Invitation:</h2></p><p>You have been invited to join your company in BrndBot.</p>To create a user, click on the link below (or copy and paste the URL into your browser):<br />" + imageContextPath + "#/signup/userregistration?userid=" + hashURL + "</body></html>");
//        message.setText("text");
        /** need to change the above link and below message**/
        message.setSubject("User Invitation");
        message.setFrom_email("mail@brndbot.com");
        message.setFrom_name("BrndBot");
        message.setAsync(true);

        ArrayList<Recipient> messageToList = new ArrayList<Recipient>();

        Recipient recipient = new Recipient();
        recipient.setEmail(companyInvite.getInviteSentToEmailId());
        recipient.setType("to");

        messageToList.add(recipient);

        message.setMessageTo(messageToList);

        RecipientMetadata recipientMetadata = new RecipientMetadata();
        recipientMetadata.setRcpt(companyInvite.getInviteSentToEmailId());

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
    
    @Override
    public void sendMail(String fromEmailId, String imageContextPath, InviteDetails inviteDetails)throws ProcessFailed {
        try{
            
        Users user = usersService.getUserByEmailId(fromEmailId);

        String randomVal = inviteDetails.getEmailaddress() + String.valueOf(user.getUserId()) + new Date().getTime();
        GenerateHashPassword generateHashPassword = new GenerateHashPassword();

        String hashURL = generateHashPassword.hashURL(randomVal);
        Invite companyInvite = new Invite();
        
        companyInvite.setCreatedDateTime(new Date());
        companyInvite.setCode(hashURL);
        companyInvite.setInviteSentBy(user);
        companyInvite.setInviteSentTo(null);
        companyInvite.setMessage(null);
        companyInvite.setIsUsed(Boolean.FALSE);
        TaskDetails taskdetails = new TaskDetails(inviteDetails.getTask(),inviteDetails.getRoles());
        companyInvite.setTask(StringUtility.objectListToJsonString(taskdetails));
        companyInvite.setInviteSentToEmailId(inviteDetails.getEmailaddress());
        save(companyInvite);

        Message message = new Message();

        message.setKey(MANDRILL_KEY);
//        TODO code to be modified
         message.setHtml("<html><body><p><h2>User Invitation:</h2></p><p>You have been invited to join your company in BrndBot.</p>To create a user, click on the link below (or copy and paste the URL into your browser):<br />" + imageContextPath + "#/signup/userregistration?userid=" + hashURL + "</body></html>");
//        message.setText("text");
        /** need to change the above link and below message**/
        message.setSubject("User Invitation");
        message.setFrom_email("mail@brndbot.com");
        message.setFrom_name("BrndBot");
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
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem",new String[]{}, Locale.US));
        }

    }
}
