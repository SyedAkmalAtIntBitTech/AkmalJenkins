/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ActivityLogDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Activity;
import com.intbittech.model.ActivityLog;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.ActivityLogDetails;
import com.intbittech.responsemappers.ActivityLogResponse;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.services.ActivityLogService;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.Utility;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    @Autowired
    private ActivityLogDao activityLogDao;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailServiceProviderService emailServiceProviderService;
    
    @Autowired
    private UsersDao usersDao;
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
    public Integer saveActivityLog(ActivityLogDetails activityLogDetails) throws ProcessFailed {

        ActivityLog activityLog = new ActivityLog();
        ScheduledEntityList scheduledEntityList = new ScheduledEntityList();
        scheduledEntityList.setScheduledEntityListId(activityLogDetails.getScheduledEntityId());
        activityLog.setFkScheduledEntityid(scheduledEntityList);
        Activity activity = new Activity();
        activity.setActivityId(activityLogDetails.getActivityId());
        if (activityLogDetails.getAssignedTo() != null) {
            Users assignedTo = new Users();
            assignedTo.setUserId(activityLogDetails.getAssignedTo());
            activityLog.setAssignedTo(assignedTo);
            Users sendToUser = usersDao.getUserById(activityLogDetails.getAssignedTo());
            sendNotificationEmail(sendToUser.getUserName(), Utility.combineUserName(sendToUser));
        }
        Users createdUser = new Users();
        createdUser.setUserId(activityLogDetails.getCreatedBy());
        activityLog.setCreatedBy(createdUser);
        activityLog.setCreatedAt(new Date());
        activityLog.setFkActivityId(activity);
        return activityLogDao.save(activityLog);
    }
    
    public Boolean sendNotificationEmail(String toEmailId, String userName)throws ProcessFailed {
        try {
            String companyName = messageSource.getMessage("companyName", new String[]{}, Locale.US);
            String body = messageSource.getMessage("notification_message", new String[]{}, Locale.US);
            String formattedBody = String.format(body);
            Content content = new Content(IConstants.kContentHTML, formattedBody);
            Email emailTo = new Email(toEmailId, userName);
            String subject = messageSource.getMessage("notification_subject", new String[]{}, Locale.US);
            String formattedSubject = String.format(subject, companyName);
            Mail mail = new Mail(null, formattedSubject, emailTo, content);
            emailServiceProviderService.sendEmail(mail, EmailType.BrndBot_NoReply);
            return true;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem", new String[]{}, Locale.US));
        }
    }
}
