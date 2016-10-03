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
import com.intbittech.responsemappers.CommentLogResponse;
import com.intbittech.services.CommentLogService;
import com.intbittech.utility.Utility;
import java.util.ArrayList;
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
public class CommentLogServiceImpl implements CommentLogService{
    
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
    public List<CommentLogResponse> getAllCommentLog() throws ProcessFailed {
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
    public List<CommentLogResponse> getAllCommentLogByScheduledEntityListId(Integer scheduledEntityListId,Integer userId) throws ProcessFailed {
         List<CommentLog> CommentLogList = commentLogDao.getAllCommentLogByScheduledEntityListId(scheduledEntityListId);
        if (CommentLogList == null) {
            throw new ProcessFailed("No Comment log found.");
        }
        List<ActivityLog> activityLogList =activityLogDao.getAllActivityLogByScheduledEntityListId(scheduledEntityListId);
        List<CommentLogResponse> commentLogResponseList = getAllCommentLogResponse(CommentLogList,activityLogList,userId);
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
    
    private List<CommentLogResponse> getAllCommentLogResponse(List<CommentLog> commentLogList,List<ActivityLog> activityLogList,Integer userId) {
        List<CommentLogResponse> commentLogResponseList = new ArrayList<>();
         
        for (CommentLog commentLog : commentLogList) {
           CommentLogResponse commentLogResponse = new CommentLogResponse();
            commentLogResponse.setCommentName(commentLog.getComment());
            commentLogResponse.setCommentByEmailId(commentLog.getCommentedBy().getUserName());
            commentLogResponse.setCommentByFirstName(commentLog.getCommentedBy().getFirstName());
            String initials = Utility.getFirstTwoCharactersOfName(commentLog.getCommentedBy().getFirstName(), commentLog.getCommentedBy().getLastName());
            commentLogResponse.setInitials(initials);
            commentLogResponse.setCommentByLastName(commentLog.getCommentedBy().getLastName());
            commentLogResponse.setScheduledEntityListId(commentLog.getFkScheduledEntityid().getScheduledEntityListId());
            commentLogResponse.setCreatedAt(commentLog.getCreatedAt());
            commentLogResponse.setCommentId(commentLog.getCommentLogId());
            if(commentLog.getCommentedBy().getUserId() == userId){
                commentLogResponse.setIsLoginUser(true);
           }
            else{
                commentLogResponse.setIsLoginUser(false);
           }
            commentLogResponseList.add(commentLogResponse);
        }
             for (ActivityLog activityLog : activityLogList) {
           CommentLogResponse commentLogResponse = new CommentLogResponse();
            commentLogResponse.setCommentName(activityLog.getFkActivityId().getActivityName());
            commentLogResponse.setCommentByEmailId(activityLog.getCreatedBy().getUserName());
            commentLogResponse.setCommentByFirstName(activityLog.getCreatedBy().getFirstName());
            String initialsName = Utility.getFirstTwoCharactersOfName(activityLog.getCreatedBy().getFirstName(), activityLog.getCreatedBy().getLastName());
            commentLogResponse.setInitials(initialsName);
            commentLogResponse.setCommentByLastName(activityLog.getCreatedBy().getLastName());
            commentLogResponse.setScheduledEntityListId(activityLog.getFkScheduledEntityid().getScheduledEntityListId());
            commentLogResponse.setCreatedAt(activityLog.getCreatedAt());
            commentLogResponse.setCommentId(activityLog.getActivityLogId());
            if(activityLog.getCreatedBy().getUserId() == userId){
                commentLogResponse.setIsLoginUser(true);
           }
            else{
                commentLogResponse.setIsLoginUser(false);
           }
            commentLogResponseList.add(commentLogResponse);
             }
            
        
        return commentLogResponseList;
    }
}
