/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.ApplicationContextListener;
import com.controller.GenerateHashPassword;
import com.intbittech.AppConstants;
import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.dao.UsersInviteDao;
import com.intbittech.enums.AdminStatus;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.Invite;
import com.intbittech.model.InvitedUsers;
import com.intbittech.model.UserProfile;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.modelmappers.InviteDetails;
import com.intbittech.modelmappers.TaskDetails;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.UserRoleCompanyLookUpService;
import com.intbittech.services.UserRoleService;
import com.intbittech.services.UsersInviteService;
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
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
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
public class UsersInviteServiceImpl implements UsersInviteService {

    private final static Logger logger = Logger.getLogger(UsersInviteServiceImpl.class);

    @Autowired
    private UsersInviteDao usersInviteDao;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private UserRoleCompanyLookUpService userRoleCompanyLookUpService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UsersService usersService;
    @Autowired
    private EmailServiceProviderService emailServiceProviderService;

    @Override
    public String save(Invite companyInvite) throws ProcessFailed {
        String returnMessage = "problem saving the record";
        try {
            usersInviteDao.save(companyInvite);

            returnMessage = "saved successfully";
        } catch (Throwable throwable) {
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
        if (companyInvite == null) {
            throw new ProcessFailed("No user found with id " + Id + ".");
        }
        usersInviteDao.delete(companyInvite);
    }

    @Override
    public List<Invite> getAllInvitedUsers(Users userFrom) throws ProcessFailed {
        List<Invite> invites = usersInviteDao.getAllInvitedUsersByuserFrom(userFrom);
        if (invites == null) {
            throw new ProcessFailed(messageSource.getMessage("user_list_not_found", new String[]{}, Locale.US));
        }
        return invites;

    }

    @Override
    public Invite getInvitedUserByUserTo(Users userTo) throws ProcessFailed {
        Invite invites = usersInviteDao.getInvitedUserByUserTo(userTo);
        if (invites == null) {
            throw new ProcessFailed(messageSource.getMessage("user_list_not_found", new String[]{}, Locale.US));
        }
        return invites;

    }

    @Override
    public Invite getInvitedUserById(Integer Id) throws ProcessFailed {
        Invite companyInvite = usersInviteDao.getInvitedUserById(Id);
        if (companyInvite == null) {
            throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
        }
        return companyInvite;
    }

    @Override
    public Invite getInvitedUserByInviteCode(String inviteCode) throws ProcessFailed {
        Invite companyInvite = usersInviteDao.getUserByInviteCode(inviteCode);
        if (companyInvite == null) {
            throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
        }
        return companyInvite;
    }

    @Override
    public boolean removeUsersByInviteIdAndCompanyId(Integer inviteId, Integer companyId) throws ProcessFailed {
        boolean returnMessage = false;
        try {
            Invite companyInvite = usersInviteDao.getInvitedUserById(inviteId);

            boolean isUsed = companyInvite.getIsUsed();
            if (isUsed) {

                Company company = companyDao.getCompanyById(companyId);
                List<UsersRoleCompanyLookup> usersRoleCompanyLookupList = userRoleCompanyLookUpService.getAllUserRolesByUser(companyInvite.getInviteSentTo());
                List<Invite> inviteList = usersInviteDao.getAllInvitedUsersByuserTo(companyInvite.getInviteSentTo());
                if (usersRoleCompanyLookupList.size() == 1 && inviteList.size() == 1) {

                    usersDao.delete(companyInvite.getInviteSentTo());
                    returnMessage = true;
                } else {
                    UsersRoleCompanyLookup usersRoleCompanyLookup = userRoleCompanyLookUpService.getUsersRoleLookupByUserAndCompany(companyInvite.getInviteSentTo(), company);

                    userRoleCompanyLookUpService.delete(usersRoleCompanyLookup.getId());
                    delete(inviteId);
                    returnMessage = true;
                }
            } else {
                delete(inviteId);
                returnMessage = true;
            }
        } catch (Throwable throwable) {
            throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
        }
        return returnMessage;
    }

    @Override
    public boolean deleteUserByUserId(Integer userId) throws ProcessFailed {
        boolean returnMessage = false;
        try {
            Users user = usersDao.getUserById(userId);
            usersDao.delete(user);
            returnMessage = true;
        } catch (Throwable throwable) {
            throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
        }
        return returnMessage;
    }

    @Override
    public List<InvitedUsers> getInvitedUsers(Users userFrom, Company company) throws ProcessFailed {
        String invitationStatus = null;
        InvitedUsers inviteduser = null;
        UsersRoleCompanyLookup userRoleLookUp = null;
        String userName = "";
        try {
            List<Invite> invites = getAllInvitedUsers(userFrom);

            List<InvitedUsers> invitedUsers = new ArrayList<InvitedUsers>();
            if (invites != null) {
                List<Invite> invitesList = new ArrayList<Invite>();
                invitesList = invites;

                for (int i = 0; i < invitesList.size(); i++) {
                    Invite invite = invitesList.get(i);
                    Users user = invite.getInviteSentTo();
                    if (user != null) {
                        userName = user.getUserName();
                    } else {
                        userName = invite.getInviteSentToEmailId();
                    }
                    JSONObject task = (JSONObject) StringUtility.stringToJSONObject(invite.getTask());
                    ArrayList roles = (ArrayList) task.get("roles");
                    String userRoleName = "", userRoleLookUpIds = "";
                    for (int j = 0; j < roles.size(); j++) {
                        Double role_id = (Double) roles.get(j);
                        UserRole userRole = userRoleService.getUserRoleById(role_id.intValue());
                        userRoleLookUp = userRoleCompanyLookUpService.getUsersRoleLookupByUserAndCompany(user, company);
                        if (userRoleLookUp != null) {
                            if (userRoleLookUpIds == "") {
                                userRoleLookUpIds = userRoleLookUp.getId().toString();
                            } else {
                                userRoleLookUpIds = userRoleLookUpIds + "," + userRoleLookUp.getId().toString();
                            }
                        }
                        if (userRoleName == "") {
                            userRoleName = AdminStatus.valueOf(userRole.getRoleName()).getDisplayName();
                        } else {
                            userRoleName = userRoleName + "," + AdminStatus.valueOf(userRole.getRoleName()).getDisplayName();
                        }
                        boolean isUsed = invite.getIsUsed();
                        if (isUsed) {
                            invitationStatus = AppConstants.Invite_Accepted;
                        } else {
                            invitationStatus = AppConstants.Invite_Sent;
                        }
                    }
                    inviteduser = new InvitedUsers(invite.getId(), userRoleLookUpIds, userName, userRoleName, invitationStatus);
                    invitedUsers.add(inviteduser);

                }

                return invitedUsers;
            } else {
                throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
        }

    }

    @Override
    public boolean reSendInvitation(Integer inviteId) throws ProcessFailed {
        boolean returnMessage = false;
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            String fromEmailId = userProfile.getUser().getUserName();

            ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
            String contextRealPath = servletContext.getRealPath("");

            String contextPath = Utility.getServerName(contextRealPath);

            Invite companyInvite = getInvitedUserById(inviteId);
            Users userTo = usersService.isUserExist(companyInvite.getInviteSentToEmailId());

            if (userTo != null) {
                reSendMail(fromEmailId, contextPath, inviteId, AppConstants.User_Status_Existing);
            } else {
                reSendMail(fromEmailId, contextPath, inviteId, AppConstants.User_Status_New);
            }

            returnMessage = true;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("user_not_found", new String[]{}, Locale.US));
        }
        return returnMessage;
    }

