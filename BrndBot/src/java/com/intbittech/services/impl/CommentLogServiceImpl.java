/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ActivityLogDao;
import com.intbittech.dao.CommentLogDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ActivityLog;
import com.intbittech.model.CommentLog;
import com.intbittech.responsemappers.CommentActivityLogResponse;
import com.intbittech.services.CommentLogService;
import com.intbittech.utility.Utility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class CommentLogServiceImpl implements CommentLogService {

    private static Logger logger = Logger.getLogger(CommentLogServiceImpl.class);
    @Autowired
    private CommentLogDao commentLogDao;
    @Autowired
    private ActivityLogDao activityLogDao;

    /**
     * {@inheritDoc}
     */
    public Integer save(CommentLog commentLog) throws ProcessFailed {
        commentLog.setCreatedAt(new Date());
        return commentLogDao.save(commentLog);
    }

    /**
     * {@inheritDoc}
     */
    public List<CommentActivityLogResponse> getAllCommentLog() throws ProcessFailed {
        List<CommentLog> CommentLogList = commentLogDao.getAllCommentLog();
        if (CommentLogList == null) {
            throw new ProcessFailed("No Comment log found.");
        }
        // List<CommentLogResponse> commentLogResponseList = getAllCommentLogResponse(CommentLogList);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public List<CommentActivityLogResponse> getAllCommentLogByScheduledEntityListId(Integer scheduledEntityListId, Integer userId) throws ProcessFailed {
        List<CommentLog> CommentLogList = commentLogDao.getAllCommentLogByScheduledEntityListId(scheduledEntityListId);

        List<ActivityLog> activityLogList = activityLogDao.getAllActivityLogByScheduledEntityListId(scheduledEntityListId);
        List<CommentActivityLogResponse> commentLogResponseList = getAllCommentLogResponse(CommentLogList, activityLogList, userId);
        return commentLogResponseList;
    }

    /**
     * {@inheritDoc}
     */
    public CommentLog getCommentLogByCommentLogId(Integer commentLogId) throws ProcessFailed {
        CommentLog CommentLog = commentLogDao.getCommentLogByCommentLogId(commentLogId);
        if (CommentLog == null) {
            throw new ProcessFailed("No Comment with id" + commentLogId + ".");
        }
        return CommentLog;
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer commentLogId) throws ProcessFailed {
        CommentLog CommentLog = commentLogDao.getCommentLogByCommentLogId(commentLogId);
        if (CommentLog == null) {
            throw new ProcessFailed("No Comment with id" + commentLogId + ".");
        }
        commentLogDao.delete(CommentLog);
    }

    private List<CommentActivityLogResponse> getAllCommentLogResponse(List<CommentLog> commentLogList, List<ActivityLog> activityLogList, Integer userId) {
        List<CommentActivityLogResponse> commentActivityLogResponseList = new ArrayList<>();
        if (commentLogList != null) {
            for (CommentLog commentLog : commentLogList) {
                CommentActivityLogResponse commentLogResponse = new CommentActivityLogResponse();
                commentLogResponse.setCommentName(commentLog.getComment());
                commentLogResponse.setCreatedByUserId(commentLog.getCommentedBy().getUserId());
                commentLogResponse.setCreatedByByEmailId(commentLog.getCommentedBy().getUserName());
                commentLogResponse.setCreatedByFirstName(commentLog.getCommentedBy().getFirstName());
                commentLogResponse.setCreatedByLastName(commentLog.getCommentedBy().getLastName());
                String initials = Utility.getFirstTwoCharactersOfName(commentLog.getCommentedBy().getFirstName(), commentLog.getCommentedBy().getLastName());
                commentLogResponse.setInitials(initials);
                commentLogResponse.setIsActivity(false);
                commentLogResponse.setScheduledEntityListId(commentLog.getFkScheduledEntityid().getScheduledEntityListId());
                commentLogResponse.setCreatedAt(commentLog.getCreatedAt());
                commentLogResponse.setCommentId(commentLog.getCommentLogId());
                if (commentLog.getCommentedBy().getUserId() == userId) {
                    commentLogResponse.setIsLoginUser(true);
                } else {
                    commentLogResponse.setIsLoginUser(false);
                }
                commentActivityLogResponseList.add(commentLogResponse);
            }
        }
        if (activityLogList != null) {
            for (ActivityLog activityLog : activityLogList) {
                CommentActivityLogResponse commentActivityLogResponse = new CommentActivityLogResponse();
                commentActivityLogResponse.setActivityName(activityLog.getFkActivityId().getActivityName());
                commentActivityLogResponse.setCreatedByUserId(activityLog.getCreatedBy().getUserId());
                commentActivityLogResponse.setCreatedByByEmailId(activityLog.getCreatedBy().getUserName());
                commentActivityLogResponse.setCreatedByFirstName(activityLog.getCreatedBy().getFirstName());
                commentActivityLogResponse.setCreatedByLastName(activityLog.getCreatedBy().getLastName());
                commentActivityLogResponse.setScheduledEntityListId(activityLog.getFkScheduledEntityid().getScheduledEntityListId());
                commentActivityLogResponse.setCreatedAt(activityLog.getCreatedAt());
                commentActivityLogResponse.setIsActivity(true);
                if (activityLog.getAssignedTo() != null) {
                    commentActivityLogResponse.setAssignedToEmailId(activityLog.getAssignedTo().getUserName());
                    commentActivityLogResponse.setAssignedToFirstName(activityLog.getAssignedTo().getFirstName());
                    commentActivityLogResponse.setAssignedToLastName(activityLog.getAssignedTo().getLastName());

                }
                commentActivityLogResponse.setCommentId(activityLog.getActivityLogId());
                if (activityLog.getCreatedBy().getUserId() == userId) {
                    commentActivityLogResponse.setIsLoginUser(true);
                } else {
                    commentActivityLogResponse.setIsLoginUser(false);
                }
                commentActivityLogResponseList.add(commentActivityLogResponse);
            }
        }
        Collections.sort(commentActivityLogResponseList);

        return commentActivityLogResponseList;
    }
}
