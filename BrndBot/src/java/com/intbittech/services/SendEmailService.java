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
public interface SendEmailService {

    public void sendMail(Map<String, Object> requestBodyMap, Integer companyId) throws Exception;

    public String getTags(Integer companyId) throws Exception;

    public String previewEmail(Integer companyId, Map<String, Object> requestBodyMap);
}