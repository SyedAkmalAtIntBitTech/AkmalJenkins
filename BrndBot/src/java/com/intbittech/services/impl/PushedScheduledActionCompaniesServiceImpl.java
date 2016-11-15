/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.PushedScheduledActionCompaniesDao;
import com.intbittech.dao.PushedScheduledEntityListDao;
import com.intbittech.dao.UserRoleCompanyLookUpDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.Franchise;
import com.intbittech.model.PushedScheduledActionCompanies;
import com.intbittech.model.PushedScheduledEntityList;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.modelmappers.ActionCompaniesDetails;
import com.intbittech.modelmappers.PushedScheduledActionCompaniesDetails;
import com.intbittech.modelmappers.SendReminderEmailDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.services.EmailListTagService;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.FranchiseService;
import com.intbittech.services.PushedScheduledActionCompaniesService;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.Utility;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class PushedScheduledActionCompaniesServiceImpl implements PushedScheduledActionCompaniesService {

    private static Logger logger = Logger.getLogger(PushedScheduledActionCompaniesServiceImpl.class);
    @Autowired
    private PushedScheduledActionCompaniesDao pushedScheduledActionCompaniesDao;
    @Autowired
    private PushedScheduledEntityListDao pushedScheduledEntityListDao;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserRoleCompanyLookUpDao roleCompanyLookUpDao;
    @Autowired
    private FranchiseService franchiseService;
    @Autowired
    private EmailServiceProviderService emailServiceProviderService;
    @Autowired
    private EmailListTagService emailListTagService;
    @Autowired
    private UsersDao usersDao;
    /**
     * {@inheritDoc}
     */
    public PushedScheduledActionCompanies getByPushedScheduledActionCompaniesId(Integer pushedScheduledActionCompaniesId) throws ProcessFailed {
        PushedScheduledActionCompanies pushedScheduledActionCompanies = pushedScheduledActionCompaniesDao.getByPushedScheduledActionCompaniesId(pushedScheduledActionCompaniesId);
        if (pushedScheduledActionCompanies == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found", new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompanies;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed {
        return pushedScheduledActionCompaniesDao.save(pushedScheduledActionCompanies);
    }

    /**
     * {@inheritDoc}
     */
    public void update(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed {
        pushedScheduledActionCompaniesDao.update(pushedScheduledActionCompanies);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer pushedScheduledActionCompaniesId) throws ProcessFailed {
        PushedScheduledActionCompanies pushedScheduledActionCompanies = pushedScheduledActionCompaniesDao.getByPushedScheduledActionCompaniesId(pushedScheduledActionCompaniesId);
        if (pushedScheduledActionCompanies == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found", new String[]{}, Locale.US));
        }
        pushedScheduledActionCompaniesDao.delete(pushedScheduledActionCompanies);
    }

    /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByScheduledEntityListId(Integer ScheduledEntityListId) throws ProcessFailed {
        List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesDao.getAllPushedScheduledActionCompaniesByScheduledEntityListId(ScheduledEntityListId);
        if (pushedScheduledActionCompaniesList == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found", new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompaniesList;
    }

    /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByFranchiseId(Integer franchiseId) throws ProcessFailed {
        List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesDao.getAllPushedScheduledActionCompaniesByFranchiseId(franchiseId);
        if (pushedScheduledActionCompaniesList == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found", new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompaniesList;
    }

    /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByCompanyId(Integer companyId) throws ProcessFailed {
        List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesDao.getAllPushedScheduledActionCompaniesByCompanyId(companyId);
        if (pushedScheduledActionCompaniesList == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found", new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompaniesList;
    }

    /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByCompanyIdAndDateDifference(Integer companyId, Timestamp fromDate, Timestamp toDate) throws ProcessFailed {
        List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesDao.getAllPushedScheduledActionCompaniesByCompanyIdAndDateDifference(companyId,fromDate, toDate);
        if (pushedScheduledActionCompaniesList == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found", new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompaniesList;
    }
    
    /**
     * {@inheritDoc}
     */
    public void savePushedScheduledActionCompanies(PushedScheduledActionCompaniesDetails pushedScheduledActionCompaniesDetails) throws ProcessFailed {
        PushedScheduledEntityList pushedScheduledEntityList = new PushedScheduledEntityList();

        ScheduledEntityList scheduledEntityList = new ScheduledEntityList();
        scheduledEntityList.setScheduledEntityListId(pushedScheduledActionCompaniesDetails.getPushedScheduledEntityDetails().getScheduledEntityListId());
        pushedScheduledEntityList.setFkScheduledEntityListId(scheduledEntityList);

        Franchise franchise = new Franchise();
        franchise.setFranchiseId(pushedScheduledActionCompaniesDetails.getPushedScheduledEntityDetails().getFranchiseId());
        pushedScheduledEntityList.setFkFranchiseId(franchise);
        pushedScheduledEntityList.setAutoApproved(pushedScheduledActionCompaniesDetails.getPushedScheduledEntityDetails().getAutoApproved());
        pushedScheduledEntityList.setEditable(pushedScheduledActionCompaniesDetails.getPushedScheduledEntityDetails().getEditable());
        Integer pushedScheduledEntityListId = pushedScheduledEntityListDao.save(pushedScheduledEntityList);

        List<ActionCompaniesDetails> actionCompaniesDetailsList = pushedScheduledActionCompaniesDetails.getActionCompaniesDetails();
        for (ActionCompaniesDetails actionCompaniesDetails : actionCompaniesDetailsList) {

            PushedScheduledActionCompanies pushedScheduledActionCompanies = new PushedScheduledActionCompanies();
            Company company = new Company();
            company.setCompanyId(actionCompaniesDetails.getCompanyId());
            pushedScheduledActionCompanies.setFkCompanyId(company);
            pushedScheduledEntityList = new PushedScheduledEntityList();
            pushedScheduledEntityList.setPushedScheduledEntityListId(pushedScheduledEntityListId);
            pushedScheduledActionCompanies.setFkPushedScheduledActionEntityListId(pushedScheduledEntityList);
            pushedScheduledActionCompanies.setUpdatedAt(new Date());
            if (franchiseService.isEmailListTagAssociateToCompany(pushedScheduledActionCompaniesDetails.getPushedScheduledEntityDetails().getEmailListTagId(), actionCompaniesDetails.getCompanyId())) {
                pushedScheduledActionCompanies.setStatus(IConstants.ACTION_COMPANIES_READY_TO_GO);
            } else {
                pushedScheduledActionCompanies.setStatus(IConstants.ACTION_COMPANIES_NO_EMAIL_TAG_CONFIGURED);
            }

            pushedScheduledActionCompaniesDao.save(pushedScheduledActionCompanies);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<UserDetails> getAllUserDetailsOfCompanyIdForSendEmail(SendReminderEmailDetails sendReminderEmailDetails) throws ProcessFailed {
        List<UsersRoleCompanyLookup> roleCompanyLookupList = roleCompanyLookUpDao.getAllUsersRoleCompanyLookupByuserRolesNameAndCompanyId(Utility.getAllUserRoleOfCompanyForNoTagEmailList(), sendReminderEmailDetails.getCompanyIds());
        if (roleCompanyLookupList == null) {
            throw new ProcessFailed("No user found");
        }
        List<UserDetails> userDetailsList = new ArrayList<>();
            
        Users fromUser = usersDao.getUserById(sendReminderEmailDetails.getUserId());
        
        for (UsersRoleCompanyLookup usersRoleCompanyLookup : roleCompanyLookupList) {
            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(usersRoleCompanyLookup.getUserId().getUserId());
            userDetails.setFirstName(usersRoleCompanyLookup.getUserId().getFirstName());
            userDetails.setLastName(usersRoleCompanyLookup.getUserId().getLastName());
            userDetails.setUserName(usersRoleCompanyLookup.getUserId().getUserName());
            EmailListTag emailListTag = emailListTagService.getByEmailListTagId(sendReminderEmailDetails.getEmailListTagId());
            sendNotificationEmailForNoEmailListPresent(usersRoleCompanyLookup.getUserId().getUserName(),
                    Utility.combineUserName(usersRoleCompanyLookup.getUserId()), emailListTag.getTagName(), 
                    usersRoleCompanyLookup.getCompanyId().getCompanyName(),usersRoleCompanyLookup.getCompanyId().getCompanyId(), fromUser.getUserName());
        }
        return userDetailsList;
    }

    public Boolean sendNotificationEmailForNoEmailListPresent(String toEmailId, String userName, String emailTag, String company,Integer companyId, String fromName) throws ProcessFailed {
        try {
            String companyName = messageSource.getMessage("companyName", new String[]{}, Locale.US);
            String body = messageSource.getMessage("notification_message_no_emaillist_tag", new String[]{}, Locale.US);
            String HTMLBody = body.replace("%s", emailTag + " in " + company);
            String formattedBody = String.format(HTMLBody);
            Content content = new Content(IConstants.kContentHTML, formattedBody);
            Email emailTo = new Email(toEmailId, userName);
            String subject = messageSource.getMessage("notification_subject_no_emailList", new String[]{}, Locale.US);
            String formattedSubject = String.format(subject, companyName);
            Mail mail = new Mail(null, formattedSubject, emailTo, content);
            emailServiceProviderService.sendEmail(mail, EmailType.BrndBot_NoReply, companyId, fromName);
            return true;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem", new String[]{}, Locale.US));
        }
    }

    @Override
    public List<PushedScheduledActionCompanies> getPushedScheduledActionCompaniesByScheduledEntityListIdAndStatus(Integer ScheduledEntityListId, String Status) throws ProcessFailed {
        List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesDao.getPushedScheduledActionCompaniesByScheduledEntityListIdAndStatus(ScheduledEntityListId, Status);
        if (pushedScheduledActionCompaniesList == null) {
            throw new ProcessFailed(messageSource.getMessage("no_pushed_scheduled_action_company_found", new String[]{}, Locale.US));
        }
        return pushedScheduledActionCompaniesList;
    }

}
