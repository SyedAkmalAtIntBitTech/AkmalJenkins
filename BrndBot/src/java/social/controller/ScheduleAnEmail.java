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
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledEntityListService;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import util.DateTimeUtil;

/**
 *
 * @author Ajit
 */
public class ScheduleAnEmail implements Callable {

    
    public void terminateThread() {
        Thread.currentThread().interrupt();
    }

    @Override
    public Date call() throws Exception {
        //Adding tens mins if there are no latest approved posts
        Date nextPostTime = DateTimeUtil.getDatePlusMins(SocialPostScheduler.DefaultPollingInterval);
        try {
            TblScheduledEntityList scheduledAnEmail = getLatestApprovedSendEmail();

            //The below table should be reused or needs a new table specifically for FB.
            if (scheduledAnEmail != null) {
                //The time zone of the saved date should be extracted.
                //This time zone should be applied to the current time and then this comparison needs to be made.
                boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledAnEmail.getScheduleTime());

                if (shouldPostNow) {
                    TblScheduledEmailList sendAnEmail = getSendEmail(scheduledAnEmail);
                    String html_text = sendAnEmail.getBody();
                    String email_subject = sendAnEmail.getSubject();
                    String jsonString = sendAnEmail.getToEmailAddresses();
                    JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
                    String to_email_addresses = "";
                    org.json.simple.JSONArray jSONArray = (org.json.simple.JSONArray) json.get("emailAddresses");
                    for (Integer i = 0; i < jSONArray.size(); i++) {
                        to_email_addresses += jSONArray.get(i).toString();
                        if ((i + 1) < jSONArray.size()) {
                            to_email_addresses += ",";
                        }
                    }
                    String emaillist_name = sendAnEmail.getEmailListName();
                    Integer user_id = getLatestApprovedSendEmail().getUserId();
                    String reply_to_address = sendAnEmail.getReplyToEmailAddress();
                    String from_email_address = sendAnEmail.getFromAddress();
                    String from_name = sendAnEmail.getFromName();

                    String message = SendAnEmail.sendEmail(html_text, email_subject, to_email_addresses, emaillist_name, user_id, reply_to_address, from_email_address, from_name);

                    if (message.equalsIgnoreCase("success")) {
                        updateStatusScheduledEmail(scheduledAnEmail);
                        //Get the next in line
                        scheduledAnEmail = getLatestApprovedSendEmail();
                    }
                }
                nextPostTime = scheduledAnEmail.getScheduleTime();
            }

        } catch (Throwable ex) {
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nextPostTime;
    }

    private void updateStatusScheduledEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        //Call the DAO here
        scheduledAnEmail.setStatus(IConstants.kSocialPostCommpleteStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledAnEmail);
        ApplicationContextListener.refreshEmailScheduler();
    }

    private TblScheduledEmailList getSendEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        TblScheduledEmailList scheduledEmailList = SchedulerUtilityMethods.getEmailEntityById(scheduledAnEmail.getEntityId());
        return scheduledEmailList;
    }

    private TblScheduledEntityList getLatestApprovedSendEmail() throws Throwable {
        String entityId = SchedulerUtilityMethods.getLatestEmailApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kEmailKey, IConstants.kUserMarketingProgramOpenStatus, Boolean.FALSE);
        TblScheduledEntityList scheduledEntityList = null;
        if (!StringUtil.isEmpty(entityId)) {
            scheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(entityId));
        }
        return scheduledEntityList;
    }

}
