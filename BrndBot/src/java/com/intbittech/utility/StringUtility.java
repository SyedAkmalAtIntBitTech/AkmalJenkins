/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intbittech.modelmappers.MarketingActionDetails;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

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
    
    public static List objectListToJsonString(String jsonString) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jFactory = new JsonFactory();
        JsonParser jsonParser= jFactory.createParser(jsonString);
        List<MarketingActionDetails> myObjectList = mapper.readValue(jsonParser, mapper.getTypeFactory().constructCollectionType(List.class, MarketingActionDetails.class));
        return myObjectList;
    }

}
