/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.ApplicationContextListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intbittech.AppConstants;
import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UserRoleCompanyLookUpDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.dao.UsersInviteDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.Invite;
import com.intbittech.model.SendGridSubUserDetails;
import com.intbittech.model.UserProfile;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.modelmappers.InviteDetails;
import com.intbittech.modelmappers.TaskDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.modelmappers.UserPreferencesJson;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.sendgrid.models.SendGridAPIDetails;
import com.intbittech.sendgrid.models.SendGridUser;
import com.intbittech.sendgrid.models.SubUserAPIKey;
import com.intbittech.sendgrid.models.Subuser;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.SendGridSubUserDetailsService;
import com.intbittech.services.UsersInviteService;
import com.intbittech.services.UserRoleCompanyLookUpService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.StringUtility;
import com.intbittech.utility.UserSessionUtil;
import com.intbittech.utility.Utility;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
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
    private UserRoleCompanyLookUpDao usersRoleLookUpDao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleCompanyLookUpService usersRoleCompanyLookUpService;

    @Autowired
    private EmailServiceProviderService emailServiceProviderService;

    @Autowired
    private SendGridSubUserDetailsService sendGridSubUserDetailsService;

    @Autowired
    private UserPreferencesJson userPreferencesJson;

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

    @Override
    public void saveSubUser(UserDetails usersDetails, Integer userId, Integer companyId) throws ProcessFailed {
        try {
            //Save subuser in sendgrid
            Subuser subuser = new Subuser();
            subuser.setEmail(usersDetails.getUserName());
//            subuser.setPassword(usersDetails.getUserPassword());
            //Hardcoded password and will be changed later
            subuser.setPassword("sendgrid123");
            //TODO change this ips
            List<String> ips = new ArrayList<String>();
            ips.add("198.37.159.5");
            subuser.setIps(ips);
            SendGridUser sendGridUser = emailServiceProviderService.addSubuser(subuser);

            //Create Sub User API Key
            SubUserAPIKey subUserAPIKey = emailServiceProviderService.createSubUserAPIKey(usersDetails.getUserName());
            SendGridAPIDetails sendGridAPIDetails = new SendGridAPIDetails();
            sendGridAPIDetails.setApiKey(subUserAPIKey.getApiKey());
            sendGridAPIDetails.setApiKeyId(subUserAPIKey.getApiKeyId());
            sendGridAPIDetails.setName(subUserAPIKey.getName());

            //Save userID in db
            SendGridSubUserDetails sendGridSubUserDetails = new SendGridSubUserDetails();
            Users user = new Users();
            user.setUserId(userId);
            sendGridSubUserDetails.setFkUserId(user);
            sendGridSubUserDetails.setSendGridUserId(usersDetails.getUserName());
            
            if(companyId != 0) {
                Company company = new Company();
                company.setCompanyId(companyId);
                sendGridSubUserDetails.setFkCompanyId(company);
            }

            sendGridSubUserDetails.setEmailAPIKey(sendGridAPIDetails.build());

            sendGridSubUserDetailsService.save(sendGridSubUserDetails);
        } catch (Throwable throwable) {
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer save(UserDetails usersDetails) throws ProcessFailed {
        Integer returnUserId = 0;
        Users user = null;
        UsersRoleCompanyLookup usersRoleLookUp = null;
        try {

            user = new Users();
            user.setUserName(usersDetails.getUserName());
            String encodedPassword = passwordEncoder.encode(usersDetails.getUserPassword());
            user.setUserPassword(encodedPassword);
            user.setFirstName(usersDetails.getFirstName());
            user.setLastName(usersDetails.getLastName());

            user.setCreatedDate(new Date());
            user.setSignupStatus(AppConstants.SignUpStatusIncomplete);
            userPreferencesJson.setProfileColor(usersDetails.getUserColor());
            ObjectMapper mapper = new ObjectMapper();
            String userPreferencesJsonToString = mapper.writeValueAsString(userPreferencesJson);
            user.setUserPreferences(userPreferencesJsonToString);
            Integer userId = usersDao.save(user);

            usersRoleLookUp = new UsersRoleCompanyLookup();

            UserRole userRole = new UserRole();
            userRole.setUserRoleId(AppConstants.UserRoleAccountOwnerValue);

            usersRoleLookUp.setUserId(user);
            usersRoleLookUp.setRoleId(userRole);

            usersRoleLookUpDao.save(usersRoleLookUp);

            //CompanyId is 0 since company is not created yet
            saveSubUser(usersDetails, userId, 0);

            returnUserId = userId;
        } catch (Throwable throwable) {
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }
        return returnUserId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Invite processCode(String inviteCode) throws ProcessFailed {
        Invite companyInvite = null;
        companyInvite = usersInviteService.getInvitedUserByInviteCode(inviteCode);
        return companyInvite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer saveUser(UserDetails usersDetails) throws ProcessFailed {
        Integer returnMessage = 0;
        Invite companyInvite = null;
        TaskDetails taskdetails = null;
        List roles = null;
        UsersRoleCompanyLookup usersRoleCompanyLookUp = null, usersRoleCompanyLookUp1 = null;
        Users existingUser = null;
        Integer userId = 0;
        try {
            companyInvite = processCode(usersDetails.getInvitationCode());
            /**
             * this step checks the validity of the invitation code *
             */
            boolean validity = isInviteCodeValid(usersDetails.getInvitationCode());
            if (validity) {

                Users user = new Users();
                String UserName = usersDetails.getUserName();

                if (UserName != null) {
                    user.setUserName(usersDetails.getUserName());
                    user.setUserPassword(passwordEncoder.encode(usersDetails.getUserPassword()));
                    user.setFirstName(usersDetails.getFirstName());
                    user.setLastName(usersDetails.getLastName());
                    user.setCreatedDate(new Date());
                    userPreferencesJson.setProfileColor(usersDetails.getUserColor());
                    ObjectMapper mapper = new ObjectMapper();
                    String userPreferencesJsonToString = mapper.writeValueAsString(userPreferencesJson);
                    user.setUserPreferences(userPreferencesJsonToString);
                    user.setSignupStatus(AppConstants.SignUpStatuscomplete);
                    if ((usersDao.checkUniqueUser(user))) {
                        userId = usersDao.save(user);
                    } else {
                        user = usersDao.getUserByEmailId(usersDetails.getUserName());
                    }
                } else {
                    user = usersDao.getUserById(usersDetails.getUserId());
                }
                Users inviteSentByUser = companyInvite.getInviteSentBy();
                usersRoleCompanyLookUp1 = usersRoleCompanyLookUpService.getUsersRoleLookupByUser(inviteSentByUser);
                Company company = usersRoleCompanyLookUp1.getCompanyId();
                companyInvite.setInviteSentTo(user);
                companyInvite.setIsUsed(true);
                usersInviteService.update(companyInvite);
                taskdetails = (TaskDetails) StringUtility.stringToTaskDetails(companyInvite.getTask());

                roles = taskdetails.getRoles();
                for (int i = 0; i < roles.size(); i++) {

                    usersRoleCompanyLookUp = new UsersRoleCompanyLookup();

                    UserRole userRole = new UserRole();
                    userRole.setUserRoleId((Integer) roles.get(i));
                    usersRoleCompanyLookUp.setUserId(user);
                    usersRoleCompanyLookUp.setRoleId(userRole);
                    usersRoleCompanyLookUp.setCompanyId(company);
                    usersRoleCompanyLookUp.setAccountStatus(AppConstants.Account_Activated);
                    boolean isRole = usersRoleCompanyLookUpService.isRoleExist(usersRoleCompanyLookUp);
                    if (isRole) {
                        usersRoleLookUpDao.save(usersRoleCompanyLookUp);
                        if (userId != 0) {
                            returnMessage = userId;
                        } else {
                            returnMessage = user.getUserId();
                        }
                    } else {
                        throw new ProcessFailed(messageSource.getMessage("role_exist", new String[]{}, Locale.US));
                    }
                }
                sendAcknowledgementEmail(usersDetails.getUserName(), usersDetails);

            } else {
                throw new ProcessFailed(messageSource.getMessage("validity_expired", new String[]{}, Locale.US));
            }

        } catch (Throwable throwable) {
            returnMessage = 0;
            logger.log(Priority.ERROR, throwable);
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        } finally {
            companyInvite = null;
            taskdetails = null;
            roles = null;
            usersRoleCompanyLookUp = null;
        }
        return returnMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveNonExistingUser(InviteDetails inviteDetails) throws ProcessFailed {
        boolean returnMessage = false;
        boolean userExist = false;
        UsersRoleCompanyLookup usersRoleCompanyLookUp = null;
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            String fromEmailId = userProfile.getUser().getUserName();

            Users user = isUserExist(inviteDetails.getEmailaddress());
            if (user != null) {
                userExist = checkUniqueUser(user);
            }
            ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
            String contextRealPath = servletContext.getRealPath("");

            String contextPath = Utility.getServerName(contextRealPath);
            if (user != null) {
                usersInviteService.sendMail(fromEmailId, contextPath, inviteDetails, AppConstants.User_Status_Existing);
                returnMessage = true;
            } else {
                usersInviteService.sendMail(fromEmailId, contextPath, inviteDetails, AppConstants.User_Status_New);
                returnMessage = true;
            }
//            if (userExist){
//                usersRoleCompanyLookUp = usersRoleCompanyLookUpService.getUsersRoleLookupByUser(userProfile.getUser());
//                boolean userExistInCompany = isUserExistInCompany(inviteDetails,usersRoleCompanyLookUp.getCompanyId());
//                if (userExistInCompany){
//                    setRole(inviteDetails);
//                    throw new ProcessFailed(messageSource.getMessage("user_exist_role_assigned", new String[]{}, Locale.US));
//                }else {
//                    throw new ProcessFailed(messageSource.getMessage("user_does_not_exist_in_company", new String[]{}, Locale.US));
//                }    
//            }else if(!userExist) {
//                ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
//                String contextRealPath = servletContext.getRealPath("");
//
//                String contextPath = Utility.getServerName(contextRealPath);
//                
//                usersInviteService.sendMail(fromEmailId, contextPath, inviteDetails);
//                returnMessage = true;
//            }
        } catch (Throwable throwable) {
            throw new ProcessFailed(messageSource.getMessage("user_exist", new String[]{}, Locale.US));
        }
        return returnMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(InviteDetails inviteDetails) throws ProcessFailed {
        TaskDetails taskdetails = null;
        List<Integer> roles = null;
        UsersRoleCompanyLookup usersRoleLookUp = null;
        Users user = null;
        try {

            taskdetails = new TaskDetails(inviteDetails.getTask(), inviteDetails.getRoles());

            user = findByUserName(inviteDetails.getEmailaddress());
            roles = taskdetails.getRoles();
            for (int i = 0; i < roles.size(); i++) {

                usersRoleLookUp = new UsersRoleCompanyLookup();
                Company company = companyDao.getCompanyById(inviteDetails.getCompanyId());
                UserRole userRole = new UserRole();
                userRole.setUserRoleId((Integer) roles.get(i));

                usersRoleLookUp.setUserId(user);
                usersRoleLookUp.setRoleId(userRole);
                usersRoleLookUp.setCompanyId(company);
                usersRoleLookUp.setAccountStatus(AppConstants.Account_Activated);
                usersRoleCompanyLookUpService.save(usersRoleLookUp);

            }

        } catch (Exception exception) {
            logger.error(exception);
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        } finally {
            roles = null;
            taskdetails = null;
            usersRoleLookUp = null;
            user = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateRole(InviteDetails inviteDetails) throws ProcessFailed {
        TaskDetails taskDetails = null, taskDetailsToSave = null;
        List<Integer> roles = null;
        UsersRoleCompanyLookup usersRoleLookUp = null;
        boolean flag = false;
        Users user = null;
        try {

            taskDetails = new TaskDetails(inviteDetails.getTask(), inviteDetails.getRoles());

            user = findByUserName(inviteDetails.getEmailaddress());
            roles = taskDetails.getRoles();
            String UserRoleLookUpIds[] = inviteDetails.getUserRoleLookUpId().split(",");
            for (int i = 0; i < roles.size(); i++) {

                usersRoleLookUp = new UsersRoleCompanyLookup();

                UserRole userRole = new UserRole();
                Company company = companyDao.getCompanyById(inviteDetails.getCompanyId());
                userRole.setUserRoleId((Integer) roles.get(i));

                usersRoleLookUp.setId(Integer.parseInt(UserRoleLookUpIds[i]));
                usersRoleLookUp.setUserId(user);
                usersRoleLookUp.setRoleId(userRole);
                usersRoleLookUp.setCompanyId(company);
                usersRoleLookUp.setAccountStatus(AppConstants.Account_Activated);
                usersRoleCompanyLookUpService.update(usersRoleLookUp);

            }
            Users userTo = findByUserName(inviteDetails.emailaddress);
            Invite invite = usersInviteService.getInvitedUserById(inviteDetails.getInviteId());

            invite.setTask(StringUtility.objectListToJsonString(taskDetails));
            usersInviteService.update(invite);
            flag = true;
        } catch (Throwable throwable) {
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
        if (user == null) {
            throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
        }
        return user;
    }

    @Override
    public Users getUserByEmailId(String emailId) throws ProcessFailed {
        Users user = usersDao.getUserByEmailId(emailId);
        if (user == null) {
            throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
        }
        return user;
    }

    @Override
    public boolean isInviteCodeValid(String inviteCode) throws ProcessFailed {
        boolean status = false;
        try {
            Invite companyInvite = usersInviteService.getInvitedUserByInviteCode(inviteCode);

            Long createdDate = companyInvite.getCreatedDateTime().getTime();
            Long todayDate = new Date().getTime();

            Long Datedifference = todayDate - createdDate;

            boolean isUsed = companyInvite.getIsUsed();
            if ((Datedifference <= AppConstants.Datedifference) && (isUsed == false)) {
                status = true;
            } else {
                status = false;
            }

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("problem_checking", new String[]{}, Locale.US));
        }
        return status;
    }

    public void sendAcknowledgementEmail(String toEmailId, UserDetails usersDetails) throws ProcessFailed {
        try {
            String companyName = messageSource.getMessage("companyName", new String[]{}, Locale.US);
            String body = messageSource.getMessage("acknowledgement_message", new String[]{}, Locale.US);
            String formattedBody = String.format(body);
            Content content = new Content(IConstants.kContentHTML, formattedBody);
            Email emailTo = new Email(toEmailId, Utility.combineUserName(usersDetails));
            String subject = messageSource.getMessage("acknowledgement_subject", new String[]{}, Locale.US);
            String formattedSubject = String.format(subject, companyName);
            Mail mail = new Mail(null, formattedSubject, emailTo, content);
            emailServiceProviderService.sendEmail(mail, EmailType.BrndBot_NoReply, 0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem", new String[]{}, Locale.US));
        }

    }
}
