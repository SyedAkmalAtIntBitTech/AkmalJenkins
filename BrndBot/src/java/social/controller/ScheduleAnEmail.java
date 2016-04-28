/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.IConstants;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledEntityList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import util.DateTimeUtil;

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
        logger.info("In Email Schedule CallBlock");

        try {
            List<TblScheduledEntityList> scheduledAnEmail = getLatestApprovedSendEmail();

            //The below table should be reused or needs a new table specifically for FB.
            for (TblScheduledEntityList currentScheduledEmail:scheduledAnEmail) {
                if (scheduledAnEmail != null) {
                //The time zone of the saved date should be extracted.
                    //This time zone should be applied to the current time and then this comparison needs to be made.
                  boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledEmail.getScheduleTime());
//                    boolean shouldPostNow = true;
                    logger.info("Message to display entity id " + currentScheduledEmail.getEntityId() + " and schedule time:"+ currentScheduledEmail.getScheduleTime());
                    logger.info("Current time:" + new Date());
                    if (shouldPostNow) {
                        logger.info("Should post now is true: Sending Mail");

                        TblScheduledEmailList sendAnEmail = getSendEmail(currentScheduledEmail);
                        String html_text = sendAnEmail.getBody();
                        String email_subject = sendAnEmail.getSubject();
                        String jsonString = sendAnEmail.getToEmailAddresses();
                        JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
                        String to_email_addresses = "";
                        String emaillist_name = sendAnEmail.getEmailListName();
                        Integer user_id = currentScheduledEmail.getUserId();
                        String reply_to_address = sendAnEmail.getReplyToEmailAddress();
                        String from_email_address = sendAnEmail.getFromAddress();
                        String message = "";
                        String from_name = sendAnEmail.getFromName();
                        org.json.simple.JSONArray jSONArray = (org.json.simple.JSONArray) json.get("emailAddresses");
                        for (Integer i = 0; i < jSONArray.size(); i++) {
                            to_email_addresses = jSONArray.get(i).toString();
                            message = SendAnEmail.sendEmail(html_text, email_subject, to_email_addresses, emaillist_name, user_id, reply_to_address, from_email_address, from_name, "");
                        }
//                    String message = "success";//TODO

                        if (message.equalsIgnoreCase("success")) {
                            updateStatusScheduledEmail(currentScheduledEmail);
                            logger.info("Should post now is true: Sent the mail");
                            //Get the next in line
                        }
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

    private void updateStatusScheduledEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        logger.info("Email post:" + scheduledAnEmail.getScheduleTitle() + "Id:" + scheduledAnEmail.getId() + " time:" + scheduledAnEmail.getScheduleTime().toString());
        //Call the DAO here
        scheduledAnEmail.setStatus(IConstants.kSocialPostCommpleteStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledAnEmail);
//        ApplicationContextListener.refreshEmailScheduler();
    }

    private TblScheduledEmailList getSendEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        TblScheduledEmailList scheduledEmailList = SchedulerUtilityMethods.getEmailEntityById(scheduledAnEmail.getEntityId());
        return scheduledEmailList;
    }

    private List<TblScheduledEntityList> getLatestApprovedSendEmail() throws Throwable {
        ArrayList<String> entityId = SchedulerUtilityMethods.getLatestEmailApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kEmailKey, IConstants.kUserMarketingProgramOpenStatus, Boolean.FALSE);
        List<TblScheduledEntityList> scheduledEntityList = new ArrayList<TblScheduledEntityList>();
        if (entityId.size() > 0) {
            for (String currentEntityId : entityId) {
                TblScheduledEntityList tblScheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(currentEntityId), IConstants.kEmailKey);
                scheduledEntityList.add(tblScheduledEntityList);
            }
        }
        return scheduledEntityList;
    }

}
