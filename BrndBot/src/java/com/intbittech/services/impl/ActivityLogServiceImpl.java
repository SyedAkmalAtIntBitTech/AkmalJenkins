/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.ApplicationContextListener;
import com.intbittech.dao.ActivityLogDao;
import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Activity;
import com.intbittech.model.ActivityLog;
import com.intbittech.model.Company;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.ActivityLogDetails;
import com.intbittech.responsemappers.ActivityLogResponse;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.services.ActivityLogService;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.ServletUtil;
import com.intbittech.utility.Utility;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class ActivityLogServiceImpl implements ActivityLogService {

    private static Logger logger = Logger.getLogger(ActivityLogServiceImpl.class);
    public final int DefaultPollingInterval = 60;//5 mins
    public final int InitialDelayPollingInterval = 10;//5 mins

    @Autowired
    private ActivityLogDao activityLogDao;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UsersDao usersDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UsersService usersService;

    @Autowired
    private EmailServiceProviderService emailServiceProviderService;

    /**
     * {@inheritDoc}
     */
    public Integer save(ActivityLog activityLog) throws ProcessFailed {
        activityLog.setCreatedAt(new Date());
        return activityLogDao.save(activityLog);
    }

    /**
     * {@inheritDoc}
     */
    public List<ActivityLogResponse> getAllActivityLog() throws ProcessFailed {
        List<ActivityLog> activityLogList = activityLogDao.getAllActivityLog();

        if (activityLogList == null) {
            throw new ProcessFailed("No activity log found.");
        }

        List<ActivityLogResponse> activityLogResponseList = getAllActivityLogResponse(activityLogList);

        return activityLogResponseList;
    }

    /**
     * {@inheritDoc}
     */
    public List<ActivityLogResponse> getAllActivityLogByScheduledEntityListId(Integer scheduledEntityListId) throws ProcessFailed {
        List<ActivityLog> activityLogList = activityLogDao.getAllActivityLogByScheduledEntityListId(scheduledEntityListId);
        if (activityLogList == null) {
            throw new ProcessFailed("No activity log found.");

        }
        List<ActivityLogResponse> activityLogResponseList = getAllActivityLogResponse(activityLogList);
        return activityLogResponseList;
    }

    /**
     * {@inheritDoc}
     */
    public ActivityLog getActivityLogByActivityLogId(Integer activityLogId) throws ProcessFailed {
        ActivityLog activityLog = activityLogDao.getActivityLogByActivityLogId(activityLogId);
        if (activityLog == null) {
            throw new ProcessFailed("No activity log found.");
        }
        return activityLog;
    }

    private List<ActivityLogResponse> getAllActivityLogResponse(List<ActivityLog> activityLogList) {
        List<ActivityLogResponse> activityLogResponseList = new ArrayList<>();
        for (ActivityLog activityLog : activityLogList) {
            ActivityLogResponse activityLogResponse = new ActivityLogResponse();
            activityLogResponse.setActivityName(activityLog.getFkActivityId().getActivityName());
            activityLogResponse.setAssignedToName(activityLog.getAssignedTo().getUserName());
            activityLogResponse.setCreatedByName(activityLog.getCreatedBy().getUserName());
            activityLogResponse.setScheduledEntityListId(activityLog.getFkScheduledEntityid().getScheduledEntityListId());
            activityLogResponse.setCreatedAt(activityLog.getCreatedAt());
            activityLogResponseList.add(activityLogResponse);
        }
        return activityLogResponseList;
    }

    /**
     * {@inheritDoc}
     */
    @Async
    public void saveActivityLog(ActivityLogDetails activityLogDetails) throws ProcessFailed {
        try {
            ActivityLog activityLog = new ActivityLog();
            ScheduledEntityList scheduledEntityList = new ScheduledEntityList();
            scheduledEntityList.setScheduledEntityListId(activityLogDetails.getScheduledEntityId());
            activityLog.setFkScheduledEntityid(scheduledEntityList);
            Activity activity = new Activity();
            activity.setActivityId(activityLogDetails.getActivityId());
            Integer createdById = activityLogDetails.getCreatedBy();
            Users createdBy = usersDao.getUserById(createdById);
            Company company = companyDao.getCompanyById(activityLogDetails.getCompanyId());
            Users createdUser = new Users();
            createdUser.setUserId(activityLogDetails.getCreatedBy());
            activityLog.setCreatedBy(createdUser);
            activityLog.setCreatedAt(new Date());
            activityLog.setFkActivityId(activity);
            activityLogDao.save(activityLog);
            switch (activityLogDetails.getActivityId()) {
                case 1:
//                        ACTIVITY_CREATED_ACTION_ID

                    if (activityLogDetails.getAssignedTo() != null && activityLogDetails.getAssignedTo() != 0) {
                        sendNotificationEmail(activityLogDetails.getActivityId(), createdBy.getUserName(), Utility.combineUserName(createdBy),
                                company.getCompanyId(),company.getCompanyName(),activityLogDetails.getActionType(),activityLogDetails.getProgramName(),
                                activityLogDetails.getActionDate(),activityLogDetails.getActionStatus(), activityLogDetails.getActionTitle(), Utility.combineUserName(createdBy));
                    }
                    break;
                case 2:
//                        ACTIVITY_ASSIGNED_TO_ID
                    if (activityLogDetails.getAssignedTo() != null && activityLogDetails.getAssignedTo() != 0) {
                        Users assignedTo = new Users();
                        assignedTo.setUserId(activityLogDetails.getAssignedTo());
                        activityLog.setAssignedTo(assignedTo);
                            Users sendToUser = usersService.getUserById(activityLogDetails.getAssignedTo());
                            sendNotificationEmail(activityLogDetails.getActivityId(), sendToUser.getUserName(), Utility.combineUserName(sendToUser),
                                company.getCompanyId(),company.getCompanyName(),activityLogDetails.getActionType(),activityLogDetails.getProgramName(),
                                activityLogDetails.getActionDate(),activityLogDetails.getActionStatus(), activityLogDetails.getActionTitle(), Utility.combineUserName(createdBy));
                    }
                    break;
                case 3:
//                        ACTIVITY_REASSIGNED_TO_ID
                    if (activityLogDetails.getAssignedTo() != null && activityLogDetails.getAssignedTo() != 0) {
                        Users assignedTo = new Users();
                        assignedTo.setUserId(activityLogDetails.getAssignedTo());
                        activityLog.setAssignedTo(assignedTo);
                            Users sendToUser = usersService.getUserById(activityLogDetails.getAssignedTo());
                            sendNotificationEmail(activityLogDetails.getActivityId(), sendToUser.getUserName(), Utility.combineUserName(sendToUser),
                                company.getCompanyId(),company.getCompanyName(),activityLogDetails.getActionType(),activityLogDetails.getProgramName(),
                                activityLogDetails.getActionDate(),activityLogDetails.getActionStatus(), activityLogDetails.getActionTitle(), Utility.combineUserName(createdBy));
                        }
                    break;
                case 4:
                    break;
//                        ACTIVITY_ADDED_TEMPLATE_ID
                case 5:
                    break;
//                        ACTIVITY_UPDATED_TEMPLATE_ID
                case 6:
                    break;
//                        ACTIVITY_REMOVED_TEMPLATE_ID
                case 7:
                    break;
//                        ACTIVITY_UPDATED_ACTION_ID
                case 8:
                    break;
//                        ACTIVITY_APPROVED_ACTION_ID
                case 9:
                    break;
//                        ACTIVITY_DISAPPROVED_ACTION_ID
                case 10:
                    break;
//                        ACTIVITY_DELETED_COMMENT_ACTION_ID
                case 11:
                    break;
//                        ACTIVITY_PLAY_ACTION_ID
                case 12:
                    break;
//                        ACTIVITY_PAUSE_ACTION_ID
            }

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("activity_save_problem", new String[]{}, Locale.US));
        }
    }

    @Async
    public Boolean sendNotificationEmail(Integer activityId, String toEmailId, String userName,
            Integer companyId,String companyName, String actionType,String campaignName,
            Timestamp actionDate,String actionStatus, String actionTitle, String createdByUserName) throws ProcessFailed {
 
            String body = null;String formattedSubject = "";
        
            ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
            String context_real_path = servletContext.getRealPath("");

            String contextPath = Utility.getServerName(context_real_path);
            
        try {

            switch (activityId) {
                case 1:
                    body = messageSource.getMessage("notification_message_activity_created_action", new String[]{}, Locale.US);
                    body = body.replace("%t", actionTitle);
                    body = body.replace("%s", createdByUserName);
                    body = body.replace("%c", companyName);
                    break;
                case 2:
                    body = ServletUtil.convertHTMLToString("actionassignmentemailtemplate.html", servletContext);
                    body = body.replace("*Name of person who assigned*", createdByUserName);
                    body = body.replace("*action type*", actionType);
                    body = body.replace("*company name*", companyName);
                    body = body.replace("*Marketing Action Name*", actionTitle);
                    body = body.replace("*Marketing Campaign Name*", campaignName);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE d MMM yy 'at' h:mm a");
                    String result = dateFormatter.format(actionDate);
                    body = body.replace("*Marketing Action Date*", result);
                    body = body.replace("*Marketing Action Status*", actionStatus);
                    body = body.replace("host", contextPath);
                    
                    break;
                case 3:
                    body = ServletUtil.convertHTMLToString("actionassignmentemailtemplate.html", servletContext);
                    body = body.replace("*Name of person who assigned*", createdByUserName);
                    body = body.replace("*action type*", actionType);
                    body = body.replace("*company name*", companyName);
                    body = body.replace("*Marketing Action Name*", actionTitle);
                    body = body.replace("*Marketing Campaign Name*", campaignName);
                    dateFormatter = new SimpleDateFormat("EEE d MMM yy 'at' h:mm a");
                    result = dateFormatter.format(actionDate);
                    body = body.replace("*Marketing Action Date*", result);
                    body = body.replace("*Marketing Action Status*", actionStatus);
                    body = body.replace("host", contextPath);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
            }
            Content content = new Content(IConstants.kContentHTML, body);
            Email emailTo = new Email(toEmailId, userName);
            String subject = messageSource.getMessage("notification_subject", new String[]{}, Locale.US);
            formattedSubject = String.format(subject, actionTitle);
            Mail mail = new Mail(null, formattedSubject, emailTo, content);
            emailServiceProviderService.sendEmail(mail, EmailType.BrndBot_NoReply, companyId, createdByUserName);
            return true;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem", new String[]{}, Locale.US));
        }
    }

}
