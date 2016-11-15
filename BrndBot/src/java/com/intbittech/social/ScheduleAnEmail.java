/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.social;

import com.intbittech.component.SpringContextBridge;
import com.intbittech.enums.EmailTypeConstants;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.EmailListTagLookup;
import com.intbittech.model.PushedScheduledActionCompanies;
import com.intbittech.model.PushedScheduledEntityList;
import com.intbittech.utility.IConstants;
import com.intbittech.model.ScheduledEmailList;
import com.intbittech.model.ScheduledEntityList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.intbittech.utility.DateTimeUtil;
import com.intbittech.modelmappers.EmailDataDetails;
import com.intbittech.modelmappers.EmailSettings;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.EmailListTagLookupService;
import com.intbittech.services.EmailListTagService;
import com.intbittech.services.PushedScheduledActionCompaniesService;
import com.intbittech.services.PushedScheduledEntityListService;
import com.intbittech.services.SendEmailService;
import com.intbittech.utility.MarketingProgramUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

/**
 *
 * @author Ajit
 */
public class ScheduleAnEmail implements Runnable {

    private Logger logger = Logger.getLogger(ScheduleAnRecurringEmail.class);

    public void terminateThread() {
        try {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    @Override
    public void run() {
        //Adding tens mins if there are no latest approved posts
        PushedScheduledEntityListService pushedScheduleEntityListService = SpringContextBridge.services().getPushedScheduledEntityListService();
        PushedScheduledActionCompaniesService pushedScheduledActionCompaniesService = SpringContextBridge.services().getPushedScheduledActionCompaniesService();
        EmailListTagService emailListTagService = SpringContextBridge.services().getEmailListTagService();
        EmailListTagLookupService emailListTagLookupService = SpringContextBridge.services().getEmailListTagLookupService();
        CompanyPreferencesService companyPreferencesService = SpringContextBridge.services().getCompanyPreferencesService();
        logger.info("In Email Schedule CallBlock");
        String emaillist_name = "";
        String replyToAddress = "";
        String fromEmailAddress = "";
        String message = "";
        String fromName = "";

        try {
            List<ScheduledEntityList> scheduledAnEmail = getLatestApprovedSendEmail();

            //The below table should be reused or needs a new table specifically for FB.
            for (ScheduledEntityList currentScheduledEmail : scheduledAnEmail) {
                if (scheduledAnEmail != null) {
                    //The time zone of the saved date should be extracted.
                    //This time zone should be applied to the current time and then this comparison needs to be made.
                    boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledEmail.getScheduleTime(), currentScheduledEmail.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId());
//                    boolean shouldPostNow = true;
                    logger.info("Message to display entity id " + currentScheduledEmail.getEntityId() + " and schedule time:" + currentScheduledEmail.getScheduleTime());
                    logger.info("Current time:" + new Date());
                    if (shouldPostNow) {
                        logger.info("Should post now is true: Sending Mail");
                        ScheduledEmailList sendAnEmail = getSendEmail(currentScheduledEmail);

                        Integer companyId = currentScheduledEmail.getFkCompanyId().getCompanyId();
                        String html_text = sendAnEmail.getBody();
                        String email_subject = sendAnEmail.getSubject();
                        
                        List<PushedScheduledEntityList> pushedScheduleEntityList = pushedScheduleEntityListService.getAllPushedScheduledEntityListIdByScheduledEntityListId(currentScheduledEmail.getScheduledEntityListId());
                        if (pushedScheduleEntityList != null) {
                            List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesService.getAllPushedScheduledActionCompaniesByScheduledEntityListId(currentScheduledEmail.getScheduledEntityListId());
                            for (int i = 0; i < pushedScheduledActionCompaniesList.size(); i++) {
                                PushedScheduledActionCompanies pushedScheduleActionCompanies = pushedScheduledActionCompaniesList.get(i);
                                Company company = pushedScheduleActionCompanies.getFkCompanyId();

                                EmailListTag emailListTag = emailListTagService.getByTagName(sendAnEmail.getEmailListName());
                                EmailListTagLookup emailListTagLookup = null;
                                try {
                                    emailListTagLookup = emailListTagLookupService.getByEmailListTagLookupByEmailListIdAndCompanyId(emailListTag.getTagId(), company.getCompanyId());
                                } catch (Throwable throwable) {

                                }

                                EmailSettings emailSettings = null;
                                try {
                                    emailSettings = companyPreferencesService.getEmailSettings(company);
                                } catch (Throwable throwable) {

                                }
                                if ((emailSettings != null) && (emailListTagLookup != null)) {
                                    //Do something
                                    emaillist_name = emailListTagLookup.getFkEmailListId().getEmailListName();

                                    replyToAddress = emailSettings.getReplyEmailAddress();
                                    fromEmailAddress = emailSettings.getFromAddress();
                                    message = "";
                                    fromName = emailSettings.getFromName();

                                    EmailDataDetails emailDataDetails = new EmailDataDetails();
                                    emailDataDetails.setCompanyId(company.getCompanyId());
                                    emailDataDetails.setEmailListName(emaillist_name);
                                    emailDataDetails.setEmailSubject(email_subject);
                                    emailDataDetails.setFromEmailAddress(fromEmailAddress);
                                    emailDataDetails.setFromName(fromName);
                                    emailDataDetails.setHtmlData(html_text);
                                    emailDataDetails.setReplyToEmailAddress(replyToAddress);
                                    emailDataDetails.setEmailType(EmailTypeConstants.General.name());

                                    //For email categories/tags
                                    Integer companyMarketingProgramId = currentScheduledEmail.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId();
                                    Integer entityId = currentScheduledEmail.getEntityId();
                                    List<String> emailCategoryList = new ArrayList<>();
                                    emailCategoryList.add(MarketingProgramUtility.getMarketingProgramCategory(companyMarketingProgramId));
                                    emailCategoryList.add(MarketingProgramUtility.getMarketingProgramActionCategory(currentScheduledEmail.getScheduledEntityListId()));

                                    emailDataDetails.setEmailCategoryList(emailCategoryList);

                                    SendEmailService sendEmailService = SpringContextBridge.services().getSendEmailService();
                                    sendEmailService.sendMail(emailDataDetails, true);
                                    pushedScheduleActionCompanies.setStatus(IConstants.ACTION_COMPANIES_SENT_STATUS);
                                    pushedScheduledActionCompaniesService.update(pushedScheduleActionCompanies);

                                }

                            }

                        } else {
                            emaillist_name = sendAnEmail.getEmailListName();
                            replyToAddress = sendAnEmail.getReplyToEmailAddress();
                            fromEmailAddress = sendAnEmail.getFromAddress();
                            message = "";
                            fromName = sendAnEmail.getFromName();
                            EmailDataDetails emailDataDetails = new EmailDataDetails();
                            emailDataDetails.setCompanyId(companyId);
                            emailDataDetails.setEmailListName(emaillist_name);
                            emailDataDetails.setEmailSubject(email_subject);
                            emailDataDetails.setFromEmailAddress(fromEmailAddress);
                            emailDataDetails.setFromName(fromName);
                            emailDataDetails.setHtmlData(html_text);
                            emailDataDetails.setReplyToEmailAddress(replyToAddress);
                            emailDataDetails.setEmailType(EmailTypeConstants.General.name());

                            //For email categories/tags
                            Integer companyMarketingProgramId = currentScheduledEmail.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId();
                            Integer entityId = currentScheduledEmail.getEntityId();
                            List<String> emailCategoryList = new ArrayList<>();
                            emailCategoryList.add(MarketingProgramUtility.getMarketingProgramCategory(companyMarketingProgramId));
                            emailCategoryList.add(MarketingProgramUtility.getMarketingProgramActionCategory(currentScheduledEmail.getScheduledEntityListId()));

                            emailDataDetails.setEmailCategoryList(emailCategoryList);

                            SendEmailService sendEmailService = SpringContextBridge.services().getSendEmailService();
                            sendEmailService.sendMail(emailDataDetails, true);
                        }
                        updateStatusScheduledEmail(currentScheduledEmail);
                        logger.info("Should post now is true: Sent the mail");
                        //Get the next in line
                    }
                } else {
                    logger.info("Should post now is false: Not sending mail");
                }
            }

        } catch (Throwable ex) {
            logger.error(ex);
        }
        logger.info("In Email Schedule CallBlock End");

    }

    private void updateStatusScheduledEmail(ScheduledEntityList scheduledAnEmail) throws Throwable {
        logger.info("Email post:" + scheduledAnEmail.getScheduleTitle() + "Id:" + scheduledAnEmail.getScheduledEntityListId() + " time:" + scheduledAnEmail.getScheduleTime().toString());
        //Call the DAO here
        scheduledAnEmail.setStatus(IConstants.kSocialPostCommpleteStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledAnEmail);
//        ApplicationContextListener.refreshEmailScheduler();
    }

    private ScheduledEmailList getSendEmail(ScheduledEntityList scheduledAnEmail) throws Throwable {
        ScheduledEmailList scheduledEmailList = SchedulerUtilityMethods.getEmailEntityById(scheduledAnEmail.getEntityId());
        return scheduledEmailList;
    }

    private List<ScheduledEntityList> getLatestApprovedSendEmail() throws Throwable {
        ArrayList<String> entityId = SchedulerUtilityMethods.getLatestEmailApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kEmailKey, IConstants.kUserMarketingProgramOpenStatus, Boolean.FALSE);
        List<ScheduledEntityList> scheduledEntityList = new ArrayList<ScheduledEntityList>();
        if (entityId.size() > 0) {
            for (String currentEntityId : entityId) {
                ScheduledEntityList tblScheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(currentEntityId), IConstants.kEmailKey);
                scheduledEntityList.add(tblScheduledEntityList);
            }
        }
        return scheduledEntityList;
    }

}
