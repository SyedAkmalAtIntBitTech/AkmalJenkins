/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.social;

import com.intbittech.component.SpringContextBridge;
import com.intbittech.enums.EmailTypeConstants;
import com.intbittech.utility.IConstants;
import com.intbittech.model.ScheduledEmailList;
import com.intbittech.model.ScheduledEntityList;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.intbittech.utility.DateTimeUtil;
import com.intbittech.modelmappers.EmailDataDetails;
import com.intbittech.services.SendEmailService;
import com.intbittech.utility.MarketingProgramUtility;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;


/**
 *
 * @author Ajit
 */
public class ScheduleAnRecurringEmail implements Runnable {

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

        try {
            List<ScheduledEntityList> scheduledAnRecurringEmail = getLatestApprovedSendEmail();
            for (ScheduledEntityList currentScheduledRecurringEmail : scheduledAnRecurringEmail) {
                if (scheduledAnRecurringEmail != null) {
                    boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledRecurringEmail.getScheduleTime(),currentScheduledRecurringEmail.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId());
//                    boolean shouldPostNow = true;

                    if (shouldPostNow) {
                        ScheduledEmailList sendAnEmail = getSendEmail(currentScheduledRecurringEmail);
                        String html_text = sendAnEmail.getBody();
                        String email_subject = sendAnEmail.getSubject();

                        String emaillist_name = sendAnEmail.getEmailListName();
                        Integer companyId = currentScheduledRecurringEmail.getFkCompanyId().getCompanyId();
                        String reply_to_address = "";
                        if (sendAnEmail.getReplyToEmailAddress() != null) {
                            reply_to_address = sendAnEmail.getReplyToEmailAddress();
                        } else {
                            reply_to_address = "";
                        }
                        String from_email_address = "";
                        if (sendAnEmail.getFromAddress() != null) {
                            from_email_address = sendAnEmail.getFromAddress();
                        } else {
                            from_email_address = "mail@brndbot.com";
                        }
                        String from_name = sendAnEmail.getFromName();
//                        SendMail anEmail = new SendMail();
                        String message = "";
                        Integer days = currentScheduledRecurringEmail.getDays();
                        
                        EmailDataDetails emailDataDetails = new EmailDataDetails();
                        emailDataDetails.setCompanyId(companyId);
                        emailDataDetails.setEmailListName(emaillist_name);
                        emailDataDetails.setEmailSubject(email_subject);
                        emailDataDetails.setFromEmailAddress(from_email_address);
                        emailDataDetails.setFromName(from_name);
                        emailDataDetails.setHtmlData(html_text);
                        emailDataDetails.setReplyToEmailAddress(reply_to_address);
                        emailDataDetails.setEmailType(EmailTypeConstants.Recurring.name());
                        emailDataDetails.setDays(days);
                        
                        //For email categories/tags
                        Integer companyMarketingProgramId = currentScheduledRecurringEmail.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId();
                        Integer entityId = currentScheduledRecurringEmail.getEntityId();
                        List<String> emailCategoryList = new ArrayList<>();
                        emailCategoryList.add(MarketingProgramUtility.getMarketingProgramCategory(companyMarketingProgramId));
                        emailCategoryList.add(MarketingProgramUtility.getMarketingProgramRecuringActionCategory(currentScheduledRecurringEmail.getScheduledEntityListId()));
                        
                        emailDataDetails.setEmailCategoryList(emailCategoryList);
                        
                        SendEmailService sendEmailService = SpringContextBridge.services().getSendEmailService();
                        sendEmailService.sendMail(emailDataDetails, false);
                        
                        updateStatusScheduledEmail(currentScheduledRecurringEmail);
                    }
                }
            }
        } catch (Throwable ex) {
            logger.error(ex);
        }
    }

    private void updateStatusScheduledEmail(ScheduledEntityList scheduledAnEmail) throws Throwable {
        logger.info("RecurringEmail post:" + scheduledAnEmail.getScheduleTitle() + "Id:" + scheduledAnEmail.getScheduledEntityListId()+ " time:" + scheduledAnEmail.getScheduleTime().toString());
        //Call the DAO here
        scheduledAnEmail.setStatus(IConstants.kSocialPostapprovedStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledAnEmail);
    }

    private ScheduledEmailList getSendEmail(ScheduledEntityList scheduledAnEmail) throws Throwable {
        ScheduledEmailList scheduledEmailList = SchedulerUtilityMethods.getEmailEntityById(scheduledAnEmail.getEntityId());
        return scheduledEmailList;
    }

    private List<ScheduledEntityList> getLatestApprovedSendEmail() throws Throwable {
        ArrayList<String> entityId = SchedulerUtilityMethods.getLatestEmailApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kEmailKey, IConstants.kUserMarketingProgramOpenStatus, Boolean.TRUE);
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
