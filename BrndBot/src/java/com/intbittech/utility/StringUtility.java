/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.intbittech.modelmappers.MarketingActionDetails;
import com.intbittech.modelmappers.MarketingActionsObjectDetails;
import com.intbittech.modelmappers.TaskDetails;
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
        return (value == null || (value.trim().length() == 0) || value.equalsIgnoreCase("null"));
    }
    
    public static String objectListToJsonString(MarketingActionsObjectDetails marketingActionsObjectDetails) throws IOException {
        
        final StringWriter stringWriter =new StringWriter();
    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(stringWriter, marketingActionsObjectDetails);
    return stringWriter.toString();
        
    }
    
    public static String objectListToJsonString(Object object) throws IOException {
        
        final StringWriter stringWriter =new StringWriter();
    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(stringWriter, object);
    return stringWriter.toString();
        
    }    
    public static List jsonStringToObjectList(String jsonString) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jFactory = new JsonFactory();
        jsonString = "["+jsonString+"]";
        JsonParser jsonParser= jFactory.createParser(jsonString);
        List<MarketingActionsObjectDetails> myObjectList = mapper.readValue(jsonParser, mapper.getTypeFactory().constructCollectionType(List.class, MarketingActionsObjectDetails.class));
        return myObjectList;
    }

    public static Object stringToObject(String content){
        Gson gson = new Gson(); 
        final Object object = gson.fromJson(content, Object.class);                
        return object;
    }
}
