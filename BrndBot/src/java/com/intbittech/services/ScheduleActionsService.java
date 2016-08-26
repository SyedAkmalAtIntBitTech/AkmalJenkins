/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ilyas
 */
public interface ScheduleActionsService {

    public String getActions(Map<String, Object> requestBodyMap, Integer companyId) throws Exception;

    public Map<String, Integer> scheduleEmail(Map<String, Object> requestBodyMap, Integer companyId);

    public Map<String, Integer> scheduleEmailActions(Map<String, Object> requestBodyMap, Integer companyId);

    public List<Map<String, Integer>> scheduleSocialPostActions(Map<String, Object> requestBodyMap, Integer companyId);

    public List<Map<String, Integer>> scheduleSocialPost(Map<String, Object> requestBodyMap, Integer companyId);
    
   
}
