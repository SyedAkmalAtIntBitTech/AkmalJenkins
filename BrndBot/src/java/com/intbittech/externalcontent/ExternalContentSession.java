/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.externalcontent;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author AR
 */
@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExternalContentSession {
    
    private final Map<String, Object> map = new HashMap<>();
    
    public void addSessionKeyValue(String key, Object value) {
        map.put(key, value);
    }
    
    public Object getSessionKeyValue(String key) {
        if(map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }
}
