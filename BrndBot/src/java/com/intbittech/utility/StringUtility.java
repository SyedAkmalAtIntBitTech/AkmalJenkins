/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 *
 * @author AR
 */
public class StringUtility {

    public static String safeBoolean(boolean valid) {
        return valid ? "true" : "false";
    }

    public static boolean isEmpty(final String value) {
        return (value == null || (value.trim().length() == 0));
    }
    
    public static String objectListToJsonString(List list) throws IOException {
        
        final StringWriter stringWriter =new StringWriter();
    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(stringWriter, list);
    return stringWriter.toString();
        
    }

}
