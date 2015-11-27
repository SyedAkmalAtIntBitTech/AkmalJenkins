/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.ApplicationContextListener;
import com.controller.IConstants;
import com.controller.SocialPostScheduler;
import com.divtohtml.StringUtil;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledEntityList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import static social.controller.ScheduleTwitterPost.logger;
import util.DateTimeUtil;

/**
 *
 * @author Ajit
 */
public class ScheduleAnRecuringEmail implements Callable {

    public void terminateThread() {
        Thread.currentThread().interrupt();
    }

    @Override
    public Date call() throws Exception {
        Date nextPostTime = DateTimeUtil.getDatePlusMins(SocialPostScheduler.DefaultPollingInterval);
        try {
            TblScheduledEntityList scheduledAnRecuringEmail = getLatestApprovedSendEmail();

            if (scheduledAnRecuringEmail != null) {

                //The time zone of the saved date should be extracted.
                //This time zone should be applied to the current time and then this comparison needs to be made.
                boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledAnRecuringEmail.getScheduleTime());
//                boolean shouldPostNow = true;

                if (shouldPostNow) {
                    TblScheduledEmailList sendAnEmail = getSendEmail(scheduledAnRecuringEmail);
                    String html_text = sendAnEmail.getBody();
                    String email_subject = sendAnEmail.getSubject();

                    String emaillist_name = sendAnEmail.getEmailListName();
                    Integer user_id = scheduledAnRecuringEmail.getUserId();
                    String reply_to_address = sendAnEmail.getReplyToEmailAddress();
                    String from_email_address = sendAnEmail.getFromAddress();
                    String from_name = sendAnEmail.getFromName();
                    SendAnEmail anEmail = new SendAnEmail();
                    //and get days from TblScheduledEntityList
                    Integer days = scheduledAnRecuringEmail.getDays();
                    String to_email_addresses = anEmail.getAllEmailAddressesForEmailList(user_id, days, emaillist_name);
                    String message = SendAnEmail.sendEmail(html_text, email_subject, to_email_addresses, emaillist_name, user_id, reply_to_address, from_email_address, from_name);
//                    String message = "success";//TODO
                    if (message.equalsIgnoreCase("success")) {
                        updateStatusScheduledEmail(scheduledAnRecuringEmail);
                        //Get the next in line
                        scheduledAnRecuringEmail = getLatestApprovedSendEmail();
                    }
                }
                nextPostTime = scheduledAnRecuringEmail.getScheduleTime();
            }
        } catch (Throwable ex) {
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nextPostTime;
    }

    private void updateStatusScheduledEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        logger.log(Level.INFO, "RecurringEmail post:" + scheduledAnEmail.getScheduleTitle() + "Id:" + scheduledAnEmail.getId() + " time:" + scheduledAnEmail.getScheduleTime().toString());
        //Call the DAO here
        scheduledAnEmail.setStatus(IConstants.kSocialPostapprovedStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledAnEmail);
        ApplicationContextListener.refreshEmailRecuringScheduler();
    }

    private TblScheduledEmailList getSendEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        TblScheduledEmailList scheduledEmailList = SchedulerUtilityMethods.getEmailEntityById(scheduledAnEmail.getEntityId());
        return scheduledEmailList;
    }

    private TblScheduledEntityList getLatestApprovedSendEmail() throws Throwable {
        String entityId = SchedulerUtilityMethods.getLatestEmailApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kEmailKey, IConstants.kUserMarketingProgramOpenStatus, Boolean.TRUE);
        TblScheduledEntityList scheduledEntityList = null;
        if (!StringUtil.isEmpty(entityId)) {
            scheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(entityId));
        }
        return scheduledEntityList;
    }

}
