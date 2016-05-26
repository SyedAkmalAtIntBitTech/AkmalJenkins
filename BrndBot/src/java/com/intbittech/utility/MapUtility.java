/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import com.intbittech.divtohtml.ProcessHTML;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author AR
 */
public class MapUtility {

    public static boolean mapContainsKey(Map<String, Object> requestBodyMap, String key) {
        if (!requestBodyMap.containsKey(key)
                || requestBodyMap.get(key) == null
                || StringUtils.isEmpty(requestBodyMap.get(key).toString())) {
            return false;
        }
        return true;
    }

    public static Map<String, String> convertObjectToStringMap(Map<String, Object> objectMap) {
        Map<String, String> newMap = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                newMap.put(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof Date) {
                String dateString = ProcessHTML.inputFormat.format(entry.getValue());
                newMap.put(entry.getKey(), dateString);
            }
        }
        return newMap;
    }
}
