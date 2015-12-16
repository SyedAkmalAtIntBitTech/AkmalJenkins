/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.IConstants;
import com.controller.SocialPostScheduler;
import com.divtohtml.StringUtil;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledEntityList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EmailInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static social.controller.ScheduleTwitterPost.logger;
import util.DateTimeUtil;
import util.Utility;

/**
 *
 * @author Ajit
 */
public class ScheduleAnRecuringEmail implements Runnable {

    public void terminateThread() {
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {

        try {
            List<TblScheduledEntityList> scheduledAnRecuringEmail = getLatestApprovedSendEmail();
            for (TblScheduledEntityList currentScheduledRecuringEmail : scheduledAnRecuringEmail) {
                if (scheduledAnRecuringEmail != null) {
//                boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledRecuringEmail.getScheduleTime());
                    boolean shouldPostNow = true;

                    if (shouldPostNow) {
                        TblScheduledEmailList sendAnEmail = getSendEmail(currentScheduledRecuringEmail);
                        String html_text = sendAnEmail.getBody();
                        String email_subject = sendAnEmail.getSubject();

                        String emaillist_name = sendAnEmail.getEmailListName();
                        Integer user_id = currentScheduledRecuringEmail.getUserId();
                        String reply_to_address = sendAnEmail.getReplyToEmailAddress();
                        String from_email_address = sendAnEmail.getFromAddress();
                        String from_name = sendAnEmail.getFromName();
                        SendAnEmail anEmail = new SendAnEmail();
                        String message = "";
                        Integer days = currentScheduledRecuringEmail.getDays();
                        JSONArray jsonArray = anEmail.getAllEmailAddressesForEmailList(user_id, days, emaillist_name);

                        for (int i = 0; i < jsonArray.size(); i++) {

                            EmailInfo emailInfo = (EmailInfo) jsonArray.get(i);

                            html_text = html_text.replace(IConstants.kEmailClientFirstName, emailInfo.getFirstName());
                            html_text = html_text.replace(IConstants.kEmailClientFirstName.toLowerCase(), emailInfo.getFirstName());

                            html_text = html_text.replace(IConstants.kEmailClientLastName, emailInfo.getLastName());
                            html_text = html_text.replace(IConstants.kEmailClientLastName.toLowerCase(), emailInfo.getLastName());

                            html_text = html_text.replace(IConstants.kEmailClientFullName, Utility.getFullName(emailInfo));
                            html_text = html_text.replace(IConstants.kEmailClientFullName.toLowerCase(), Utility.getFullName(emailInfo));

                            message = SendAnEmail.sendEmail(html_text, email_subject,
                                    emailInfo.getEmailAddress(), emaillist_name, user_id, reply_to_address, from_email_address, from_name, Utility.getFullName(emailInfo));
                        }
//                      String message = "success";//TODO
                        if (message.equalsIgnoreCase("success")) {
                            updateStatusScheduledEmail(currentScheduledRecuringEmail);
                        }
                    }
                }
            }
        } catch (Throwable ex) {
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateStatusScheduledEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        logger.log(Level.INFO, "RecurringEmail post:" + scheduledAnEmail.getScheduleTitle() + "Id:" + scheduledAnEmail.getId() + " time:" + scheduledAnEmail.getScheduleTime().toString());
        //Call the DAO here
        scheduledAnEmail.setStatus(IConstants.kSocialPostapprovedStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledAnEmail);
    }

    private TblScheduledEmailList getSendEmail(TblScheduledEntityList scheduledAnEmail) throws Throwable {
        TblScheduledEmailList scheduledEmailList = SchedulerUtilityMethods.getEmailEntityById(scheduledAnEmail.getEntityId());
        return scheduledEmailList;
    }

    private List<TblScheduledEntityList> getLatestApprovedSendEmail() throws Throwable {
        ArrayList<String> entityId = SchedulerUtilityMethods.getLatestEmailApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kEmailKey, IConstants.kUserMarketingProgramOpenStatus, Boolean.TRUE);
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
