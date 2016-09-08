/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CommentLog;
import java.util.List;

/**
 *
 * @author ajit @ Intbit
 */
public interface CommentLogDao {
    
     /**
     * This method save {@link CommentLog} into the database.
     *
     * @param commentLog the commentLog
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(CommentLog commentLog) throws ProcessFailed;
    
     /**
     * This method retrieves the list of {@link CommentLog} from DAO layer.
     *
     * @return {@link CommentLog}
     * @throws ProcessFailed the process failed
     */
    public List<CommentLog> getAllCommentLog() throws ProcessFailed;
    
     /**
     * This method retrieves the list of {@link CommentLog} from DAO layer.
     * @param scheduledEntityListId the scheduledEntityListId
     * @return {@link CommentLog}
     * @throws ProcessFailed the process failed
     */
    public List<CommentLog> getAllCommentLogByScheduledEntityListId(Integer scheduledEntityListId ) throws ProcessFailed;
    
    /**
     * This method retrieves the list of {@link CommentLog} from DAO layer.
     * @param commentLogId the commentLogId
     * @return {@link CommentLog}
     * @throws ProcessFailed the process failed
     */
    public CommentLog getCommentLogByCommentLogId(Integer commentLogId ) throws ProcessFailed;
    
    /**
     * This method delete particular {@link CommentLog} based on the
     * activity from the database.
     * @param commentLog the commentLog
     * @throws ProcessFailed the process failed
     */
    public void delete(CommentLog commentLog) throws ProcessFailed;
}