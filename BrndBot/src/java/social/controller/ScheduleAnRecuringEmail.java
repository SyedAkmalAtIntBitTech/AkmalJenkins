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
import org.json.simple.JSONObject;
import static social.controller.ScheduleTwitterPost.logger;
import util.DateTimeUtil;

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

                boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledRecuringEmail.getScheduleTime());
//                boolean shouldPostNow = true;

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
                    //and get days from TblScheduledEntityList
                    Integer days = currentScheduledRecuringEmail.getDays();
                    JSONObject json_object = anEmail.getAllEmailAddressesForEmailList(user_id, days, emaillist_name);

                    String to_email_addresses = json_object.get("emailAddresses").toString();
                    String firstName = json_object.get("firstName").toString();
                    String lastName = json_object.get("lastName").toString();
                    
                    boolean check_availability_firstName = html_text.contains("clientFirstName");
                    boolean check_availability_lastName = html_text.contains("clientLastName");
                    boolean check_availability_fullName = html_text.contains("clientFullName");

                    if (check_availability_firstName){
                        html_text.replace("clientFirstName", firstName);
                    }
                    if (check_availability_lastName){
                        html_text.replace("clientLastName", lastName);
                    }
                    if (check_availability_fullName){
                        html_text.replace("clientFullName", firstName + " " + lastName);
                    }
                    
                    String message = SendAnEmail.sendEmail(html_text, email_subject, to_email_addresses, emaillist_name, user_id, reply_to_address, from_email_address, from_name);
//                    String message = "success";//TODO
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
        if (entityId.size()>0) {
            for (String currentEntityId : entityId) {
            TblScheduledEntityList tblScheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(currentEntityId), IConstants.kEmailKey);
            scheduledEntityList.add(tblScheduledEntityList);
            }
        }
        return scheduledEntityList;
    }

}
