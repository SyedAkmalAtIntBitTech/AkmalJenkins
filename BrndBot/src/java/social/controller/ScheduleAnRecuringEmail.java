/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.ApplicationContextListener;
import com.controller.IConstants;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledEntityListService;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import util.DateTimeUtil;

/**
 *
 * @author Ajit
 */
public class ScheduleAnRecuringEmail implements Callable {

    @Autowired
    ScheduledEntityListService scheduledEntityListService;
    @Autowired
    ScheduledEmailListService scheduledEmailListService;

    public void terminateThread() {
        Thread.currentThread().interrupt();
    }

    @Override
    public Date call() throws Exception {
        TblScheduledEntityList scheduledAnRecuringEmail = null;
        try {
            //The below table should be reused or needs a new table specifically for FB.
            scheduledAnRecuringEmail = getLatestApprovedSendEmail();

            //The time zone of the saved date should be extracted.
            //This time zone should be applied to the current time and then this comparison needs to be made.
            boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(getLatestApprovedSendEmail().getScheduleTime());

            if (shouldPostNow) {
                TblScheduledEmailList sendAnEmail = getSendEmail(scheduledAnRecuringEmail);
                String html_text = sendAnEmail.getBody();
                String email_subject = sendAnEmail.getSubject();

                String emaillist_name = sendAnEmail.getEmailListName();
                Integer user_id = getLatestApprovedSendEmail().getUserId();
                String reply_to_address = sendAnEmail.getReplyToEmailAddress();
                String from_email_address = sendAnEmail.getFromAddress();
                String from_name = sendAnEmail.getFromName();
                SendAnEmail anEmail = new SendAnEmail();
                // To do add days column in TblScheduledEntityList model class
                //and get days from TblScheduledEntityList
                Integer days = 10;
                String to_email_addresses = anEmail.getAllEmailAddressesForEmailList(user_id, days, emaillist_name);
                String message = SendAnEmail.sendEmail(html_text, email_subject, to_email_addresses, emaillist_name, user_id, reply_to_address, from_email_address, from_name);
                if (message.equalsIgnoreCase("success")) {
                    updateStatusScheduledEmail(scheduledAnRecuringEmail);
                    //Get the next in line
                    scheduledAnRecuringEmail = getLatestApprovedSendEmail();
                }
            }

        } catch (Throwable ex) {
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scheduledAnRecuringEmail.getScheduleTime();
    }

    private void updateStatusScheduledEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        //Call the DAO here
        scheduledAnEmail.setStatus(IConstants.kSocialPostCommpleteStatus);
        scheduledEntityListService.update(scheduledAnEmail);
        ApplicationContextListener.refreshEmailRecuringScheduler();
    }

    private TblScheduledEmailList getSendEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        TblScheduledEmailList scheduledEmailList = scheduledEmailListService.getById(scheduledAnEmail.getEntityId());
        return scheduledEmailList;
    }

    private TblScheduledEntityList getLatestApprovedSendEmail() throws Throwable {
        String entityId = scheduledEntityListService.getLatestApprovedEmail(IConstants.kSocialPostTemplateSavedStatus, IConstants.kEmailKey, IConstants.kUserMarketingProgramOpenStatus, Boolean.TRUE);
        TblScheduledEntityList scheduledEntityList = scheduledEntityListService.getScheduledEntityListByEntityId(Integer.parseInt(entityId));
        return scheduledEntityList;
    }

}