    @Override
    public void reSendMail(String fromEmailId, String imageContextPath, Integer inviteId, String userStatus) throws ProcessFailed {
        try {

            Users user = usersService.getUserByEmailId(fromEmailId);
            Invite companyInvite = getInvitedUserById(inviteId);

            String randomVal = companyInvite.getInviteSentToEmailId() + String.valueOf(user.getUserId()) + new Date().getTime();
            GenerateHashPassword generate_hash_password = new GenerateHashPassword();

            String hashURL = generate_hash_password.hashURL(randomVal);

            companyInvite.setCreatedDateTime(new Date());
            companyInvite.setCode(hashURL);
            companyInvite.setIsUsed(Boolean.FALSE);
            update(companyInvite);

            sendInviteEmail(companyInvite.getInviteSentToEmailId(), user, imageContextPath, hashURL, userStatus);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem", new String[]{}, Locale.US));
        }

    }

    @Override
    public void sendMail(String fromEmailId, String imageContextPath, InviteDetails inviteDetails, String userStatus) throws ProcessFailed {
        try {

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
            TaskDetails taskdetails = new TaskDetails(inviteDetails.getTask(), inviteDetails.getRoles());
            companyInvite.setTask(StringUtility.objectListToJsonString(taskdetails));
            companyInvite.setInviteSentToEmailId(inviteDetails.getEmailaddress());
            save(companyInvite);

            sendInviteEmail(inviteDetails.getEmailaddress(), user, imageContextPath, hashURL, userStatus);
        } catch (Throwable throwable) {
             logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem", new String[]{}, Locale.US));
        }

    }

    private void sendInviteEmail(String emailaddress, Users user, String imageContextPath, String hashURL, String userStatus) {
        String companyName = messageSource.getMessage("companyName", new String[]{}, Locale.US);
        String body = "";
        if (userStatus.equals(AppConstants.User_Status_New)) {
            body = messageSource.getMessage("userInviteBodySignup", new String[]{}, Locale.US);
        } else if (userStatus.equals(AppConstants.User_Status_Existing)) {
            body = messageSource.getMessage("userInviteBodySignin", new String[]{}, Locale.US);
        }
        String formattedBody = String.format(body, companyName, imageContextPath, hashURL);
        Content content = new Content(IConstants.kContentHTML, formattedBody);
        Email emailTo = new Email(emailaddress, Utility.combineUserName(user));
        String subject = messageSource.getMessage("userInviteSubject", new String[]{}, Locale.US);
        String formattedSubject = String.format(subject, companyName);
        Mail mail = new Mail(null, formattedSubject, emailTo, content);
        emailServiceProviderService.sendEmail(mail, EmailType.BrndBot_NoReply);
    }
}
