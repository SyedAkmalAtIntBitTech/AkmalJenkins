/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.IConstants;
import com.intbittech.model.ScheduledEmailList;
import com.intbittech.model.ScheduledEntityList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EmailInfo;
import org.json.simple.JSONArray;
import static social.controller.ScheduleTwitterPost.logger;
import util.DateTimeUtil;
import util.Utility;

/**
 *
 * @author Ajit
 */
public class ScheduleAnRecurringEmail implements Runnable {

    public void terminateThread() {
        try {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            Logger.getLogger(ScheduleAnRecurringEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        try {
            List<ScheduledEntityList> scheduledAnRecurringEmail = getLatestApprovedSendEmail();
            for (ScheduledEntityList currentScheduledRecurringEmail : scheduledAnRecurringEmail) {
                if (scheduledAnRecurringEmail != null) {
                    boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledRecurringEmail.getScheduleTime());
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
                        if (sendAnEmail.getReplyToEmailAddress() != null) {
                            from_email_address = sendAnEmail.getFromAddress();
                        } else {
                            from_email_address = "";
                        }
                        String from_name = sendAnEmail.getFromName();
                        SendAnEmail anEmail = new SendAnEmail();
                        String message = "";
                        Integer days = currentScheduledRecurringEmail.getDays();
                        JSONArray jsonArray = anEmail.getAllEmailAddressesForEmailList(companyId, days, emaillist_name);

                        for (int i = 0; i < jsonArray.size(); i++) {

                            EmailInfo emailInfo = (EmailInfo) jsonArray.get(i);

                            html_text = html_text.replace(IConstants.kEmailClientFirstName, emailInfo.getFirstName());
                            html_text = html_text.replace(IConstants.kEmailClientFirstName.toLowerCase(), emailInfo.getFirstName());

                            html_text = html_text.replace(IConstants.kEmailClientLastName, emailInfo.getLastName());
                            html_text = html_text.replace(IConstants.kEmailClientLastName.toLowerCase(), emailInfo.getLastName());

                            html_text = html_text.replace(IConstants.kEmailClientFullName, Utility.getFullName(emailInfo));
                            html_text = html_text.replace(IConstants.kEmailClientFullName.toLowerCase(), Utility.getFullName(emailInfo));

                            message = SendAnEmail.sendEmail(html_text, email_subject,
                                    emailInfo.getEmailAddress(), emaillist_name, companyId, reply_to_address, from_email_address, from_name, Utility.getFullName(emailInfo));
                        }
//                      String message = "success";//TODO
                        if (message.equalsIgnoreCase("success")) {
                            updateStatusScheduledEmail(currentScheduledRecurringEmail);
                        }
                    }
                }
            }
        } catch (Throwable ex) {
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateStatusScheduledEmail(ScheduledEntityList scheduledAnEmail) throws Throwable {
        logger.log(Level.INFO, "RecurringEmail post:" + scheduledAnEmail.getScheduleTitle() + "Id:" + scheduledAnEmail.getScheduledEntityListId() + " time:" + scheduledAnEmail.getScheduleTime().toString());
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
