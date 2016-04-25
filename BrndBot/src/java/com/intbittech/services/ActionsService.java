/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import java.util.Map;

/**
 *
 * @author ilyas
 */
public interface ActionsService {

    public String getActions(Map<String, Object> requestBodyMap, Integer userId) throws Exception;
    
   
}
