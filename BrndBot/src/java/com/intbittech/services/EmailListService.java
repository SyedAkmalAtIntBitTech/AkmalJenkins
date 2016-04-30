/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * <code>{@link CategoryService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface EmailListService {

    public String getEmailList(HttpServletRequest request, Integer companyId) throws Exception;

    public Boolean setEmailList(Map<String, Object> requestBodyMap, Integer companyId) throws Exception;
}
